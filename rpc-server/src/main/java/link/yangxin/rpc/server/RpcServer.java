package link.yangxin.rpc.server;

import link.yangxin.rpc.codec.Decoder;
import link.yangxin.rpc.codec.Encoder;
import link.yangxin.rpc.common.utils.ReflectionUtils;
import link.yangxin.rpc.proto.Request;
import link.yangxin.rpc.proto.Response;
import link.yangxin.rpc.transport.RequestHandler;
import link.yangxin.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author yangxin
 * @date 2020/2/23
 */
@Slf4j
public class RpcServer {

    private RpcServerConfig config;

    private TransportServer net;

    private Encoder encoder;

    private Decoder decoder;

    private ServiceManager serviceManager;

    private ServiceInvoker serviceInvoker;

    public RpcServer() {
        this.config = new RpcServerConfig();

        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), requestHandler);

        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();

    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        this.serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.close();

    }


    private RequestHandler requestHandler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream toResp) {
            Response response = new Response();
            try {
                byte[] bytes = IOUtils.readFully(receive, receive.available());
                Request request = decoder.decode(bytes, Request.class);
                log.info("=== get request :{} === ", request);

                ServiceInstance serviceInstance = serviceManager.lookup(request);
                Object data = serviceInvoker.invoke(serviceInstance, request);
                response.setData(data);

            } catch (Exception e) {
                log.error(e.getMessage(), e);
                response.setCode(1);
                response.setMessage(e.getMessage());
            } finally {
                byte[] out = encoder.encode(response);
                log.info("==== response to client === ");
                try {
                    toResp.write(out);
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }

            }
        }
    };
}