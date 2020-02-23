package link.yangxin.rpc.client;

import link.yangxin.rpc.codec.Decoder;
import link.yangxin.rpc.codec.Encoder;
import link.yangxin.rpc.codec.JSONDecoder;
import link.yangxin.rpc.codec.JSONEncoder;
import link.yangxin.rpc.proto.Peer;
import link.yangxin.rpc.transport.TransportClient;
import link.yangxin.rpc.transport.impl.HttpTransportClient;
import link.yangxin.rpc.transport.impl.HttpTransportServer;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangxin
 * @date 2020/2/23
 */
@Data
public class RpcClientConfig {

    private Class<? extends TransportClient> TransportClientClass = HttpTransportClient.class;

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private Class<? extends TransportSelector> transportSelectorClass = RandomTransportSelector.class;

    private int connentCount = 1;

    List<Peer> servers = new ArrayList(Arrays.asList(new Peer("127.0.0.1", 3000)));


}