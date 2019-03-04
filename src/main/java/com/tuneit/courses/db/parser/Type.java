package com.tuneit.courses.db.parser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
public class Type {
    @XmlElement(name = "table")
    private String table;

    @XmlElement(name = "column1")
    private String column1;

    @XmlElement(name = "column2")
    private String column2;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "subtype")
    private List<Subtype> subtypes = new ArrayList<>();

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public List<Subtype> getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(List<Subtype> subtypes) {
        this.subtypes = subtypes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
