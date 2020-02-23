package link.yangxin.example;

import link.yangxin.rpc.server.RpcServer;

/**
 * @author yangxin
 * @date 2020/2/23
 */
public class Server {

    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(CalcService.class, new CalcServiceImpl());
        rpcServer.start();
    }

}