package link.yangxin.rpc.client;

import link.yangxin.rpc.codec.Decoder;
import link.yangxin.rpc.codec.Encoder;
import link.yangxin.rpc.proto.Request;
import link.yangxin.rpc.proto.Response;
import link.yangxin.rpc.proto.ServiceDescriptor;
import link.yangxin.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 調用远程服务的代理类
 *
 * @author yangxin
 * @date 2020/2/23
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {

    private Class clazz;

    private Encoder encoder;

    private Decoder decoder;

    private TransportSelector transportSelector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector transportSelector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.transportSelector = transportSelector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);
        Response response = invokeRemote(request);
        if (response == null || response.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote:" + response);
        }

        return response.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient client = null;
        Response response = null;

        try {
            client = transportSelector.select();
            byte[] out = encoder.encode(request);

            InputStream inputStream = client.write(new ByteArrayInputStream(out));
            byte[] bytes = IOUtils.readFully(inputStream, inputStream.available());

            response =  decoder.decode(bytes, Response.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response = new Response();
            response.setCode(1);
            response.setMessage(e.getMessage());
        } finally {
            if (client != null) {
                transportSelector.release(client);
            }
        }
        return response;
    }
}