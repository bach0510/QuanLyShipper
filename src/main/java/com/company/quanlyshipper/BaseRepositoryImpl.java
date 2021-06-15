/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 *
 * @author Admin
 */
public class BaseRepositoryImpl<E,ID> extends SimpleJpaRepository<E,ID> implements BaseRepository<E,ID> {
    
    private EntityManager em;
    
    public BaseRepositoryImpl(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }

    @Override
    public List<E> findByQuery(String jpql, Map<String, Object> params) {
        TypedQuery<E> query = em.createQuery(jpql, getDomainClass());
        
        if (params != null ){
            for(String key : params.keySet()) {
                query.setParameter(key,params.get(key));
            }
        }
        return query.getResultList();
    }
    
    @Override
    public E findObjByQuery(String jpql, Map<String, Object> params) {
        TypedQuery<E> query = em.createQuery(jpql, getDomainClass());
        
        if (params != null ){
            for(String key : params.keySet()) {
                query.setParameter(key,params.get(key));
            }
        }
        return query.getSingleResult();
    }
    
}
