package link.yangxin.rpc.client;

import link.yangxin.rpc.proto.Peer;
import link.yangxin.rpc.transport.TransportClient;

import java.util.List;

/**
 * 表示选择哪个Server 去连接
 *
 * @author yangxin
 * @date 2020/2/23
 */
public interface TransportSelector {

    /**
     * 初始化selector
     * @param peers 可以连接的server断点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现的class
     */
    void init(List<Peer> peers,int count,Class<? extends TransportClient> clazz);

    /**
     * 选择一个transport与Server做交互
     *
     * @return 网络client
     */
    TransportClient select();

    /**
     * 释放用完的client
     */
    void release(TransportClient client);

    void close();

}