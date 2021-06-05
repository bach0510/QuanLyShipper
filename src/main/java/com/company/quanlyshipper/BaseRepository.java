/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author Admin
 */
@NoRepositoryBean
public interface BaseRepository<E,ID> extends JpaRepository<E,ID>{
    List<E> findByQuery(String jpql,Map<String,Object> params);
}
