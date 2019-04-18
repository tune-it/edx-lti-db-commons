package com.tuneit.courses.lab2.schema;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class Substring implements Cloneable {
    @XmlAttribute(name = "sql")
    private String sqlNameColumn;

    @XmlElement(name = "left-position")
    @XmlList
    private List<String> leftSubstrings;

    @XmlElement(name = "right-position")
    @XmlList
    private List<String> rightSubstrings;

    @Override
    public Substring clone() {
        try {
            Substring substring = (Substring) super.clone();
            substring.sqlNameColumn = sqlNameColumn;
            substring.leftSubstrings = cloneListString(leftSubstrings);
            substring.rightSubstrings = cloneListString(rightSubstrings);
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

    public String getRandomLeftSubstring(Random random) {
        return leftSubstrings.get(random.nextInt(leftSubstrings.size()));
    }

    public String getRandomRightSubstring(Random random) {
        return rightSubstrings.get(random.nextInt(rightSubstrings.size()));
    }
}