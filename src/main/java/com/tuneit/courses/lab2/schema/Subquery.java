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
public class Subquery implements Cloneable {
    @XmlAttribute(name = "column")
    private String sqlNameColumn;

    @XmlAttribute(name = "join-table")
    private String sqlNameJoinTable;

    @XmlAttribute(name = "join-column")
    private String sqlNameJoinColumn;

    @XmlAttribute(name = "native-query")
    private String nativeQuery;

    @XmlAttribute(name = "function")
    private String sqlFunction;

    @XmlAttribute(name = "condition-column")
    private String sqlNameConditionColumn;

    @XmlElement(name = "option")
    private List<String> options;

    @Override
    public Subquery clone() {
        try {
            Subquery substring = (Subquery) super.clone();
            substring.sqlNameColumn = sqlNameColumn;
            substring.sqlNameJoinTable = sqlNameJoinTable;
            substring.sqlNameJoinColumn = sqlNameJoinColumn;
            substring.nativeQuery = nativeQuery;
            substring.sqlFunction = sqlFunction;
            substring.sqlNameConditionColumn = sqlNameConditionColumn;
            substring.options = cloneListString(options);
            return substring;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> cloneListString(List<String> strings) {
        List<String> cloneList = new ArrayList<>();
        for (String string : strings) {
            cloneList.add(string);
        }
        return cloneList;
    }

    public String getRandomOption(Random random) {
        return options.get(random.nextInt(options.size()));
    }
}