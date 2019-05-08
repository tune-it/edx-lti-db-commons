package com.tuneit.courses.lab.schema;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

import static com.tuneit.courses.lab.schema.Schema.cloneList;

@XmlAccessorType(XmlAccessType.FIELD)
public class ConditionTable implements Cloneable, Clone<ConditionTable> {
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
}
