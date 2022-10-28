public class MyConcurrentHashMap<K, V> {
    private int size = 0;
    private Node<K, V>[] table;
    private int capacity = 16;
    private double loadFactory = 0.75;

    public MyConcurrentHashMap() {
        this.table = new Node[capacity];
    }

    public MyConcurrentHashMap(int capacity, double loadFactory) {
        this.capacity = capacity;
        this.loadFactory = loadFactory;
        this.table = new Node[tableSizeFor(capacity)];
    }

    private int tableSizeFor(int capacity) {
        int newCapacity = capacity - 1;
        newCapacity |= newCapacity >> 1;
        newCapacity |= newCapacity >> 2;
        newCapacity |= newCapacity >> 4;
        newCapacity |= newCapacity >> 8;
        newCapacity |= newCapacity >> 16;
        return newCapacity + 1;
    }

    public V put(K key, V value) {
        if (key == null)
            return null;
        int hash = key.hashCode();
        int index = hash & (capacity - 1);
        Node<K, V> t = table[index];
        synchronized (t) {
            Node<K, V> node = new Node<>(key, value, hash, null);
            if (t == null) {
                table[index] = node;
            } else {
                while (t.next != null) {
                    //hash值不同直接跳过，不然有些key过大可能导致比较过慢
                    if (t.hash == hash&&t.key == key||t.key.equals(key)) {
                        V oldValue = t.value;
                        t.value = value;
                        return oldValue;
                    }
                    t = t.next;
                }
                t.next = node;
            }
        }
        size++;
        if (size > capacity * loadFactory)
            transfer(capacity * 2);
        return value;
    }

    private void transfer(int newCapacity) {
        //防止重复扩容
        if (size <= capacity * loadFactory)
            return;
        Node<K, V>[] newTable = new Node[newCapacity];
        for (int i = 0; i < capacity; i++) {
            Node<K, V> t = table[i];
            if (t != null) {
                synchronized (t) {
                    if (t.next == null) {
                        //如果只有一个节点，直接插入新表
                        newTable[t.hash & (newCapacity - 1)] = t;
                    } else {
                        Node<K, V> oldSite = null, newSite = null;
                        while (t != null) {
                            //在同一位置的元素，参与计算的位肯定是相同的
                            //但数组长度翻倍后，参与的位多了一位，所以可以根据多出来的最高位情况区分位置
                            //数组长度翻倍后，会受到影响的是与旧数组长度相与之后为1的，在之前位置+旧表长度的位置
                            if ((t.hash & capacity) == 0) {
                                if (newTable[i] == null) {
                                    newTable[i] = t;
                                } else {
                                    oldSite.next = t;
                                }
                                oldSite = t;
                            } else {
                                if (newTable[i + capacity] == null) {
                                    newTable[i + capacity] = t;
                                } else {
                                    newSite.next = t;
                                }
                                newSite = t;
                            }
                            t = t.next;
                        }
                        if (oldSite != null) {
                            oldSite.next = null;
                        }
                        if (newSite != null) {
                            newSite.next = null;
                        }
                    }
                }
            }
        }
        table = newTable;
        capacity = newCapacity;
    }
    public V remove(K key){
        if(key==null)
            return null;
        int index=key.hashCode()&(capacity-1);
        Node<K,V> t=table[index];
        if(t!=null){
            synchronized (t){
                //如果是表头第一个元素，直接令表头等于下个元素
                if(t.hash==key.hashCode()&&t.key==key||t.key.equals(key)){
                    table[index]=t.next;
                    return t.value;
                }
                t=t.next;
                Node<K,V> t2=table[index];
                while(t!=null){
                    if(t.hash == key.hashCode()&&t.key==key||t.key.equals(key)){
                        t2.next=t.next;
                        return t.value;
                    }
                    t=t.next;
                    t2=t2.next;
                }
            }
        }
        return null;
    }

    class Node<K, V> {
        K key;
        V value;
        int hash;
        Node next = null;

        Node(K key, V value, int hash, Node next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }
    }
}
