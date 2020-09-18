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
public class TestPreAgent {

    private static Instrumentation INST;

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

        INST = instrumentation;
        process();

    }


    /**
     * 通过ClassFileTransformer实现字节码的增强处理
     */
    private static void process() {
        INST.addTransformer(new ClassFileTransformer() {
                                @Override
                                public byte[] transform(ClassLoader loader, String className,
                                                        Class<?> clazz,
                                                        ProtectionDomain protectionDomain,
                                                        byte[] byteCode) throws IllegalClassFormatException {
                                    System.out.println(String.format("Process by ClassFileTransformer,target class = %s", className));
                                    return byteCode;
                                }

                                //重新定义的类， 能否通过retransformClasses 的形式 ，再次变更。
                            } , true
        );
    }
}
