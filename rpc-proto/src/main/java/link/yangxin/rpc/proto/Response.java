package link.yangxin.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * @date 2020/2/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private int code = 0;

    private String message;

    private Object data;

}