package com.jvm.ti.server.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.AbstractList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shenxie
 * @date 2020/9/10
 */
public class TestAgent {

    /**
     * javaAgent简介：
     * 在jdk1.5后， 使用agent构建一个独立于应用程序之外的程序【agent】 ， 用来协助监测， 运行， 甚至替换 jvm上的程序 。 实现虚拟机级别的aop。
     *
     * javaAgent种类：
     * 1.在主程序之前运行agent
     * 2.在主程序之后运行agent
     *
     * 使用javaAgent运行premain方法， 需要在生成的jar包里面的 META-INF/MAINIFEST.MF 必须包含 Premain-Class这个属性 ，
     * 即为需要在maven的maven-jar-plugin插件中Premain-Class完成运行主类的定义。
     *
     * 使用方法：
     * 1.定义premain方法： Instrumentation规定：方法名必须为premain
     * 2.在pom.xml定义： maven-jar-plugin 的 Premain-Class关于主类的定义。
     * 3.打jar包。
     * 4.在vm option中设置： -javaagent:绝对路径/jvm_ti_server-0.0.1-SNAPSHOT.jar=参数  【参数即为： premain方法中：args】
     * 5.运行main方法即可。
     *
     * 运行结果：
     * =============premain start=========
     * args : javaAgent , instrumentation : sun.instrument.InstrumentationImpl@6f94fa3e
     * =============main start===========
     *
     *
     * javaAgent
     * 优点：可以实现JVM级别的AOP
     * 缺点： 一旦agent运行失败 ， 那么主程序将不会运行。容器会启动失败。
     *
     * @param args
     * @param instrumentation
     */
    public static void premain(String args , Instrumentation instrumentation) throws Exception {
        System.out.println("=============premain start=========");
        //throw new Exception();
        System.out.println(String.format("args : %s , instrumentation : %s" , args , instrumentation));
    }


    /**
     * 关于jdk中 tools.jar maven编译报错的问题：
     * 在vm options : -Xbootclasspath/a:/Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/lib/tools.jar
     * @param args
     * @throws IOException
     * @throws AttachNotSupportedException
     * @throws AgentLoadException
     * @throws AgentInitializationException
     */
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        System.out.println("=============main start===========");
        List<VirtualMachineDescriptor> virtualMachineDescriptors = VirtualMachine.list();
        Assert.notEmpty(virtualMachineDescriptors , "获取本机的java进程失败");

        //获取进程id
        List<VirtualMachineDescriptor> descriptors = virtualMachineDescriptors.stream().filter(virtualMachineDescriptor -> virtualMachineDescriptor.toString().contains("TestAgent")).collect(Collectors.toList());
        String id = descriptors.get(0).id();
        VirtualMachine virtualMachine = VirtualMachine.attach(id);
        virtualMachine.loadAgent("/Users/littlecadet/workspace/self/myproj/jvm_ti_server/target/jvm_ti_server-0.0.1-SNAPSHOT.jar" , "after main");

        for (int i = 0 ; i < args.length ; i++){
            System.out.println(String.format("args: %s" , args[i]));
        }
    }


    /**
     * 在JVM启动之后运行。 Instrumentation规定：方法名必须为 agentmain
     * @param args
     * @param instrumentation
     */
    public static void agentmain(String args , Instrumentation instrumentation) {
        System.out.println("=============afterMain start=========");
        Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
        for (Class cls : allLoadedClasses){
            System.out.println(String.format("cls.getName() : %s , cls.getCanonicalName() : %s , cls.getSimpleName() : %s ,  cls.getTypeName() : %s" , cls.getName() , cls.getCanonicalName() , cls.getSimpleName() , cls.getTypeName()) );
        }
        //throw new Exception();
        System.out.println(String.format("args : %s , instrumentation : %s" , args , instrumentation));
    }
}
