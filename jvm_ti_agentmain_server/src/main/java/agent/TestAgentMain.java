package agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shenxie
 * @date 2020/9/10
 */
public class TestAgentMain {


    /**
     * 关于jdk中 tools.jar maven编译报错的问题：
     * 方式一 ；在vm options : -Xbootclasspath/a:/Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/lib/tools.jar
     * 方式二： jdk的jdk + jre要分开安装。
     * @param args
     * @throws IOException
     * @throws AttachNotSupportedException
     * @throws AgentLoadException
     * @throws AgentInitializationException
     */
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException, InterruptedException {
        System.out.println("=============main start===========");

        while(true){

            Thread.sleep(1000);
            System.out.println("获取TestAgent进程中");
            List<VirtualMachineDescriptor> virtualMachineDescriptors = VirtualMachine.list();
            Assert.notEmpty(virtualMachineDescriptors , "获取本机的java进程失败");

            //获取进程id
            List<VirtualMachineDescriptor> descriptors = virtualMachineDescriptors.stream().filter(virtualMachineDescriptor -> virtualMachineDescriptor.toString().contains("TestTiAgent")).collect(Collectors.toList());

            if(CollectionUtils.isEmpty(descriptors)){
                continue;
            }

            String id = descriptors.get(0).id();

            //通过VirtureMachine的attach方法连接到指定的进程上。
            VirtualMachine virtualMachine = VirtualMachine.attach(id);

            //接入agentmain（）【该方法必须定义在业务方】
            virtualMachine.loadAgent("/Users/littlecadet/workspace/self/myproj/jvm_ti_server/target/jvm_ti_server-0.0.1-SNAPSHOT.jar" );

            for (int i = 0 ; i < args.length ; i++){
                System.out.println(String.format("args: %s" , args[i]));
            }
        }

    }


    /**
     * 在JVM启动之后运行。 Instrumentation规定：方法名必须为 agentmain
     * 如果运行异常【报错，且没有捕获】，那么会导致agent启动失败 【 Agent JAR loaded but agent failed to initialize 】
     *
     *
     * 该方法必须定义在业务方
     *
     * @param args
     * @param instrumentation
     */
    /*public static void agentmain(String args , Instrumentation instrumentation) throws Exception {
        System.out.println("=============afterMain start=========");

        //获取所有加载的类
        Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
        for (Class cls : allLoadedClasses){
            System.out.println(String.format("cls.getName() : %s , cls.getCanonicalName() : %s , cls.getSimpleName() : %s ,  cls.getTypeName() : %s" , cls.getName() , cls.getCanonicalName() , cls.getSimpleName() , cls.getTypeName()) );
        }
        //throw new Exception();
        System.out.println(String.format("args : %s , instrumentation : %s" , args , instrumentation));
        System.out.println("=============afterMain stop=========");
    }*/
}
