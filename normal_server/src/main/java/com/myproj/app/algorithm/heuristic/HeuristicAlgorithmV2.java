package com.myproj.app.algorithm.heuristic;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 *
 * v1 与 v2的不相同点：
 * 1.将泛化属性的值 替换为父类属性的值的方式不同
 *   v1: 获取所有记录中的泛化属性的最小父节点，之后两条记录的泛化属性的值，替换为最小父节点的值
 *   v2: 严格按照blog所写： 将泛化属性的所有值 替换为 父节点的值。
 * 2.聚类告警的依据不同：
 *   v1: 每一次聚类的依据： 重写 dto中的equals方法
 *   v2: 首次聚类： 根据 重写dto的equals方法去判定
 *       非首次聚类： 根据最小不相似度 中的 a` 【指的是第二条记录】, 去聚类
 * 3.泛化属性覆盖量的统计方式不同：
 *   v1: 用最小父节点的值 的覆盖量
 *   v2: 所有最小不相似度 的属性的覆盖量 【严重弊端： 如果异常泛化树 所有叶子节点 到 根节点等高的情况下， 极有可能出现： 覆盖量带到100% 】
 *
 * 4.最小不相似度的运算：
 *   v1: 没有
 *   v2: 有 ， 为告警聚类做准备
 *
 *
 *
 * @author shenxie
 */
public class HeuristicAlgorithmV2 {

    /**
     * 可调整的minSize的因子
     */
    private static final Double MIN_SIZE_FACTORY = 0.2;

    /**
     * 鲁棒因子
     */
    private static final Double ROBUST_FACTORY = 0.05;

    private static GeneralizationTree generalizationTree;

    public static void main(String[] args) {

        int count = 1;

        // 准备泛化层次结构字典
        generalizationTree = prepareGeneralization();

        // 获取error日志源数据
        List<AlertDto> alertDtos = errorSource();

        // 第一轮合并日志信息 【根据exception】
        List<AlertDto> mergeDtos = mergeSource(alertDtos);

        // 定义鲁棒值 : 这里的告警集合使用的是： 原始告警集合 ： 原因： 聚合后的告警集合的值：可能太小了。完全不会触发告警聚类
        int minSize = getMinSize(alertDtos);

        // 判定是否需要聚类告警
        while (count <= minSize) {
            // 找到当前泛化程度最小的属性
            String minFiled = getMinFiled(mergeDtos);
            System.out.println("minFiled:" + minFiled);

            // 将两个错误日志中的特征属性： 替换为有公共节点的父类属性的值
            replaceFiled(minFiled, mergeDtos);

            // 获取不相似度最小的两个属性值的覆盖量
            List<GeneralizationTree.MinParentNode> minDissimilarities = getDissimilarityValue(mergeDtos);


            // 合并告警
            count = mergeSourceV2(mergeDtos, minDissimilarities);
        }


        // 输出聚类告警
        System.out.println("聚类告警结果：" + mergeDtos);

    }

    private static GeneralizationTree prepareGeneralization(){
        GeneralizationTree generalization = new GeneralizationTree();

        // 定义host
        GeneralizationTree.HostTree hostAll = new GeneralizationTree.HostTree();
        List<GeneralizationTree.HostTree> hosts = Lists.newArrayList();

        GeneralizationTree.HostTree kvm = new GeneralizationTree.HostTree();
        List<GeneralizationTree.HostTree> hostKvms = Lists.newArrayList();
        GeneralizationTree.HostTree hostKvm1 = new GeneralizationTree.HostTree();
        GeneralizationTree.HostTree hostKvm2 = new GeneralizationTree.HostTree();
        hostKvm1.setHostName("kvm-123-1");
        hostKvm1.setId(4);
        hostKvm1.setParentId(2);
        hostKvm2.setHostName("kvm-123-2");
        hostKvm2.setId(4);
        hostKvm2.setParentId(2);
        hostKvms.add(hostKvm1);
        hostKvms.add(hostKvm2);
        kvm.setHosts(hostKvms);
        kvm.setHostName("kvm");
        kvm.setId(2);
        kvm.setParentId(1);
        hosts.add(kvm);

        List<GeneralizationTree.HostTree> hostClouds = Lists.newArrayList();
        GeneralizationTree.HostTree cloud = new GeneralizationTree.HostTree();
        GeneralizationTree.HostTree hostCloud1 = new GeneralizationTree.HostTree();
        hostCloud1.setHostName("liveservice-123-1");
        hostCloud1.setId(5);
        hostCloud1.setParentId(3);
        GeneralizationTree.HostTree hostCloud2 = new GeneralizationTree.HostTree();
        hostCloud2.setHostName("liveservice-123-2");
        hostCloud2.setId(6);
        hostCloud2.setParentId(3);
        GeneralizationTree.HostTree hostCloud3 = new GeneralizationTree.HostTree();
        hostCloud3.setHostName("estudy-123-1");
        hostCloud3.setId(7);
        hostCloud3.setParentId(3);
        GeneralizationTree.HostTree hostCloud4 = new GeneralizationTree.HostTree();
        hostCloud4.setHostName("estudy-123-2");
        hostCloud4.setId(8);
        hostCloud4.setParentId(3);
        hostClouds.add(hostCloud1);
        hostClouds.add(hostCloud2);
        hostClouds.add(hostCloud3);
        hostClouds.add(hostCloud4);
        cloud.setHosts(hostClouds);
        cloud.setHostName("aLiYun");
        cloud.setId(3);
        cloud.setParentId(1);
        hosts.add(cloud);

        hostAll.setHostName("所有机器");
        hostAll.setId(1);
        hostAll.setParentId(1);
        hostAll.setHosts(hosts);


        // 定义app
        GeneralizationTree.AppTree app = new GeneralizationTree.AppTree("app", 1 , 1, "微服务", null);
        List<GeneralizationTree.AppTree> appTrees = Lists.newArrayList();
        GeneralizationTree.AppTree app1 = new GeneralizationTree.AppTree("liveservice", 2 , 1, "直播服务" , null);
        GeneralizationTree.AppTree app2 = new GeneralizationTree.AppTree("estudy", 3, 1,"estudy" , null);
        appTrees.add(app1);
        appTrees.add(app2);
        app.setApps(appTrees);


        // 定义exception
        GeneralizationTree.ExceptionTree throwable = new GeneralizationTree.ExceptionTree();
        throwable.setException("错误");
        throwable.setId(1);
        throwable.setParentId(1);

        List<GeneralizationTree.ExceptionTree> exceptions = Lists.newArrayList();
        GeneralizationTree.ExceptionTree systemExe = new GeneralizationTree.ExceptionTree();
        List<GeneralizationTree.ExceptionTree> exceptionChild = Lists.newArrayList();
        GeneralizationTree.ExceptionTree exception1 = new GeneralizationTree.ExceptionTree();
        GeneralizationTree.ExceptionTree exception2 = new GeneralizationTree.ExceptionTree();
        exception1.setException("dubbo错误");
        exception1.setId(4);
        exception1.setParentId(2);
        exception2.setException("rocketMq错误");
        exception2.setId(5);
        exception2.setParentId(2);
        exceptionChild.add(exception1);
        exceptionChild.add(exception2);
        systemExe.setException("系统错误");
        systemExe.setId(2);
        systemExe.setParentId(1);
        systemExe.setExceptions(exceptionChild);
        exceptions.add(systemExe);
        throwable.setExceptions(exceptions);


        List<GeneralizationTree.ExceptionTree> dubboErrors = Lists.newArrayList();
        GeneralizationTree.ExceptionTree dubbo1 = new GeneralizationTree.ExceptionTree();
        GeneralizationTree.ExceptionTree dubbo2 = new GeneralizationTree.ExceptionTree();
        dubbo1.setException("RpcException");
        dubbo1.setId(6);
        dubbo1.setParentId(4);
        dubbo2.setException("RemotingException");
        dubbo2.setId(7);
        dubbo2.setParentId(4);
        dubboErrors.add(dubbo1);
        dubboErrors.add(dubbo2);
        exception1.setExceptions(dubboErrors);

        List<GeneralizationTree.ExceptionTree> rocketErrors = Lists.newArrayList();
        GeneralizationTree.ExceptionTree rocket1 = new GeneralizationTree.ExceptionTree();
        rocket1.setException("RemotingTimeoutException");
        rocket1.setId(8);
        rocket1.setParentId(5);
        rocketErrors.add(rocket1);
        exception2.setExceptions(rocketErrors);




        GeneralizationTree.ExceptionTree otherExe = new GeneralizationTree.ExceptionTree();
        List<GeneralizationTree.ExceptionTree> otherChild = Lists.newArrayList();
        GeneralizationTree.ExceptionTree other1 = new GeneralizationTree.ExceptionTree();
        other1.setException("NPE");
        other1.setId(9);
        other1.setParentId(3);
        otherChild.add(other1);
        otherExe.setException("其他错误");
        otherExe.setId(3);
        otherExe.setParentId(1);
        otherExe.setExceptions(otherChild);
        exceptions.add(otherExe);
        throwable.setExceptions(exceptions);


        generalization.setApps(app);
        generalization.setExceptions(throwable);
        generalization.setHosts(hostAll);

        System.out.println("泛化层次结构：" + JSONObject.toJSONString(generalization));

        return generalization;

    }

    private static List<AlertDto> errorSource(){

        List<AlertDto> dtos = Lists.newArrayList();
        AlertDto alertDto1 = new AlertDto("liveservice-123-1", "RemotingTimeoutException", "send heart beat to broker exception - \n" +
                "com.alibaba.rocketmq.remoting.exception.RemotingTimeoutException: wait response on the channel <192.168.200.27:10911> timeout, 3000(ms)");
        AlertDto alertDto2 = new AlertDto("liveservice-123-2", "RemotingException", "org.apache.dubbo.remoting.RemotingException: client(url: dubbo://192.168.200.78:31765/la.kaike.nkobase.live.PushStreamProvider?anyhost=true&application=liveservice&bean.name=PushStreamProvider&check=false&cluster=failsafe&codec=dubbo&export=true&generic=true&heartbeat=60000&init=false&interface=la.kaike.nkobase.live.PushStreamProvider&ip=172.20.5.21&loadbalance=random&logger=slf4j&message_size=4&methods=Check%2CClose%2COpen&module=dubbogo+user-info+server&name=nkobase&organization=estudy.cn&pid=1&reference.filter=consumerFilter&register.ip=172.19.0.61&registry.role=3&release=dubbo-golang-1.5.5&remote.application=nkobase&retries=0&send.reconnect=true&service.filter=echo%2Ctoken%2Caccesslog%2Ctps%2Cgeneric_service%2Cexecute%2Cpshutdown&side=consumer&ssl-enabled=false&sticky=false&threads=500&timeout=10000&timestamp=1617953427&warmup=100) failed to connect to server /192.168.200.78:31765 client-side timeout 3000ms (elapsed: 3000ms) from netty client 172.19.0.61 using dubbo version 2.7.7");
        AlertDto alertDto3 = new AlertDto("liveservice-123-2", "RemotingException", "org.apache.dubbo.remoting.RemotingException: client(url: dubbo://192.168.200.78:31765/la.kaike.nkobase.live.PushStreamProvider?anyhost=true&application=liveservice&bean.name=PushStreamProvider&check=false&cluster=failsafe&codec=dubbo&export=true&generic=true&heartbeat=60000&init=false&interface=la.kaike.nkobase.live.PushStreamProvider&ip=172.20.5.21&loadbalance=random&logger=slf4j&message_size=4&methods=Check%2CClose%2COpen&module=dubbogo+user-info+server&name=nkobase&organization=estudy.cn&pid=1&reference.filter=consumerFilter&register.ip=172.19.0.61&registry.role=3&release=dubbo-golang-1.5.5&remote.application=nkobase&retries=0&send.reconnect=true&service.filter=echo%2Ctoken%2Caccesslog%2Ctps%2Cgeneric_service%2Cexecute%2Cpshutdown&side=consumer&ssl-enabled=false&sticky=false&threads=500&timeout=10000&timestamp=1617953427&warmup=100) failed to connect to server /192.168.200.78:31765 client-side timeout 3000ms (elapsed: 3000ms) from netty client 172.19.0.61 using dubbo version 2.7.7");
        AlertDto alertDto4 = new AlertDto("liveservice-123-1", "RemotingException", "org.apache.dubbo.remoting.RemotingException: client(url: dubbo://192.168.200.78:31765/la.kaike.nkobase.live.PushStreamProvider?anyhost=true&application=liveservice&bean.name=PushStreamProvider&check=false&cluster=failsafe&codec=dubbo&export=true&generic=true&heartbeat=60000&init=false&interface=la.kaike.nkobase.live.PushStreamProvider&ip=172.20.5.21&loadbalance=random&logger=slf4j&message_size=4&methods=Check%2CClose%2COpen&module=dubbogo+user-info+server&name=nkobase&organization=estudy.cn&pid=1&reference.filter=consumerFilter&register.ip=172.19.0.61&registry.role=3&release=dubbo-golang-1.5.5&remote.application=nkobase&retries=0&send.reconnect=true&service.filter=echo%2Ctoken%2Caccesslog%2Ctps%2Cgeneric_service%2Cexecute%2Cpshutdown&side=consumer&ssl-enabled=false&sticky=false&threads=500&timeout=10000&timestamp=1617953427&warmup=100) failed to connect to server /192.168.200.78:31765 client-side timeout 3000ms (elapsed: 3000ms) from netty client 172.19.0.61 using dubbo version 2.7.7");
        AlertDto alertDto5 = new AlertDto("liveservice-123-1", "RpcException", "org.apache.dubbo.remoting.RemotingException: client(url: dubbo://192.168.200.78:31765/la.kaike.nkobase.live.PushStreamProvider?anyhost=true&application=liveservice&bean.name=PushStreamProvider&check=false&cluster=failsafe&codec=dubbo&export=true&generic=true&heartbeat=60000&init=false&interface=la.kaike.nkobase.live.PushStreamProvider&ip=172.20.5.21&loadbalance=random&logger=slf4j&message_size=4&methods=Check%2CClose%2COpen&module=dubbogo+user-info+server&name=nkobase&organization=estudy.cn&pid=1&reference.filter=consumerFilter&register.ip=172.19.0.61&registry.role=3&release=dubbo-golang-1.5.5&remote.application=nkobase&retries=0&send.reconnect=true&service.filter=echo%2Ctoken%2Caccesslog%2Ctps%2Cgeneric_service%2Cexecute%2Cpshutdown&side=consumer&ssl-enabled=false&sticky=false&threads=500&timeout=10000&timestamp=1617953427&warmup=100) failed to connect to server /192.168.200.78:31765 client-side timeout 3000ms (elapsed: 3000ms) from netty client 172.19.0.61 using dubbo version 2.7.7");
        AlertDto alertDto6 = new AlertDto("liveservice-123-1", "RpcException", "org.apache.dubbo.remoting.RemotingException: client(url: dubbo://192.168.200.78:31765/la.kaike.nkobase.live.PushStreamProvider?anyhost=true&application=liveservice&bean.name=PushStreamProvider&check=false&cluster=failsafe&codec=dubbo&export=true&generic=true&heartbeat=60000&init=false&interface=la.kaike.nkobase.live.PushStreamProvider&ip=172.20.5.21&loadbalance=random&logger=slf4j&message_size=4&methods=Check%2CClose%2COpen&module=dubbogo+user-info+server&name=nkobase&organization=estudy.cn&pid=1&reference.filter=consumerFilter&register.ip=172.19.0.61&registry.role=3&release=dubbo-golang-1.5.5&remote.application=nkobase&retries=0&send.reconnect=true&service.filter=echo%2Ctoken%2Caccesslog%2Ctps%2Cgeneric_service%2Cexecute%2Cpshutdown&side=consumer&ssl-enabled=false&sticky=false&threads=500&timeout=10000&timestamp=1617953427&warmup=100) failed to connect to server /192.168.200.78:31765 client-side timeout 3000ms (elapsed: 3000ms) from netty client 172.19.0.61 using dubbo version 2.7.7");
        AlertDto alertDto7 = new AlertDto("liveservice-123-1", "RpcException", "org.apache.dubbo.remoting.RemotingException: client(url: dubbo://192.168.200.78:31765/la.kaike.nkobase.live.PushStreamProvider?anyhost=true&application=liveservice&bean.name=PushStreamProvider&check=false&cluster=failsafe&codec=dubbo&export=true&generic=true&heartbeat=60000&init=false&interface=la.kaike.nkobase.live.PushStreamProvider&ip=172.20.5.21&loadbalance=random&logger=slf4j&message_size=4&methods=Check%2CClose%2COpen&module=dubbogo+user-info+server&name=nkobase&organization=estudy.cn&pid=1&reference.filter=consumerFilter&register.ip=172.19.0.61&registry.role=3&release=dubbo-golang-1.5.5&remote.application=nkobase&retries=0&send.reconnect=true&service.filter=echo%2Ctoken%2Caccesslog%2Ctps%2Cgeneric_service%2Cexecute%2Cpshutdown&side=consumer&ssl-enabled=false&sticky=false&threads=500&timeout=10000&timestamp=1617953427&warmup=100) failed to connect to server /192.168.200.78:31765 client-side timeout 3000ms (elapsed: 3000ms) from netty client 172.19.0.61 using dubbo version 2.7.7");
        AlertDto alertDto8 = new AlertDto("liveservice-123-1", "RpcException", "org.apache.dubbo.remoting.RemotingException: client(url: dubbo://192.168.200.78:31765/la.kaike.nkobase.live.PushStreamProvider?anyhost=true&application=liveservice&bean.name=PushStreamProvider&check=false&cluster=failsafe&codec=dubbo&export=true&generic=true&heartbeat=60000&init=false&interface=la.kaike.nkobase.live.PushStreamProvider&ip=172.20.5.21&loadbalance=random&logger=slf4j&message_size=4&methods=Check%2CClose%2COpen&module=dubbogo+user-info+server&name=nkobase&organization=estudy.cn&pid=1&reference.filter=consumerFilter&register.ip=172.19.0.61&registry.role=3&release=dubbo-golang-1.5.5&remote.application=nkobase&retries=0&send.reconnect=true&service.filter=echo%2Ctoken%2Caccesslog%2Ctps%2Cgeneric_service%2Cexecute%2Cpshutdown&side=consumer&ssl-enabled=false&sticky=false&threads=500&timeout=10000&timestamp=1617953427&warmup=100) failed to connect to server /192.168.200.78:31765 client-side timeout 3000ms (elapsed: 3000ms) from netty client 172.19.0.61 using dubbo version 2.7.7");
        AlertDto alertDto9 = new AlertDto("estudy-123-1", "RpcException", "org.apache.dubbo.rpc.RpcException: Failed to invoke the method getLiveCourseById in the service cn.estudy.course.service.facade.inner.LiveCoursePrivateFacade. Tried 1 times of the providers [10.230.1.189:20880] (1/11) from the registry 192.168.200.17:2181 on the consumer 172.20.15.72 using the dubbo version 2.7.7. Last error is: Failed to invoke remote method: getLiveCourseById, provider: dubbo://10.230.1.189:20880/cn.estudy.course.service.facade.inner.LiveCoursePrivateFacade?anyhost=true&application=liveservice&check=false&default.delay=-1&default.dubbo.router.group=&default.retries=0&default.service.filter=providerFilter&default.timeout=10000&delay=-1&dispatcher=message&dubbo=2.8.7&generic=false&init=false&interface=cn.estudy.course.service.facade.inner.LiveCoursePrivateFacade&loadbalance=random&logger=slf4j&methods=listCourseIdBySubjectId,queryForChangeCourse,getCourseTitleMap,getCourseTitle,isSendPointLesson,getStartNotEndCourseList,getAllLiveCourseOnSale,getLiveCourseSeriesBaseById,getLiveCourseById,getMemberStartNotEndCourseList,getLastCourseDTOByDate,getCourseListByProductIds,getBySuperGroup,updateLiveCourseSuperGroup,getCourseMapByProductIds,getVersionIdByCourseId,getCourseTermSubjectListVO,queryCourseIdByProductId,getTermIdsByCourseIdAndType,selectLiveLessonInfoByMemberId,getActivityTermByMemberIdAndCourseIdCache,selectForExamResult,listIdByTeacherId,getCourseIdByCourseEndTime,getNotOverCourseListForAll,getCourseIdByEndTimeCache,queryPeriodCourseIdListByCache,selectTeacherInfo,getByCache&pid=1&reference.filter=consumerFilter&register.ip=172.20.15.72&remote.application=estudy&retries=0&revision=1.0.0.20210315-SNAPSHOT&serialization=hessian2&side=consumer&sticky=false&threads=500&timeout=60000&timestamp=1618291559353, cause: client(url: dubbo://10.230.1.189:20880/cn.estudy.message.service.facade.PhoneNotifyProxyServicePrivateFacade?_client_memo=referencecounthandler.replacewithlazyclient&anyhost=true&application=liveservice&check=false&codec=dubbo&connect.lazy.initial.state=true&default.delay=-1&default.dubbo.router.group=&default.retries=0&default.service.filter=providerFilter&default.timeout=10000&delay=-1&dispatcher=message&dubbo=2.8.7&generic=false&heartbeat=60000&init=false&interface=cn.estudy.message.service.facade.PhoneNotifyProxyServicePrivateFacade&lazyclient_request_with_warning=true&loadbalance=random&logger=slf4j&methods=simpleDial,getUserPhoneNotifyStatus,getAlreadyDialUserPhoneList,countDialdingRecord,dial&pid=1&reconnect=false&reference.filter=consumerFilter&register.ip=172.20.15.72&remote.application=estudy&retries=0&revision=1.0.0.20210315-SNAPSHOT&send.reconnect=true&serialization=hessian2&side=consumer&sticky=false&threads=500&timeout=60000&timestamp=1618193739298&warning=true) failed to connect to server /10.230.1.189:20880 client-side timeout 3000ms (elapsed: 3005ms) from netty client 172.20.15.72 using dubbo version 2.7.7");
        AlertDto alertDto10 = new AlertDto("estudy-123-1", "RpcException", "org.apache.dubbo.rpc.RpcException: Failed to invoke the method getLiveCourseById in the service cn.estudy.course.service.facade.inner.LiveCoursePrivateFacade. Tried 1 times of the providers [10.230.1.189:20880] (1/11) from the registry 192.168.200.17:2181 on the consumer 172.20.15.72 using the dubbo version 2.7.7. Last error is: Failed to invoke remote method: getLiveCourseById, provider: dubbo://10.230.1.189:20880/cn.estudy.course.service.facade.inner.LiveCoursePrivateFacade?anyhost=true&application=liveservice&check=false&default.delay=-1&default.dubbo.router.group=&default.retries=0&default.service.filter=providerFilter&default.timeout=10000&delay=-1&dispatcher=message&dubbo=2.8.7&generic=false&init=false&interface=cn.estudy.course.service.facade.inner.LiveCoursePrivateFacade&loadbalance=random&logger=slf4j&methods=listCourseIdBySubjectId,queryForChangeCourse,getCourseTitleMap,getCourseTitle,isSendPointLesson,getStartNotEndCourseList,getAllLiveCourseOnSale,getLiveCourseSeriesBaseById,getLiveCourseById,getMemberStartNotEndCourseList,getLastCourseDTOByDate,getCourseListByProductIds,getBySuperGroup,updateLiveCourseSuperGroup,getCourseMapByProductIds,getVersionIdByCourseId,getCourseTermSubjectListVO,queryCourseIdByProductId,getTermIdsByCourseIdAndType,selectLiveLessonInfoByMemberId,getActivityTermByMemberIdAndCourseIdCache,selectForExamResult,listIdByTeacherId,getCourseIdByCourseEndTime,getNotOverCourseListForAll,getCourseIdByEndTimeCache,queryPeriodCourseIdListByCache,selectTeacherInfo,getByCache&pid=1&reference.filter=consumerFilter&register.ip=172.20.15.72&remote.application=estudy&retries=0&revision=1.0.0.20210315-SNAPSHOT&serialization=hessian2&side=consumer&sticky=false&threads=500&timeout=60000&timestamp=1618291559353, cause: client(url: dubbo://10.230.1.189:20880/cn.estudy.message.service.facade.PhoneNotifyProxyServicePrivateFacade?_client_memo=referencecounthandler.replacewithlazyclient&anyhost=true&application=liveservice&check=false&codec=dubbo&connect.lazy.initial.state=true&default.delay=-1&default.dubbo.router.group=&default.retries=0&default.service.filter=providerFilter&default.timeout=10000&delay=-1&dispatcher=message&dubbo=2.8.7&generic=false&heartbeat=60000&init=false&interface=cn.estudy.message.service.facade.PhoneNotifyProxyServicePrivateFacade&lazyclient_request_with_warning=true&loadbalance=random&logger=slf4j&methods=simpleDial,getUserPhoneNotifyStatus,getAlreadyDialUserPhoneList,countDialdingRecord,dial&pid=1&reconnect=false&reference.filter=consumerFilter&register.ip=172.20.15.72&remote.application=estudy&retries=0&revision=1.0.0.20210315-SNAPSHOT&send.reconnect=true&serialization=hessian2&side=consumer&sticky=false&threads=500&timeout=60000&timestamp=1618193739298&warning=true) failed to connect to server /10.230.1.189:20880 client-side timeout 3000ms (elapsed: 3005ms) from netty client 172.20.15.72 using dubbo version 2.7.7");

        dtos.add(alertDto1);
        dtos.add(alertDto2);
        dtos.add(alertDto3);
        dtos.add(alertDto4);
        dtos.add(alertDto5);
        dtos.add(alertDto6);
        dtos.add(alertDto7);
        dtos.add(alertDto8);
        dtos.add(alertDto9);
        dtos.add(alertDto10);

        System.out.println("告警源数据：size:" + dtos.size() + "\n结果：" + dtos);
        return dtos;
    }

    /**
     * 告警聚类 : 依据： 重写equals方法。符合的，就聚类。
     *
     * 必须避免： 不同属性的不同特征值   不能合并。
     *
     * @param alertDtos 告警的数据
     * @return 聚类后的告警
     */
    private static List<AlertDto> mergeSource(List<AlertDto> alertDtos){
        List<AlertDto> dtos = Lists.newArrayList();

        for(AlertDto dto : alertDtos){
            if( ! CollectionUtils.isEmpty(dtos)){
                if( dtos.stream().noneMatch(d -> d.equals(dto))){
                    dtos.add(dto);
                }

            }else{
                dtos.add(dto);
            }
        }
        System.out.println("第1次告警聚类,size:" + dtos.size() + "\n结果：" + dtos);
        return dtos;
    }

    /**
     * 聚合最小不相似度的属性的记录
     * @param alertDtos 告警记录
     * @param minParentNodes 最小不相似度的集合
     */
    private static Integer mergeSourceV2(List<AlertDto> alertDtos , List<GeneralizationTree.MinParentNode> minParentNodes){

        final long[] count = {0L};
        List<AlertDto> deleteDtos  =Lists.newArrayList();

        // 用于存放第二个异常： 指的是blog中的 a`
        Set<String> exceptions = new HashSet<>();

        // 只用于不重复统计相同的异常
        Set<String> exceptionsAll = new HashSet<>();

        minParentNodes.forEach(n -> {
            List<GeneralizationTree.ExceptionTree> exceptionChildNodes = n.getExceptionChildNodes();
            String e1 = exceptionChildNodes.get(0).getException();
            String e2 = exceptionChildNodes.get(1).getException();
            if(e1.equals(e2)){
                if( ! exceptionsAll.contains(e1)){
                    count[0] = alertDtos.stream().filter(a -> a.getException().equals(e1)).count() + count[0];
                }
            }else{
                long count1 = 0L;
                long count2 = 0L;
                if( !exceptionsAll.contains(e1)){
                    count1 = alertDtos.stream().filter(a -> a.getException().equals(e1)).count();
                }
                if( !exceptionsAll.contains(e2)){
                    count2 = alertDtos.stream().filter(a -> a.getException().equals(e2)).count();
                }
                count[0] = count1 + count2 + count[0];
            }
            exceptions.add(e2);
            exceptionsAll.add(e1);
            exceptionsAll.add(e2);
        });

        alertDtos.stream().filter(d -> exceptions.contains(d.getException())).forEach(deleteDtos::add);

        alertDtos.removeAll(deleteDtos);
        System.out.println("第2次告警聚类：size:" + alertDtos.size() + "\n结果：" + alertDtos);
        return (int)count[0];
    }

    private static int getMinSize(List<AlertDto> dtos){
        return (int)(dtos.size() * MIN_SIZE_FACTORY * (1 - ROBUST_FACTORY));
    }

    /**
     * 启发性算法：
     * 取出最小的覆盖的属性，用于泛化告警并聚类
     *
     * @param dtos 第一次合并后的告警日志的数量
     * @return 返回需要泛化的每个属性中 覆盖告警日志的数量的最少的那个属性
     */
    private static String getMinFiled(List<AlertDto> dtos){

        List<String> hostNames = Lists.newArrayList();
        List<String> exceptions = Lists.newArrayList();
        getHosts(generalizationTree.getHosts(), hostNames);

        getExceptions(generalizationTree.getExceptions(), exceptions);

        // TODO demo认为泛化层次结构 完整：
        // 对每个属性的每个值 【值 在泛化层次结构中】，算出覆盖告警的数量
        List<Long> hostCount = Lists.newArrayList();
        List<Long> exceptionCount = Lists.newArrayList();
        dtos.stream()
                .filter(dto -> hostNames.contains(dto.getHost()))
                .forEach(dto -> {
                    long count = dtos.stream().filter(d -> dto.getHost().equals(d.getHost())).count();
                    hostCount.add(count);
                });
        dtos.stream()
                .filter(dto -> exceptions.contains(dto.getException()))
                .forEach(dto -> {
                    long count = dtos.stream().filter(d -> dto.getException().equals(d.getException())).count();
                    exceptionCount.add(count);
                });

        // 相同属性之间：求最小的覆盖数量
        Long hostMin = getMin(hostCount);
        Long exceptionMin = getMin(exceptionCount);


        // 不同属性之间：取最小覆盖数量，进行泛化。
        return hostMin < exceptionMin ? "host" : "exception";
    }

    /**
     *
     * @param datas 各个属性的值的覆盖告警日志的数量
     * @return  快速取Long数组中的最小值
     */
    private static Long getMin(List<Long> datas){
        if(CollectionUtils.isEmpty(datas)) {
            return 0L;
        }

        Long min = Long.MAX_VALUE;
        for (Long d : datas) {
            if(d < min) {
                min = d;
            }
        }
        return min;
    }

    /**
     * 递归调用， 获取指定参数
     * @param exception 单个泛化层次结构
     * @param exceptions 放置 获取的指定参数的容器
     */
    private static void getExceptions(GeneralizationTree.ExceptionTree exception , List<String> exceptions){

        String exe = exception.getException();
        exceptions.add(exe);

        if( ! CollectionUtils.isEmpty(exception.getExceptions())) {
            exception.getExceptions().forEach(e -> {
                getExceptions(e, exceptions);
            });
        }
    }

    private static void getHosts(GeneralizationTree.HostTree host , List<String> hosts){

        hosts.add(host.getHostName());

        if( ! CollectionUtils.isEmpty(host.getHosts())) {
            host.getHosts().forEach(h -> {
                getHosts(h , hosts);
            });
        }
    }

    /**
     * 将所有filed的属性值， 替换为对应的父类属性
     * @param filed 泛化属性
     * @param alertDtos 告警数据
     */
    private static void replaceFiled(String filed , List<AlertDto> alertDtos) {

        if("exception".equals(filed)){
            alertDtos.forEach(d -> {
                // 获取当前节点
                getNode(d.getException(), generalizationTree.getExceptions())
                        // 获取当前节点的父节点
                        .flatMap(node -> getParentNode(node, generalizationTree.getExceptions()))
                        .ifPresent(parentNode -> {
                            d.setException(parentNode.getException());
                });
            });
        }

    }


    /**
     * 根据两个当前节点的parentId找到相同的parentId为止， 再用parentId换取父节点的值
     * @param t1 当前节点： 异常树1
     * @param t2 当前节点： 异常树2
     */
    private static void getMinParentNode(Optional<GeneralizationTree.ExceptionTree> t1,
                                                                     Optional<GeneralizationTree.ExceptionTree> t2 ,
                                                                     GeneralizationTree.MinParentNode minParentNode) {

        List<GeneralizationTree.ExceptionTree> exceptionChildNodes = minParentNode.getExceptionChildNodes();
        GeneralizationTree.ExceptionTree tree1 = t1.orElse(null);
        GeneralizationTree.ExceptionTree tree2 = t2.orElse(null);

        if (ObjectUtils.isEmpty(tree1) || ObjectUtils.isEmpty(tree2)) {
            return;
        }
        // 维护当前运行的所有节点
        exceptionChildNodes.add(tree1);
        exceptionChildNodes.add(tree2);

        if ( ! ObjectUtils.isEmpty(tree1) && ! ObjectUtils.isEmpty(tree2)) {
            if (tree1.getParentId().equals(tree2.getParentId())) {
                minParentNode.setDissimilarity(minParentNode.getDissimilarity() + 1);
                minParentNode.setParentNode(getParentNode(tree1, generalizationTree.getExceptions()).orElse(null));
                return;
            }
        }

        if (ObjectUtils.isEmpty(tree1) && !ObjectUtils.isEmpty(tree2)) {
            minParentNode.setDissimilarity(minParentNode.getDissimilarity() + 1);
            getMinParentNode(Optional.empty(), getParentNode(tree2, generalizationTree.getExceptions()), minParentNode);
            return;
        }
        if (!ObjectUtils.isEmpty(tree1) && ObjectUtils.isEmpty(tree2)) {
            minParentNode.setDissimilarity(minParentNode.getDissimilarity() + 1);
            getMinParentNode(getParentNode(tree1, generalizationTree.getExceptions()), Optional.empty(), minParentNode);
            return;
        }


        Optional<GeneralizationTree.ExceptionTree> parentNode1 = getParentNode(tree1, generalizationTree.getExceptions());
        Optional<GeneralizationTree.ExceptionTree> parentNode2 = getParentNode(tree2, generalizationTree.getExceptions());

        if (parentNode1.isPresent() && parentNode2.isPresent()) {
            // 获取tree1当前所有子集的parentId
            List<Integer> parentIds1 = getChildrenParentIds(parentNode1.get(), Lists.newArrayList());
            if (parentIds1.contains(tree2.getParentId()) && !parentNode1.get().getId().equals(parentNode2.get().getId())) {
                minParentNode.setDissimilarity(minParentNode.getDissimilarity() + 1);
                getMinParentNode(Optional.empty(), parentNode2, minParentNode);
                return;
            }

            // 获取tree2当前所有子集的parentId
            List<Integer> parentIds2 = getChildrenParentIds(parentNode2.get(), Lists.newArrayList());
            if (parentIds2.contains(tree1.getParentId()) && !parentNode1.get().getId().equals(parentNode2.get().getId())) {
                minParentNode.setDissimilarity(minParentNode.getDissimilarity() + 1);
                getMinParentNode(parentNode1, Optional.empty(), minParentNode);
                return;
            }

            // 当前两个节点的id相等 且不为根节点 || 任意一方的父节点的所有子集的parentId 都不包含另一节点的父节点
            if (!parentNode1.get().getId().equals(parentNode1.get().getParentId()) && !parentNode2.get().getId().equals(parentNode2.get().getParentId())) {
                getMinParentNode(parentNode1, parentNode2, minParentNode);
                minParentNode.setDissimilarity(minParentNode.getDissimilarity() + 2);
            }

        }
    }

    /**
     * 先序遍历
     */
    private static Optional<GeneralizationTree.ExceptionTree> getParentNode(GeneralizationTree.ExceptionTree paramException, GeneralizationTree.ExceptionTree exceptionTree){
        if(exceptionTree.getId().equals(paramException.getParentId())){
            return Optional.of(exceptionTree);
        }

        if( ! CollectionUtils.isEmpty(exceptionTree.getExceptions())){
            for (GeneralizationTree.ExceptionTree tree : exceptionTree.getExceptions()){
                Optional<GeneralizationTree.ExceptionTree> parentNode = getParentNode(paramException, tree);
                if(parentNode.isPresent()){
                    return parentNode;
                }
            }
        }
        return Optional.empty();
    }

    /**
     * 先序遍历
     *
     * 根据异常名返回节点, 从顶层开始找
     *
     * @param exception 异常名
     * @return 节点
     */
    private static Optional<GeneralizationTree.ExceptionTree> getNode(String exception, GeneralizationTree.ExceptionTree exceptionTree){

        if(exception.equals(exceptionTree.getException())){
            return Optional.of(exceptionTree);
        }

        if( ! CollectionUtils.isEmpty(exceptionTree.getExceptions())){
            for (GeneralizationTree.ExceptionTree e : exceptionTree.getExceptions()) {
                Optional<GeneralizationTree.ExceptionTree> node = getNode(exception, e);
                if(null != node && node.isPresent()){
                    return node;
                }
            }
        }

        return Optional.empty();

    }


    /**
     *
     * @param alertDtos 告警记录
     * @return 最小不相似度的所有属性值
     */
    private static List<GeneralizationTree.MinParentNode> getDissimilarityValue(List<AlertDto> alertDtos){
        List<GeneralizationTree.MinParentNode> minParentNodes = Lists.newArrayList();
        for (int i = 0; i< alertDtos.size(); i ++) {
            String exception1 = alertDtos.get(i).getException();

            for (int j = i + 1 ; j < alertDtos.size() ; j ++) {
                GeneralizationTree.MinParentNode minParentNode = new GeneralizationTree.MinParentNode();
                // 找个下一条记录的同一个属性的值
                String exception2 = alertDtos.get(j).getException();

                // 获取两个相同属性的值的不相似度： 通过找父节点的方式：循环n次，那么不相似度为n
                getMinParentNode(Objects.requireNonNull(getNode(exception1, generalizationTree.getExceptions())),
                        Objects.requireNonNull(getNode(exception2, generalizationTree.getExceptions())),
                        minParentNode);
                minParentNodes.add(minParentNode);

            }

        }


        // 对不相似度排序
        minParentNodes = minParentNodes.stream().sorted(new Comparator<GeneralizationTree.MinParentNode>() {
            @Override
            // 升序排序
            public int compare(GeneralizationTree.MinParentNode o1, GeneralizationTree.MinParentNode o2) {
                return o2.getDissimilarity().compareTo(o1.getDissimilarity());
            }
        }).collect(Collectors.toList());

        GeneralizationTree.MinParentNode minParentNode = minParentNodes.get(0);

        //  获取所有相同最小不相似度的记录
        return minParentNodes.stream().filter(n -> minParentNode.getDissimilarity().equals(n.getDissimilarity())).collect(Collectors.toList());
    }

    /**
     * 先序遍历
     * 获取当前节点下 所有子集的parentId
     * @param exceptionTree 异常树
     * @param parentIds parentIds
     */
    private static List<Integer> getChildrenParentIds(GeneralizationTree.ExceptionTree exceptionTree, List<Integer> parentIds){

        if( ! CollectionUtils.isEmpty(exceptionTree.getExceptions())) {
            List<Integer> childParentIds = exceptionTree.getExceptions().stream().map(GeneralizationTree.ExceptionTree::getParentId).distinct().collect(Collectors.toList());
            parentIds.addAll(childParentIds);
            exceptionTree.getExceptions().forEach(e -> getChildrenParentIds(e, parentIds));
        }

        return parentIds;
    }

}
