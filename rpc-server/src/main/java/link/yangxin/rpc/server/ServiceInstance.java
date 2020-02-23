package link.yangxin.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 服务的具体实例
 * @author yangxin
 * @date 2020/2/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInstance {

    private Object target;

    private Method method;

}