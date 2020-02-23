package link.yangxin.rpc.client;

import link.yangxin.rpc.common.utils.ReflectionUtils;
import link.yangxin.rpc.proto.Peer;
import link.yangxin.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yangxin
 * @date 2020/2/23
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector {

    // 连接好的client
    private List<TransportClient> clients ;

    public RandomTransportSelector() {
        clients = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);
        for (Peer peer : peers) {
            for (int i = 0; i < count; i++) {
                TransportClient transportClient = ReflectionUtils.newInstance(clazz);
                transportClient.connect(peer);
                clients.add(transportClient);
            }
            log.info("connect server :{}", peer);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int index = new Random().nextInt(clients.size());
        return clients.remove(index);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        for (TransportClient client : clients) {
            client.close();
        }
        clients.clear();
    }
}