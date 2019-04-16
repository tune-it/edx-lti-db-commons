package com.tuneit.courses.lab1.db.schema;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class Aggregation implements Cloneable {
    @XmlAttribute(name = "table-sql")
    private String tableSqlName;

    @XmlAttribute(name = "table-native")
    private String tableNativeName;

    @XmlAttribute(name = "column")
    private String columnSqlName;

    @XmlAttribute(name = "function")
    private String functionSqlName;

    @XmlAttribute(name = "column-function-native")
    private String columnFunctionNativeName;

    @XmlAttribute(name = "condition")
    private String conditionColumnName;

    @Override
    public Aggregation clone() {
        try {
            Aggregation aggregation = (Aggregation) super.clone();
            aggregation.tableSqlName = tableSqlName;
            aggregation.tableNativeName = tableNativeName;
            aggregation.columnSqlName = columnSqlName;
            aggregation.functionSqlName = functionSqlName;
            aggregation.columnFunctionNativeName = columnFunctionNativeName;
            aggregation.conditionColumnName = conditionColumnName;
            return aggregation;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
