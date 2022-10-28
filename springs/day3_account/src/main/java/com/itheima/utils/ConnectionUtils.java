package com.itheima.utils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 */
public class ConnectionUtils {

    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     * @return
     */
    public Connection getThreadConnection() {
        try{
            //1.先从ThreadLocal上获取
            Connection conn = tl.get();
            //2.判断当前线程上是否有连接
            if (conn == null) {
                //3.从数据源中获取一个连接，并且存入ThreadLocal中
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            //4.返回当前线程上的连接
            return conn;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection(){
        tl.remove();
    }
    //当连接用完后，会将连接归还到连接池中
    //Java的Web应用开发需要将线程和连接进行一个解绑操作，Java开发不需要考虑

    /*
        实际上 ThreadLocalMap 中使用的 key 为 ThreadLocal 的弱引用，
    弱引用的特点是，如果这个对象只存在弱引用，那么在下一次垃圾回收的时候必然会被清理掉。

        所以如果 ThreadLocal 没有被外部强引用的情况下，在垃圾回收的时候会被清理掉的，
    这样一来 ThreadLocalMap中使用这个 ThreadLocal 的 key 也会被清理掉。
    但是，value 是强引用，不会被清理，这样一来就会出现 key 为 null 的 value。

        ThreadLocal其实是与线程绑定的一个变量，如此就会出现一个问题：
        如果没有将ThreadLocal内的变量删除（remove）或替换，它的生命周期将会与线程共存。
    通常线程池中对线程管理都是采用线程复用的方法，在线程池中线程很难结束甚至于永远不会结束，
    这将意味着线程持续的时间将不可预测，甚至与JVM的生命周期一致。
     */


    /*
    ThreadLocal<T>其实是与线程绑定的一个变量。ThreadLocal和Synchonized都用于解决多线程并发访问。

    但是ThreadLocal与synchronized有本质的区别：
    1、Synchronized用于线程间的数据共享，而ThreadLocal则用于线程间的数据隔离。
    2、Synchronized是利用锁的机制，使变量或代码块在某一时该只能被一个线程访问。而ThreadLocal为每一个线程都提供了变量的副本
    ，使得每个线程在某一时间访问到的并不是同一个对象，这样就隔离了多个线程对数据的数据共享。

    而Synchronized却正好相反，它用于在多个线程间通信时能够获得数据共享。

    一句话理解ThreadLocal，向ThreadLocal里面存东西就是向它里面的Map存东西的，
    然后ThreadLocal把这个Map挂到当前的线程底下，这样Map就只属于这个线程了。
     */
}
