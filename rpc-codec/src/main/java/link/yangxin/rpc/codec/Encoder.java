package link.yangxin.rpc.codec;

/**
 * 序列化
 * @author yangxin
 * @date 2020/2/15
 */
public interface Encoder {

    /**
     * 序列化
     * @param object
     * @return
     */
    byte[] encode(Object object);

}
