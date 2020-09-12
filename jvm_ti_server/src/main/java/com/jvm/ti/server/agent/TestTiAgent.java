package com.jvm.ti.server.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
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
        System.out.println("=============afterMain stop=========");
    }
}
