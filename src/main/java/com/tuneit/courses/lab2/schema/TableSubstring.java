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
public class TableSubstring implements Cloneable {

    @XmlElement(name = "substring")
    private List<Substring> substring;

    @XmlAttribute(name = "sql")
    private String sqlTableName;

    @Override
    public TableSubstring clone() {
        try {
            TableSubstring tableSubstring = (TableSubstring) super.clone();
            tableSubstring.sqlTableName = sqlTableName;
            tableSubstring.substring = cloneListSubstring(substring);
            return tableSubstring;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Substring> cloneListSubstring(List<Substring> substrings) {
        List<Substring> cloneList = new ArrayList<>();
        for (Substring substring : substrings) {
            cloneList.add(substring.clone());
        }
        return cloneList;
    }

    public Substring getRandomSubstring(Random random) {
        return substring.get(random.nextInt(substring.size()));
    }
}
