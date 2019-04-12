package com.tuneit.courses.lab2.db.schema;

import com.tuneit.courses.db.schema.Column;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReferenceToTable {
    @XmlAttribute(name = "table")
    private String tableName;
    @XmlAttribute(name = "column-ref")
    private String columnReferenceName;
    @XmlAttribute(name = "join-column-ref")
    private String joinColumnReferenceName;

    @XmlTransient
    private Table02 table;
    @XmlTransient
    private Column columnReference;
    @XmlTransient
    private Column joinColumnReference;

    public String getTableName() {
        return tableName;
    }

    public String getColumnReferenceName() {
        return columnReferenceName;
    }

    public String getJoinColumnReferenceName() {
        return joinColumnReferenceName;
    }

    public Table02 getTable() {
        return table;
    }

    public void setTable(Table02 table) {
        this.table = table;
    }

    public Column getColumnReference() {
        return columnReference;
    }

    public void setColumnReference(Column columnReference) {
        this.columnReference = columnReference;
    }

    public Column getJoinColumnReference() {
        return joinColumnReference;
    }

    public void setJoinColumnReference(Column joinColumnReference) {
        this.joinColumnReference = joinColumnReference;
    }
}
