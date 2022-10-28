package packingclass;

import java.util.ArrayList;

public class packingclass2 {
    public static void main(String[] args) {
        //自动装箱：直接把int类型的整数赋值包装类
        //Integer in=1;就相当于Integer in = new Integer(1);

        Integer in = 1;
        in = in + 2;

        //自动拆箱：in是包装类，无法直接参与运算，可以自动转换为基本类型数据再计算
        //in+2 就相当于 in.intValue()+2;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);  //-->自动装箱list.add(new Integer(1));
        int a = list.get(0);  //-->自动拆箱list.get(0).intValue();
    }
}
