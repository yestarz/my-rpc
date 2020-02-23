package link.yangxin.rpc.server;

import link.yangxin.rpc.common.utils.ReflectionUtils;
import link.yangxin.rpc.proto.Request;
import link.yangxin.rpc.proto.ServiceDescriptor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理RPC暴露的服务
 * @author yangxin
 * @date 2020/2/15
 */
@Slf4j
public class ServiceManager {

    private Map<ServiceDescriptor,ServiceInstance> service;

    public ServiceManager() {
        this.service = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance sis = new ServiceInstance(bean, method);
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(interfaceClass, method);
            service.put(serviceDescriptor, sis);
            log.info("=== register service,class :{}, method:{} ====", interfaceClass.getName(), method.getName());
        }
    }

    public ServiceInstance lookup(Request request) {
        return service.get(request.getService());
    }
}