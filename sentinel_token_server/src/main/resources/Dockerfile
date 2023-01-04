# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER LittleCadet <LittleCadet@163.com>
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为app.jar
ADD sentinel_token_server-0.0.1-SNAPSHOT.jar app.jar
#ADD pinpoint-agent-ewt-2.2.2-SNAPSHOT.tar.gz ./

# 替换镜像源
ADD sources.list /etc/apt
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 04EE7237B7D453EC
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 648ACFD622F3D138
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 0E98404D386FA1D9
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys EF0F382A1A7B6500
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 112695A0E562B32A
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys AA8E81B4331F7F50

RUN echo 'deb http://archive.debian.org/debian jessie-backports main' > /etc/apt/sources.list.d/jessie-backports.list
RUN echo "Acquire::Check-Valid-Until \"false\";" > /etc/apt/apt.conf.d/100disablechecks

# 安装基础linux命令
RUN apt-get update && apt-get install --force-yes -y iputils-ping  && apt-get install --force-yes -y telnet && apt-get install --force-yes -y net-tools

# 运行jar包
RUN bash -c 'touch /app.jar'
#ENTRYPOINT ["java","-Dpinpoint.applicationName=normal-server","-Dprofiler.transport.grpc.collector.ip=pp-collector-service", "-javaagent:./pinpoint-agent-ewt-2.2.2-SNAPSHOT/pinpoint-bootstrap.jar" , "-Djava.security.egd=file:/dev/./urandom","-jar", "app.jar"]
ENTRYPOINT ["java","-Dspring.cloud.sentinel.transport.port=8719", "-Dspring.cloud.sentinel.transport.dashboard=121.43.160.21:31868" , "-Djava.security.egd=file:/dev/./urandom","-jar", "app.jar"]