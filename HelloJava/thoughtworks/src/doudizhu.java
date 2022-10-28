import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class doudizhu {
    public static void main(String[] args) {
        HashMap<Integer,String> poker=new HashMap<>();//用来存储牌的索引及其唯一对应的牌面
        ArrayList<Integer> pokerindex=new ArrayList<>();//用来存储牌的索引

        List<String> colors=List.of("♠","♥","♦","♣");//牌的花色
        List<String> numbers=List.of("2","A","K","Q","J","10","9","8","7","6","5","4","3");//牌的序号值

        int index=0;
        poker.put(index,"大王");//先将特殊的大小王提前存起来
        pokerindex.add(index);
        index++;
        poker.put(index,"小王");
        pokerindex.add(index);
        index++;

        for(String number:numbers){//用增强for循环遍历两个集合，并组合成一个牌面与索引对应起来
            for(String color:colors){//{0=大王, 1=小王, 2=♠2, 3=♥2...49=♣4, 50=♠3, 51=♥3, 52=♦3, 53=♣3}
                poker.put(index,color+number);
                pokerindex.add(index);
                index++;
            }
        }
        Collections.shuffle(pokerindex);//洗牌：将牌的索引打乱，以达到随机发牌的目的

        ArrayList<Integer> player1=new ArrayList<>();//定义三个玩家
        ArrayList<Integer> player2=new ArrayList<>();
        ArrayList<Integer> player3=new ArrayList<>();
        ArrayList<Integer> leave=new ArrayList<>();//3张底牌
        for (int i = 0; i < pokerindex.size(); i++) {
            Integer x=pokerindex.get(i);
            if(i>=51){//剩余3张留给底牌
                leave.add(x);
            }else if(i%3==0){
                player1.add(x);
            }else if(i%3==1){
                player2.add(x);
            }else{
                player3.add(x);
            }
        }

        Collections.sort(player1);
        Collections.sort(player2);
        Collections.sort(player3);

        watchpoker("QAQ",poker,player1);//需要多次使用，所以创建一个看牌的方法
        watchpoker("QwQ",poker,player2);
        watchpoker("QxQ",poker,player3);
        watchpoker("底牌",poker,leave);


    }
    public static void watchpoker(String name,HashMap<Integer,String> poker,ArrayList<Integer> index){
        System.out.print(name+":");//玩家名
        for(Integer key:index){
            String value=poker.get(key);//获取玩家集合中索引对应的牌面
            System.out.print(value+" ");
        }
        System.out.println();
    }
}
