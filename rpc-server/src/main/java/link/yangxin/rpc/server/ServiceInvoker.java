package link.yangxin.rpc.server;

import link.yangxin.rpc.common.utils.ReflectionUtils;
import link.yangxin.rpc.proto.Request;

/**
 * 调用具体服务
 *
 * @author yangxin
 * @date 2020/2/23
 */
public class ServiceInvoker {

    public Object invoke(ServiceInstance serviceInstance, Request request) {
        return ReflectionUtils.invoke(serviceInstance.getTarget(), serviceInstance.getMethod(), request.getParameters());
    }

}