package com.tuneit.courses.lab2.schema;

import com.tuneit.courses.lab.schema.Clone;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

import static com.tuneit.courses.lab.schema.Schema.cloneList;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class TableCases implements Cloneable, Clone<TableCases> {

    @XmlElement(name = "case")
    private List<Case> cases;

    @XmlAttribute(name = "sql")
    private String sqlTableName;

    @Override
    public TableCases clone() {
        try {
            TableCases tableSubstring = (TableCases) super.clone();
            tableSubstring.sqlTableName = sqlTableName;
            tableSubstring.cases = cloneList(cases);
            return tableSubstring;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
