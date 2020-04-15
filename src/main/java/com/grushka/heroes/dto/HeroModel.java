package com.grushka.heroes.dto;

import com.grushka.heroes.entity.Hero;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class HeroModel {
    public Integer id;

    public String name;

    public HeroModel() {}

    public HeroModel(Hero hero) {
        this.id = hero.getID();
        this.name = hero.getName();
    }
}
