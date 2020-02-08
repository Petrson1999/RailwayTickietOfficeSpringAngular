package com.railvayticketiffice.entity;

import javax.persistence.*;

@MappedSuperclass
@Access(value = AccessType.FIELD)
public class BaseEntity  implements Identified<Integer> {

    public BaseEntity() {
    }

    protected BaseEntity(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Access(value = AccessType.PROPERTY)
    private int id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
