package com.tuneit.courses.lab2.schema;

import com.tuneit.courses.db.schema.Clone;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class Substring implements Cloneable, Clone<Substring> {
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
            substring.leftSubstrings = new ArrayList<>(leftSubstrings);
            substring.rightSubstrings = new ArrayList<>(rightSubstrings);
            return substring;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}