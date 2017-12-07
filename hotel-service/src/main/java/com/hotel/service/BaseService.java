package com.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BaseService<T, ID extends Serializable, R extends CrudRepository<T, ID>> {

    @Autowired
    protected R repository;

    private PagingAndSortingRepository<T, ID> repositoryPagingAndSortingRepository;
    private JpaSpecificationExecutor<T> repositoryJpaSpecificationExecutor;

    @PostConstruct
    public void init() {
        if(repository instanceof PagingAndSortingRepository) {
            repositoryPagingAndSortingRepository = (PagingAndSortingRepository<T, ID>) repository;
        }
        if(repository instanceof JpaSpecificationExecutor) {
            repositoryJpaSpecificationExecutor = (JpaSpecificationExecutor<T>) repository;
        }
    }

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


    private PagingAndSortingRepository<T, ID> repositoryPagingAndSortingRepository() {
        Assert.notNull(repositoryPagingAndSortingRepository, repository.getClass().getName() + "不支持分页");
        return repositoryPagingAndSortingRepository;
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Sort sort) {
        return iterable2List(repositoryPagingAndSortingRepository().findAll(sort));
    }

    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return repositoryPagingAndSortingRepository().findAll(pageable);
    }


    private JpaSpecificationExecutor<T> repositoryJpaSpecificationExecutor() {
        Assert.notNull(repositoryJpaSpecificationExecutor, repository.getClass().getName() + "不支持条件查询");
        return repositoryJpaSpecificationExecutor;
    }

    @Transactional(readOnly = true)
    public T findOne(Specification<T> spec) {
        return repositoryJpaSpecificationExecutor.findOne(spec);
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Specification<T> spec) {
        return repositoryJpaSpecificationExecutor.findAll(spec);
    }

    @Transactional(readOnly = true)
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return repositoryJpaSpecificationExecutor.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return repositoryJpaSpecificationExecutor.findAll(spec, sort);
    }

    @Transactional(readOnly = true)
    public long count(Specification<T> spec) {
        return repositoryJpaSpecificationExecutor.count(spec);
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
