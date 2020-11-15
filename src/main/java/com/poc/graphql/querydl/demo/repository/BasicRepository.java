package com.poc.graphql.querydl.demo.repository;

import com.poc.graphql.querydl.demo.holder.AuthenticationHolder;
import com.poc.graphql.querydl.demo.model.AbstractModel;
import com.poc.graphql.querydl.demo.model.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BasicRepository<T extends AbstractModel> {

    private Class< T > clazz;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private AuthenticationHolder holder;

    public void setClazz( Class< T > clazzToSet ) {
        this.clazz = clazzToSet;
    }

    public T findOne( Long id ){
        return entityManager.find( clazz, id );
    }

    public List< T > findAll(){
        return entityManager.createQuery( "from " + clazz.getName() )
                .getResultList();
    }

    public List<?> findAll(Class<T> clazz, Predicate expression) {
        this.clazz = clazz;
        JPAQuery<T> query = new JPAQuery(entityManager);

        List<T> fetch = query.from(QUser.user)
                .orderBy(QUser.user.id.desc())
                .fetch();

        query = new JPAQuery(entityManager);
        T one = query
                .from(QUser.user)
                .where(expression)
                .fetchOne();

        return null;
    }

    public void save( T entity ){
        entity.setEmpresaId(Integer.valueOf(holder.getTenant()));
        entityManager.persist( entity );
    }

    public void update( T entity ){
        entityManager.merge( entity );
    }

    public void delete( T entity ){
        entityManager.remove( entity );
    }
    public void deleteById( Long entityId ){
        T entity = getById( entityId );
        delete( entity );
    }

    private T getById(Long entityId) {
        return null;
    }
}
