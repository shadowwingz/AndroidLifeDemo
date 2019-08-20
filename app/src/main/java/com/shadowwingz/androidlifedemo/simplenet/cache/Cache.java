package com.shadowwingz.androidlifedemo.simplenet.cache;

/**
 * 请求缓存接口
 *
 * @param <K> key的类型
 * @param <V> value类型
 */
public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

    void remove(K key);
}
