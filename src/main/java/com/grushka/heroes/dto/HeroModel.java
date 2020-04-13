package com.grushka.heroes.dto;

import com.grushka.heroes.entity.Hero;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class HeroModel {
    public static class HeroObject {
        public Integer id;

        public String name;

        public HeroObject() {}

        public HeroObject(Hero hero) {
            this.id = hero.getID();
            this.name = hero.getName();
        }
    }

    public HeroObject hero;
    public List<HeroObject> heroes;

    public HeroModel() {}

    public HeroModel(Hero[] heroEntities) {
        this.heroes = Arrays.stream(heroEntities)
            .map(HeroObject::new)
            .collect(Collectors.toList());
    }

    public HeroModel(Hero heroEntity) {
        this.hero = new HeroObject(heroEntity);
    }
}
