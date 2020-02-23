package link.yangxin.example;

/**
 * @author yangxin
 * @date 2020/2/23
 */
public class CalcServiceImpl implements CalcService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }
}