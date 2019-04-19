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
public class TableCases implements Cloneable {

    @XmlElement(name = "case")
    private List<Case> cases;

    @XmlAttribute(name = "sql")
    private String sqlTableName;

    @Override
    public TableCases clone() {
        try {
            TableCases tableSubstring = (TableCases) super.clone();
            tableSubstring.sqlTableName = sqlTableName;
            tableSubstring.cases = cloneListCase(cases);
            return tableSubstring;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Case> cloneListCase(List<Case> cases) {
        List<Case> cloneList = new ArrayList<>();
        for (Case elementCases : cases) {
            cloneList.add(elementCases.clone());
        }
        return cloneList;
    }

    public Case getRandomCases(Random random) {
        return cases.get(random.nextInt(cases.size()));
    }
}
