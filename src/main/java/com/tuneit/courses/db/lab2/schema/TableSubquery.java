package com.tuneit.courses.db.lab2.schema;

import com.tuneit.courses.db.schema.Clone;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

import static com.tuneit.courses.db.schema.Schema.cloneList;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class TableSubquery implements Cloneable, Clone<TableSubquery> {

    @XmlElement(name = "subquery")
    private List<Subquery> subqueries;

    @XmlAttribute(name = "sql")
    private String sqlTableName;

    @Override
    public TableSubquery clone() {
        try {
            TableSubquery tableReference = (TableSubquery) super.clone();
            tableReference.sqlTableName = sqlTableName;
            tableReference.subqueries = cloneList(subqueries);
            return tableReference;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
