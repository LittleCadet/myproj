package com.myproj.app.abstractd;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * 本地缓存抽象类
 *
 * @author The flow developers
 */
@Slf4j
public abstract class AbstractLoadingCacheDao<K, V> {

    protected LoadingCache<K, V> loadingCache;

    private Long maximunSize = 1000L;

    private Long minutes = 1L;

    private Integer concurrencyLevel = 8;

    @PostConstruct
    protected void init() {
        if (null == loadingCache) {
            synchronized (this) {
                if (null == loadingCache) {
                    loadingCache = CacheBuilder.newBuilder()
                            .maximumSize(maximunSize)
                            .expireAfterWrite(Duration.ofMinutes(minutes))
                            .concurrencyLevel(concurrencyLevel)
                            .recordStats()
                            .build(new CacheLoader<K, V>() {
                                @Override
                                public V load(K key) throws Exception {
                                    return loadData(key);
                                }
                            });
                }
            }
        }
    }

    public abstract V get(Object key);

    public abstract V get(Object key1, Object key2);

    public abstract V get(Object... key);

    public abstract void put(Object key);

    public abstract void put(Object key1, Object key2);

    public abstract void put(Object... key);

    public abstract void delete(Object key);

    public abstract void delete(Object key1,Object key2);

    public abstract void deleteAll();


    /**
     * 在缓存不命中的情况下，加载缓存
     *
     * @param key
     * @return
     * @throws Exception
     */
    protected abstract V loadData(K key) throws Exception;


}
