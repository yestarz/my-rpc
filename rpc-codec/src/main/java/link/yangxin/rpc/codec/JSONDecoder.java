package link.yangxin.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * 基于json的反序列化事先
 *
 * @author yangxin
 * @date 2020/2/15
 */
public class JSONDecoder implements Decoder {

    @Override
    public <T> T decode(byte[] bytes, Class<T> tClass) {
        return JSON.parseObject(bytes, tClass);
    }
}