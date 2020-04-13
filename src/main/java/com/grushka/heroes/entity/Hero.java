package com.grushka.heroes.entity;

import net.java.ao.Entity;
import net.java.ao.schema.Indexed;

public interface Hero extends Entity {
    @Indexed
    String getName();
    void setName(String name);
}
