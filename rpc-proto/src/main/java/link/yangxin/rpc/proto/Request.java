package link.yangxin.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * @date 2020/2/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    private ServiceDescriptor service;

    private Object[] parameters;

}