package link.yangxin.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标识网络传输的一个端点
 * @author yangxin
 * @date 2020/2/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peer {

    private String host;

    private int port;

}