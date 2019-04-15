package com.tuneit.courses.lab1.db.schema;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.Random;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class Condition {

    @XmlElement(name = "option")
    private List<String> optionConditions;

    @XmlAttribute(name = "native")
    private String nativeColumnName;

    @XmlAttribute(name = "sql")
    private String sqlColumnName;

    @XmlAttribute(name = "count-conditions")
    private Integer countConditions;

    @XmlAttribute(name = "greater")
    private String greater;

    @XmlAttribute(name = "below")
    private String below;

    @XmlAttribute(name = "equals")
    private String equals;

    public String getRandomOption(Random random) {
        return optionConditions.get(random.nextInt(optionConditions.size()));
    }
}
