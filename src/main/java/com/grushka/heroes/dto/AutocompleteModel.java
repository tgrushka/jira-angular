package com.grushka.heroes.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AutocompleteModel {
    public Integer value;
    public String label;

    public AutocompleteModel(String label, Integer value) {
        this.label = label;
        this.value = value;
    }
}
