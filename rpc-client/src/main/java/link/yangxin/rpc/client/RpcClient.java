package link.yangxin.rpc.client;

import link.yangxin.rpc.codec.Decoder;
import link.yangxin.rpc.codec.Encoder;
import link.yangxin.rpc.codec.JSONDecoder;
import link.yangxin.rpc.codec.JSONEncoder;
import link.yangxin.rpc.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @author yangxin
 * @date 2020/2/23
 */
public class RpcClient {

    private RpcClientConfig config;

    private Encoder encoder;

    private Decoder decoder;

    private TransportSelector transportSelector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        encoder = ReflectionUtils.newInstance(JSONEncoder.class);
        decoder = ReflectionUtils.newInstance(JSONDecoder.class);
        transportSelector = ReflectionUtils.newInstance(RandomTransportSelector.class);

        this.transportSelector.init(this.config.getServers(), this.config.getConnentCount(), this.config.getTransportClientClass());
    }

    public <T> T getProxy(Class<T> tClass){
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{tClass},
                new RemoteInvoker(tClass, encoder, decoder, transportSelector)
        );
    }

}