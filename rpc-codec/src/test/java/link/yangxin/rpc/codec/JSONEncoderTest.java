package link.yangxin.rpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yangxin
 * @date 2020/2/15
 */
public class JSONEncoderTest {

    @Test
    public void encode() {
        Encoder encoder = new JSONEncoder();
        byte[] bytes = encoder.encode(new TestBean("yangxin", 26));

        Decoder decoder = new JSONDecoder();
        TestBean bean = decoder.decode(bytes, TestBean.class);
        assertEquals(26, bean.getAge());

    }
}