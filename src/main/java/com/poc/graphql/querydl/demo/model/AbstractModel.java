package com.poc.graphql.querydl.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
public abstract class AbstractModel implements Serializable {

    @Column()
    protected Integer empresaId;
}
