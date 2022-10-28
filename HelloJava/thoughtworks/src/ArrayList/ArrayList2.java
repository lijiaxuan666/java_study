package ArrayList;

import java.util.ArrayList;
import java.util.Random;

//如果希望在ArrayList集合中储存基本类型的数据，必须使用基本类型对应的“包装类”
//包装类是引用类型，位于java.lang包下，所以他不需要导包

public class ArrayList2 {
    public static void main(String[] args) {

        ArrayList<Integer> list2 = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int num = r.nextInt(50);
            //表示产生0~49的随机数，若要产生1~50的随机数可表示为 r.nextInt(50)+1;
            list2.add(num);
        }
        System.out.println(list2);
    }
}
