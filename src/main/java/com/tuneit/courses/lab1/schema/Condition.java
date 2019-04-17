package com.tuneit.courses.lab1.schema;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class Condition implements Cloneable {

    @XmlElement(name = "option")
    private List<String> optionConditions;

    @XmlAttribute(name = "native")
    private String nativeColumnName;

    @XmlAttribute(name = "sql")
    private String sqlColumnName;

    @XmlAttribute(name = "count-conditions")
    private int countConditions;

    @XmlAttribute(name = "greater")
    private String greater;

    @XmlAttribute(name = "below")
    private String below;

    @XmlAttribute(name = "equals")
    private String equals;

    @XmlAttribute(name = "contains-null")
    private String containsNull;

    public String getRandomOption(Random random) {
        return optionConditions.get(random.nextInt(optionConditions.size()));
    }

    public PairSign getConditionSign(Random random) {
        if (!greater.isEmpty() && random.nextBoolean()) {
            return new PairSign(" " + greater + " ", " > ");
        } else if (!below.isEmpty() && random.nextBoolean()) {
            return new PairSign(" " + below + " ", " < ");
        } else if (!equals.isEmpty() && random.nextBoolean()) {
            return new PairSign(" " + equals + " ", " = ");
        } else {
            if (!greater.isEmpty()) {
                return new PairSign(" " + greater + " ", " > ");
            } else if (!below.isEmpty()) {
                return new PairSign(" " + below + " ", " < ");
            } else {
                return new PairSign(" " + equals + " ", " = ");
            }
        }
    }

    @Override
    protected Condition clone() {
        try {
            Condition condition = (Condition) super.clone();
            condition.nativeColumnName = nativeColumnName;
            condition.sqlColumnName = sqlColumnName;
            condition.countConditions = countConditions;
            condition.greater = greater;
            condition.below = below;
            condition.equals = equals;
            condition.containsNull = containsNull;
            if (condition.optionConditions != null) {
                condition.optionConditions = cloneList(optionConditions);
            }
            return condition;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> cloneList(List<String> options) {
        List<String> cloneList = new ArrayList<>();
        for (String condition : options) {
            cloneList.add(condition);
        }
        return cloneList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PairSign {
        String signConditionNative;
        String signConditionSql;
    }
}
