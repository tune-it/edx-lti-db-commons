package com.tuneit.courses.lab2.schema;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class Case implements Cloneable {

    @XmlAttribute(name = "sql-column")
    private String sqlNameColumn;

    @XmlAttribute(name = "native-query")
    private String nativeQuery;

    @XmlAttribute(name = "sql-query")
    private String sqlQuery;

    @Override
    public Case clone() {
        try {
            Case reference = (Case) super.clone();
            reference.sqlNameColumn = sqlNameColumn;
            reference.nativeQuery = nativeQuery;
            reference.sqlQuery = sqlQuery;
            return reference;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
