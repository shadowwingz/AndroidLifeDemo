package com.shadowwingz.androidlifedemo.simplenet.cache;

/**
 * 请求缓存接口，key 是请求对应的 url，value 是请求对应的 response
 *
 * @param <K> key的类型
 * @param <V> value类型
 */
public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

    void remove(K key);
}
