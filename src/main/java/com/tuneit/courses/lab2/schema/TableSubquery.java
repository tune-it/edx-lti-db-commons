package com.tuneit.courses.lab2.schema;

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
public class TableSubquery implements Cloneable {

    @XmlElement(name = "subquery")
    private List<Subquery> subqueries;

    @XmlAttribute(name = "sql")
    private String sqlTableName;

    @Override
    public TableSubquery clone() {
        try {
            TableSubquery tableReference = (TableSubquery) super.clone();
            tableReference.sqlTableName = sqlTableName;
            tableReference.subqueries = cloneListSubquery(subqueries);
            return tableReference;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Subquery> cloneListSubquery(List<Subquery> subqueries) {
        List<Subquery> cloneList = new ArrayList<>();
        for (Subquery subquery : subqueries) {
            cloneList.add(subquery.clone());
        }
        return cloneList;
    }

    public Subquery getRandomSubquery(Random random) {
        return subqueries.get(random.nextInt(subqueries.size()));
    }
}
