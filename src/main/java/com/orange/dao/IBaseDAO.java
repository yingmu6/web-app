package com.orange.dao;

import java.util.List;

public interface IBaseDAO<T> {
    void save(T t);

    void saveBatch(List<T> list);

    int update(T t);

    int updateBatch(List<T> list);

    T get(Object id);

    List<T> findList(Object t);

    T findModel(Object t);

    int remove(Object id);
}
