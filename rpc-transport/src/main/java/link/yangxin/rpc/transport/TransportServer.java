package link.yangxin.rpc.transport;

/**
 * 1. 启动、监听端口
 * 2. 等待网络客户端连接服务端，收到请求做处理
 * 3. 关闭监听
 *
 * @author yangxin
 * @date 2020/2/15
 */
public interface TransportServer {

    /**
     * 初始化
     *
     * @param port
     * @param requestHandler
     */
    void init(int port, RequestHandler requestHandler);

    /**
     * 启动服务
     */
    void start();

    /**
     * 关闭监听
     */
    void close();

}