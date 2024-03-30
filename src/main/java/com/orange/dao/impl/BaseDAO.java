package com.orange.dao.impl;

import com.orange.dao.IBaseDAO;
import com.orange.entity.BaseDO;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDAO<T extends BaseDO> extends SqlSessionDaoSupport implements IBaseDAO<T> {

     public String nameSpace;

    public SqlSessionFactory sqlSessionFactory;

    public BaseDAO() {
        this.nameSpace = this.getClass().getCanonicalName() + ".";
    }

    @Resource(name = "sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public void save(T t) {
        this.executeInsert(nameSpace + "save", t);
    }

    @Override
    public void saveBatch(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        this.executeBatchInsert(nameSpace + "saveBatchInsert", list);
    }

    @Override
    public int update(T t) {
        return this.executeUpdate(nameSpace + "update", t);
    }

    @Override
    public int updateBatch(List<T> list) {
        return this.executeBatchUpdate(nameSpace + "updateBatch", list);
    }

    @Override
    public T get(Object id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return this.queryForObject(this.nameSpace + "get", paramMap);
    }

    @Override
    public List<T> findList(Object t) {
        return this.queryForList(this.nameSpace + "findList", t);
    }

    @Override
    public T findModel(Object t) {
        return this.queryForObject(this.nameSpace + "findModel", t);
    }

    @Override
    public int remove(Object id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return this.executeDelete(this.nameSpace + "remove", paramMap);
    }

    protected T queryForObject(String statementName, Object parameterObject) {
        T object = this.getSqlSession().selectOne(statementName, parameterObject);
        return object;
    }


    protected List<T> queryForList(String statementName, Object parameterObject) {
        List<T> objectList = this.getSqlSession().selectList(statementName, parameterObject);
        return objectList;
    }

    protected int executeUpdate(String statementName, Object parameterObject) {
        int updateRows = this.getSqlSession().update(statementName, parameterObject);
        return updateRows;
    }

    protected Object executeInsert(String statementName, Object parameterObject) {
        Object back = null;
        back = this.getSqlSession().insert(statementName, parameterObject);
        return back;
    }

    protected boolean executeBatchInsert(String statementName, List<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        } else {
            SqlSession session = this.sqlSessionFactory.openSession(ExecutorType.BATCH, false);

            boolean isSuccess = false;
            try {
                session.insert(statementName, list);
                session.commit();
                session.clearCache();
                isSuccess = true;
            } catch (Exception e) {
                session.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }

            return isSuccess;
        }
    }

    protected int executeBatchUpdate(String statementName, List<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        } else {
            SqlSession session = this.sqlSessionFactory.openSession(ExecutorType.BATCH, false);

            int num = 0;
            try {
                num = session.update(statementName, list);
                session.commit();
                session.clearCache();
            } catch (Exception e) {
                session.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }

            return num;
        }
    }

    protected int executeDelete(String statementName, Object parameterObject) {
        int updateRows = this.getSqlSession().delete(statementName, parameterObject);
        return updateRows;
    }
}
