package com.railvayticketiffice.entity;

import java.io.Serializable;

/**
 * Interface for entity identification
 *
 * @author Vladimir Petrenko
 */
public interface Identified<PK extends Serializable> {
    /**
     *
     * @return entity id
     */
    public PK getId();

    public void setId(PK key);

}
