package com.poc.graphql.querydl.demo.repository;

import com.poc.graphql.querydl.demo.model.AbstractModel;
import com.poc.graphql.querydl.demo.model.QUser;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BasicRepository<T extends AbstractModel> {

    private Class< T > clazz;

    @PersistenceContext
    EntityManager entityManager;

    public void setClazz( Class< T > clazzToSet ) {
        this.clazz = clazzToSet;
    }

    public T findOne( Long id ){
        return entityManager.find( clazz, id );
    }
    public List< T > findAll(){
        JPAQuery query = new JPAQuery(entityManager);
        QUser q = QUser.user;

        return entityManager.createQuery( "from " + clazz.getName() )
                .getResultList();
    }

    public void save( T entity ){
        entity.setEmpresaId(1);
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
