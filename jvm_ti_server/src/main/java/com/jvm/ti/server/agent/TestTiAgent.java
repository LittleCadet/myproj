package com.jvm.ti.server.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import java.lang.instrument.UnmodifiableClassException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.AbstractList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shenxie
 * @date 2020/9/10
 */
public class TestTiAgent {

    private static Instrumentation INST;

    /**
     *
     * 关于premain（） ， 不需要启动，只要有jar就行。 在vmOpetions中定义  -javaagent:/Users/littlecadet/workspace/self/myproj/jvm_ti_premain_server/target/jvm_ti_premain_server-0.0.1-SNAPSHOT.jar=premain
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=============main start===========");

        for (int i = 0 ; i < args.length ; i++){
            System.out.println(String.format("args: %s" , args[i]));
        }

        //大招： 容器挂了 ， 会用shutdown的钩子 ， 执行指定的任务。
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            //TODO : 用netty发送消息到 jvm_ti_agentmain_server
        }));


        while(true){

            System.out.println("============TestAgent 运行中 ==============");
            Thread.sleep(1000);
        }


    }


    /**
     * 在JVM启动之后运行。 Instrumentation规定：方法名必须为 agentmain
     * 如果运行异常【报错，且没有捕获】，那么会导致agent启动失败 【 Agent JAR loaded but agent failed to initialize 】
     *
     *
     * 该方法必须定义在业务方
     *
     *
     * @param args
     * @param instrumentation
     */
    public static void agentmain(String args , Instrumentation instrumentation) throws Exception {
        System.out.println("=============afterMain start=========");

        //获取所有加载的类
        Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
        for (Class cls : allLoadedClasses){
            System.out.println(String.format("cls.getName() : %s , cls.getCanonicalName() : %s , cls.getSimpleName() : %s ,  cls.getTypeName() : %s" , cls.getName() , cls.getCanonicalName() , cls.getSimpleName() , cls.getTypeName()) );
        }
        //throw new Exception();
        System.out.println(String.format("args : %s , instrumentation : %s" , args , instrumentation));

        instrumentation.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if (!className.equals("com/jvm/ti/server/agent/TestTiAgent")) {
                    return null;
                }
                try {
                    // 在instrumentation中使用javassist的方式修改字节码
                    System.out.println("MyClassTransformer，当前类名:" + className);
                    ClassPool classPool = ClassPool.getDefault();
                    CtClass ctClass = classPool.get("com.jvm.ti.server.agent.TestTiAgent");
                    CtMethod ctMethod = ctClass.getDeclaredMethod("showInfo");
                    // 先解冻， 否则报错： com.jvm.ti.server.agent.TestTiAgent class is frozen
                    ctClass.defrost();
                    ctMethod.insertBefore("{ System.out.println(\"start\");}");
                    ctMethod.insertAfter("{ System.out.println(\"end\"); }");
                    return ctClass.toBytecode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

        }, true);

        try {
            // 重新对类加载 触发MyClassTransformer
            instrumentation.retransformClasses(TestTiAgent.class);
            TestTiAgent.class.newInstance().showInfo();
            System.out.println("attach agent 加载完毕");
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }

        System.out.println("=============afterMain stop=========");
    }


    public void showInfo(){
        System.out.println("正在运行的类是retransformClasses类");
    }
}
