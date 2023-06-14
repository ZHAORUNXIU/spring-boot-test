package com.x.test.common.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseMongoRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {

    <S extends T> S save(S entity);
    <S extends T> List<S> saveAll(Iterable<S> entities);

    <S extends T> S insert(S entity);

    <S extends T> List<S> insert(Iterable<S> entities);

}
