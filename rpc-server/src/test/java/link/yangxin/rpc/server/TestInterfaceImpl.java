package link.yangxin.rpc.server;

/**
 * @author yangxin
 * @date 2020/2/15
 */
public class TestInterfaceImpl implements TestInterface {

    @Override
    public void hello() {
        System.out.println("hello world!");
    }
}