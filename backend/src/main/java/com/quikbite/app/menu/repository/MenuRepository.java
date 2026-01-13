package com.quikbite.app.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.quikbite.app.menu.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {
    // JpaSpecificationExecutor provides support for complex queries using predicates to compose dynamic queries so that we dont have to write custom queries
    /**
     * below was an example of how to implement
     * @Override
     * public List<Menu> findAll(Specification<Menu> spec) {
     *    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
     *   CriteriaQuery<Menu> query = cb.createQuery(Menu.class);
     *   Root<Menu> menu = query.from(Menu.class);
     *  query.select(menu).where(spec.toPredicate(menu, query, cb));
     *  return entityManager.createQuery(query).getResultList();
     * 
     */
}
