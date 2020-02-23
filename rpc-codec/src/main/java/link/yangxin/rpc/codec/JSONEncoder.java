package link.yangxin.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * JSON序列化
 * @author yangxin
 * @date 2020/2/15
 */
public class JSONEncoder implements Encoder {
    @Override
    public byte[] encode(Object object) {
        return JSON.toJSONBytes(object);
    }
}