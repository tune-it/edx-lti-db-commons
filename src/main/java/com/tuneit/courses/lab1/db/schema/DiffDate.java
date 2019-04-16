package com.tuneit.courses.lab1.db.schema;

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
public class DiffDate implements Cloneable {

    @XmlElement(name = "option")
    private List<DiffDateOption> diffDateOptions;

    @XmlAttribute(name = "table")
    private String sqlTableName;

    @XmlAttribute(name = "column1")
    private String sqlNameColumn1;

    @XmlAttribute(name = "column2")
    private String sqlNameColumn2;

    @XmlAttribute(name = "description")
    private String nativeDescription;

    public DiffDateOption getRandomDiffDateOption(Random random) {
        return diffDateOptions.get(random.nextInt(diffDateOptions.size()));
    }

    @Override
    public DiffDate clone() {
        try {
            DiffDate diffDate = (DiffDate) super.clone();
            diffDate.sqlTableName = sqlTableName;
            diffDate.sqlNameColumn1 = sqlNameColumn1;
            diffDate.sqlNameColumn2 = sqlNameColumn2;
            diffDate.nativeDescription = nativeDescription;
            diffDate.diffDateOptions = cloneListDiffDateOptions(diffDateOptions);
            return diffDate;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<DiffDateOption> cloneListDiffDateOptions(List<DiffDateOption> diffDateOptions) {
        List<DiffDateOption> cloneList = new ArrayList<>();
        for (DiffDateOption diffDateOption : diffDateOptions) {
            cloneList.add(diffDateOption.clone());
        }
        return cloneList;
    }
}
