package link.yangxin.rpc.server;

import link.yangxin.rpc.common.utils.ReflectionUtils;
import link.yangxin.rpc.proto.Request;
import link.yangxin.rpc.proto.ServiceDescriptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @author yangxin
 * @date 2020/2/15
 */
public class ServiceManagerTest {

    ServiceManager serviceManager;

    @Before
    public void init(){
        this.serviceManager = new ServiceManager();
        this.register();
    }

    @Test
    public void register() {
        TestInterface test = new TestInterfaceImpl();
        serviceManager.register(TestInterface.class, test);
    }

    @Test
    public void lookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(TestInterface.class, method);

        Request request = new Request();
        request.setService(serviceDescriptor);

        ServiceInstance serviceInstance = serviceManager.lookup(request);
        System.out.println(serviceInstance);
    }
}