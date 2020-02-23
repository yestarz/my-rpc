package link.yangxin.rpc.codec;

/**
 * 反序列化
 *
 * @author yangxin
 * @date 2020/2/15
 */
public interface Decoder {

    /**
     * 反序列化
     *
     * @param bytes
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T decode(byte[] bytes, Class<T> tClass);

}
