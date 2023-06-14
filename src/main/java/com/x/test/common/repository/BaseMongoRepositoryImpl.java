package com.x.test.common.repository;

import com.x.test.common.annotation.CreatedAt;
import com.x.test.common.annotation.UpdatedAt;
import com.x.test.common.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.util.StreamUtils;
import org.springframework.data.util.Streamable;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoRepositoryBean
public class BaseMongoRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements BaseMongoRepository<T, ID> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseMongoRepositoryImpl.class);


    private final MongoOperations mongoOperations;
    private final MongoEntityInformation<T, ID> entityInformation;

    public BaseMongoRepositoryImpl(MongoEntityInformation<T, ID> entityInformation, MongoOperations mongoOperations) {
        super(entityInformation, mongoOperations);
        this.entityInformation = entityInformation;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public <S extends T> S save(S entity) {

        Assert.notNull(entity, "Entity must not be null!");

        if (entityInformation.isNew(entity)) {
            this.setTimestampField(entity, Boolean.TRUE);
            return mongoOperations.insert(entity, entityInformation.getCollectionName());
        }

        this.setTimestampField(entity, Boolean.FALSE);
        return mongoOperations.save(entity, entityInformation.getCollectionName());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.repository.MongoRepository#saveAll(java.lang.Iterable)
     */
    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {

        Assert.notNull(entities, "The given Iterable of entities not be null!");

        Streamable<S> source = Streamable.of(entities);
        boolean allNew = source.stream().allMatch(entityInformation::isNew);

        if (allNew) {

            List<S> result = source.stream().map(x -> {
                setTimestampField(x, Boolean.TRUE);
                return x;
            }).collect(Collectors.toList());
            return new ArrayList<>(mongoOperations.insert(result, entityInformation.getCollectionName()));
        }

        return source.stream().map(this::save).collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.repository.MongoRepository#insert(java.lang.Object)
     */
    @Override
    public <S extends T> S insert(S entity) {

        Assert.notNull(entity, "Entity must not be null!");

        this.setTimestampField(entity, Boolean.TRUE);
        return mongoOperations.insert(entity, entityInformation.getCollectionName());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.repository.MongoRepository#insert(java.lang.Iterable)
     */
    @Override
    public <S extends T> List<S> insert(Iterable<S> entities) {

        Assert.notNull(entities, "The given Iterable of entities not be null!");

        Collection<S> list = toCollection(entities).stream().map(x -> {
            setTimestampField(x, Boolean.TRUE);
            return x;
        }).collect(Collectors.toList());

        if (list.isEmpty()) {
            return Collections.emptyList();
        }

        return new ArrayList<>(mongoOperations.insertAll(list));
    }

    private static <E> Collection<E> toCollection(Iterable<E> ids) {
        return ids instanceof Collection ? (Collection<E>) ids
                : StreamUtils.createStreamFromIterator(ids.iterator()).collect(Collectors.toList());
    }

    /**
     * Set timestamp field
     *
     * @param entity
     * @param isCreated
     * @return
     */
    private void setTimestampField(T entity, Boolean isCreated) {
        Field[] fields = entity.getClass().getDeclaredFields();
        long now = Instant.now().toEpochMilli();
        try {
            for (Field field : fields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }

                // update
                if (field.isAnnotationPresent(UpdatedAt.class)) {
                    // private 也可以访问值
                    field.setAccessible(true);
                    field.set(entity, now);
                }

                // created
                if (isCreated && field.isAnnotationPresent(CreatedAt.class)) {
                    // private 也可以访问值
                    field.setAccessible(true);
                    field.set(entity, now);
                }
            }
        } catch (IllegalAccessException exception) {
            LOG.error(Log.format("Set timestamp field failed"), exception);
        }
    }


}

