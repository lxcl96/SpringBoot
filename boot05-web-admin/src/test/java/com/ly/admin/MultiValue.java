package com.ly.admin;

import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.*;

/**
 * FileName:MultiValue.class
 * Author:ly
 * Date:2022/8/30 0030
 * Description:
 */
public class MultiValue<K,V> implements MultiValueMap<K, V>, Serializable {
    private final Map<K, List<V>> targetMap;

    public MultiValue(Map<K, List<V>> targetMap) {
        this.targetMap = targetMap;
    }

    @Override
    public V getFirst(K key) {
        return null;
    }

    @Override
    public void add(K key, V value) {
        List<V> values = this.targetMap.computeIfAbsent(key, k -> new ArrayList<>(1));
        values.add(value);
    }

    @Override
    public void addAll(K key, List<? extends V> values) {

    }

    @Override
    public void addAll(MultiValueMap<K, V> values) {

    }

    @Override
    public void set(K key, V value) {

    }

    @Override
    public void setAll(Map<K, V> values) {

    }

    @Override
    public Map<K, V> toSingleValueMap() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public List<V> get(Object key) {
        return null;
    }

    @Override
    public List<V> put(K key, List<V> value) {
        return null;
    }

    @Override
    public List<V> remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends List<V>> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<List<V>> values() {
        return null;
    }

    @Override
    public Set<Entry<K, List<V>>> entrySet() {
        return null;
    }
}
