package link.yangxin.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求的handler
 * @author yangxin
 * @date 2020/2/15
 */
public interface RequestHandler {

    void onRequest(InputStream receive, OutputStream toResp);

}
