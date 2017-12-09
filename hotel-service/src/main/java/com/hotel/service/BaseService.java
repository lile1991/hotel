package com.hotel.service;

import com.hotel.dao.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BaseService<T, ID extends Serializable, R extends BaseRepository<T, ID>> {

    @Autowired
    protected R repository;

    @Transactional
    public <S extends T> T save(S entity) {
        return repository.save(entity);
    }

    @Transactional
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        return repository.save(entities);
    }

    @Transactional(readOnly = true)
    public T findOne(ID id) {
        return repository.findOne(id);
    }

    @Transactional(readOnly = true)
    public boolean exists(ID id) {
        return repository.exists(id);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return iterable2List(repository.findAll());
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Iterable<ID> ids) {
        return iterable2List(repository.findAll(ids));
    }

    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Transactional
    public void delete(ID id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Transactional
    public void delete(Iterable<? extends T> entities) {
        repository.delete(entities);
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }


    @Transactional(readOnly = true)
    public List<T> findAll(Sort sort) {
        return iterable2List(repository.findAll(sort));
    }

    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Transactional(readOnly = true)
    public T findOne(Specification<T> spec) {
        return repository.findOne(spec);
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Specification<T> spec) {
        return repository.findAll(spec);
    }

    @Transactional(readOnly = true)
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return repository.findAll(spec, sort);
    }

    @Transactional(readOnly = true)
    public long count(Specification<T> spec) {
        return repository.count(spec);
    }


    public List<T> iterable2List(Iterable<T> iterable) {
        if(iterable == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();
        iterator.forEachRemaining(list::add);
        return list;
    }
}
