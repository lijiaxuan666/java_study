package Maps;

public class HashMap<K, V> {
    private int size;//元素个数
    private double loadFactor = 0.75;//负载因子
    private int yuzhi;//阈值，超过启动动态扩容
    private int capacity;//数组大小
    private Entry<K, V>[] table;//基础数组

    public HashMap() {//无参构造方法，默认数组大小为16,负载因子为0.75
        table = new Entry[16];
        size = 0;
        this.capacity = 16;
        this.yuzhi = (int) (this.capacity * this.loadFactor);
    }

    public HashMap(int capacity, double loadFactor) {//有参构造方法，根据输入参数创建基础数组
        if (capacity < 0) {
            throw new IllegalArgumentException();//抛出非法参数异常
        } else {
            table = new Entry[capacity];
            size = 0;
            this.capacity = capacity;
            this.yuzhi = (int) (capacity * loadFactor);
        }
    }

    private class Entry<K, V> {
        int hash;
        K key;
        V value;
        Entry next;

        Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private void extend() {//私有属性，HashMap的动态扩容，不能被外部访问
        Entry<K, V>[] newtable = new Entry[capacity];
        for (int i = 0; i < capacity / 2; i++) {
            if (table[i] != null) {
                Entry<K, V> entry = table[i];
                while (entry != null) {
                    int hash = entry.key.hashCode() & (capacity - 1);//计算根据key的hash值计算坐标
                    Entry<K, V> newEntry = new Entry<K, V>(hash, entry.key, entry.value, null);
                    newEntry.next = newtable[hash];
                    newtable[hash] = newEntry;
                    entry = entry.next;
                }
            }
        }
        table = newtable;
    }

    void put(K key, V value) { //添加元素
        if (key == null) {//如果输入键为null抛出异常
            throw new IllegalArgumentException();
        }
        if (size >= yuzhi) {//如果此时元素个数超过了阈值，启动扩容，数组变为原来两倍，重新计算元素坐标
            //懒扩容，只有调用put方法时才会判断元素个数，如果超过则启动扩容
            this.capacity *= 2;
            this.yuzhi = (int) (this.capacity * this.loadFactor);
            extend();
        }
        int hash = key.hashCode() & (capacity - 1);//计算根据key的hash值计算坐标
        Entry<K, V> newEntry = new Entry<K, V>(hash, key, value, null);
        Entry<K, V> entry = table[hash];
        while (entry != null) {
            if (entry.key.equals(key)) {//如果该键存在，则替换该键对应的值为新值
                entry.value = value;
                return;
            }
            entry = entry.next;
        }//若该键不存在，则使用头插
        newEntry.next = table[hash];
        table[hash] = newEntry;
        size++;
    }

    int size() {
        return size;
    }

    V get(K key) { //根据键查找对应的值
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int hash = key.hashCode() & (capacity - 1);
        Entry<K, V> entry = table[hash];//根据键的哈希值计算坐标，创建指针遍历该坐标的链表
        while (entry != null) {//若找到，返回键的值
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }//若没找到，返回空
        return null;
    }

    V remove(K key) { //删除指定的键
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int hash = key.hashCode() & (capacity - 1);
        Entry<K, V> entry = table[hash];
        Entry<K, V> entry2 = new Entry<>(0, null, null, null);
        entry2.next = table[hash];
        while (entry != null) {//若找到，返回键的值
            if (entry.key.equals(key)) {
                if (entry == table[hash]) {
                    table[hash] = entry.next;
                } else {
                    entry2.next = entry.next;
                }
                size--;
                return entry.value;
            }
            entry2 = entry2.next;
            entry = entry.next;
        }//若没找到，返回空
        return null;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean containsKey(K key) {//判断是否存在该键
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int hash = key.hashCode() & (capacity - 1);
        Entry<K, V> entry = table[hash];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

}

class text {
    public static void main(String[] args) {
        HashMap<String, Integer> has = new HashMap<>();
        has.put("abc", 100);
        has.put("bcd", 99);

        System.out.println(has.remove("abc"));
        System.out.println(has.get("bcd"));
        System.out.println(has.get("abc"));
        System.out.println(has.isEmpty());

        System.out.println(has.remove("bcd"));
        System.out.println(has.isEmpty());
    }
}