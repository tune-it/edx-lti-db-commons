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
public class ConditionTable {
    @XmlElement(name = "condition")
    @Getter
    @Setter
    private List<Condition> conditions;

    @XmlAttribute(name = "sql")
    @Getter
    @Setter
    private String sqlTableName;

    @XmlAttribute(name = "native")
    @Getter
    @Setter
    private String nativeTableName;

    public Condition getRandomCondition(Random random) {
        return conditions.get(random.nextInt(conditions.size()));
    }
}
