package link.yangxin.rpc.server;

import link.yangxin.rpc.codec.Decoder;
import link.yangxin.rpc.codec.Encoder;
import link.yangxin.rpc.codec.JSONDecoder;
import link.yangxin.rpc.codec.JSONEncoder;
import link.yangxin.rpc.transport.TransportServer;
import link.yangxin.rpc.transport.impl.HttpTransportServer;
import lombok.Data;

/**
 * server配置
 * @author yangxin
 * @date 2020/2/15
 */
@Data
public class RpcServerConfig {

    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    // 端口号
    private int port = 3000;

}