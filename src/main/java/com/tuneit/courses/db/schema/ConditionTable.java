package com.tuneit.courses.db.schema;


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
public class ConditionTable implements Cloneable {
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

    @Override
    public ConditionTable clone() {
        try {
            ConditionTable conditionTable = (ConditionTable) super.clone();
            conditionTable.sqlTableName = sqlTableName;
            conditionTable.nativeTableName = nativeTableName;
            conditionTable.conditions = cloneList(conditions);
            return conditionTable;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Condition> cloneList(List<Condition> conditions) {
        List<Condition> cloneList = new ArrayList<>();
        for (Condition condition : conditions) {
            cloneList.add(condition.clone());
        }
        return cloneList;
    }
}
