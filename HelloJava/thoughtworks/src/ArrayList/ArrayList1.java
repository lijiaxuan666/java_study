package ArrayList;

import java.util.ArrayList;

//数组的长度是不能发生变化的，但是ArrayList集合长度是可以发生变化的
//对于ArrayList来说，有一个<E>代表泛型
//泛型也就是装在集合当中的所有元素，全部都是统一的什么类型
//注意：泛型只能是引用类型，不能是基本类型

public class ArrayList1 {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        //左边类型必须要写，JDK 1.7+开始右边尖括号内的类型可以省略
        System.out.println(list1);

        //添加
        list1.add("thoughtworks");//创建的String类型的列表就只能添加字符串类型的数据
        list1.add("java");
        list1.add(1, "c");//在指定索引位置插入指定元素
        System.out.println(list1);

        //替换
        String str=list1.set(1,"c");
        System.out.println(str);
        System.out.println(list1);
        System.out.println("=============");

        //查找
        System.out.println(list1.get(0));//获取某索引位置的元素

        //删除
        String name1 = list1.remove(1);//list.remove的返回值为删除的元素
        System.out.println(name1);
        System.out.println(list1);

        System.out.println("=============");
        int size = list1.size();//获取集合长度
        System.out.println(size);

        for (int i = 0; i < list1.size(); i++) {//遍历集合
            System.out.println(list1.get(i));
        }
    }
}
