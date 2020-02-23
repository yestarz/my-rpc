package link.yangxin.example;

import link.yangxin.rpc.client.RpcClient;

/**
 * @author yangxin
 * @date 2020/2/23
 */
public class Client {

    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        CalcService calcService = rpcClient.getProxy(CalcService.class);

        int result1 = calcService.add(1, 2);
        int result2 = calcService.sub(10, 8);

        System.out.println(result1);
        System.out.println(result2);

    }

}