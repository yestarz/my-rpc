package link.yangxin.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 表示服务
 * @author yangxin
 * @date 2020/2/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {

    // 类名
    private String clazz;

    // 方法名
    private String method;

    // 返回值类型
    private String returnType;

    // 参数类型
    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.setClazz(clazz.getName());
        serviceDescriptor.setMethod(method.getName());
        serviceDescriptor.setReturnType(method.getReturnType().getName());

        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] parameters = new String[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = parameterTypes[0].getName();
        }
        serviceDescriptor.setParameterTypes(parameters);
        return serviceDescriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor) o;
        return that.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServiceDescriptor{");
        sb.append("clazz='").append(clazz).append('\'');
        sb.append(", method='").append(method).append('\'');
        sb.append(", returnType='").append(returnType).append('\'');
        sb.append(", parameterTypes=").append(Arrays.toString(parameterTypes));
        sb.append('}');
        return sb.toString();
    }
}