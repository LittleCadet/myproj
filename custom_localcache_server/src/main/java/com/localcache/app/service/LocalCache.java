package com.localcache.app.service;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.Weighers;

public class LocalCache<K, V> {

    private long expire;
    private ConcurrentLinkedHashMap<K, Node<V>> store;

    public LocalCache() {
        this(100, 3000);
    }

    /**
     * 本地缓存使用google的ConcurrentLinkedHashMap的原因： 可以执行lru的过期策略
     * @param maxSize
     * @param expire
     */
    public LocalCache(int maxSize, int expire) {
        this.expire = expire;
        //this.expire = expire*1000;
        ConcurrentLinkedHashMap.Builder<K, Node<V>> builder = new ConcurrentLinkedHashMap.Builder<>();
        builder.initialCapacity(maxSize / 4);
        builder.maximumWeightedCapacity(maxSize);
        builder.weigher(Weighers.singleton());
        store = builder.build();
    }

    public V get(K k) {
        Node<V> node = store.get(k);
        if (node == null) {
            return null;
        }

        //如果过期，则在查询的时候 删除
        Long now = System.currentTimeMillis();
        if (node.getExpire() < now) {
            store.remove(k);
            return null;
        }
        return node.getV();
    }

    public void put(K k, V v) {

        //过期时间和value放在同一个类中
        Node node = new Node();
        node.setExpire(System.currentTimeMillis() + expire);
        node.setV(v);
        store.put(k, node);
    }

    public V remove(K k) {
        //如果缓存中有key，那么删除对应的key
        Node<V> node = store.get(k);
        if (node == null) {
            return null;
        }
        store.remove(k);
        return node.getV();
    }


    public void setMaxSize(int maxSize) {
        this.store.setCapacity(maxSize);
    }

    public int getMaxSize() {
        return (int) this.store.capacity();
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    private class Node<V> {
        private V v;
        private long expire;

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }

        public long getExpire() {
            return expire;
        }

        public void setExpire(long expire) {
            this.expire = expire;
        }
    }
}
