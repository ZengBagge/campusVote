package com.vote.dao;

import java.io.Serializable;



public interface CommonDao<T extends Serializable, PK extends Serializable> {
    // -------------------- 基本检索、增加、修改、删除操作 --------------------

    // 根据主键获取实体。如果没有相应的实体，返回 null。
    public T get(PK id)throws Exception;


    // 根据主键获取实体。如果没有相应的实体，抛出异常。
    public T load(PK id)throws Exception;


    // 更新实体
    public boolean update(T entity)throws Exception;


    // 存储实体到数据库
    public boolean save(T entity)throws Exception;


    // 增加或更新实体
    public boolean saveOrUpdate(T entity)throws Exception;


    // 删除指定的实体
    public boolean delete(T entity)throws Exception;



}
