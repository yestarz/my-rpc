package link.yangxin.rpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yangxin
 * @date 2020/2/15
 */
public class JSONDecoderTest {

    @Test
    public void decode() {
        Encoder encoder = new JSONEncoder();
        byte[] bytes = encoder.encode(new TestBean("yangxin", 26));
        System.out.println(bytes);
        assertNotNull(bytes);

    }
}