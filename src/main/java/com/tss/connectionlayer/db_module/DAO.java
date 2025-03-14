package com.tss.connectionlayer.db_module;

public interface DAO<T> {
    void save(T object);
    T findById(String id);
}
