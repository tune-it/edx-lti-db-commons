package com.tuneit.courses.db.lab1.schema;

import com.tuneit.courses.db.schema.Clone;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

import static com.tuneit.courses.db.schema.Schema.cloneList;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class DiffDate implements Cloneable, Clone<DiffDate> {

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

    @Override
    public DiffDate clone() {
        try {
            DiffDate diffDate = (DiffDate) super.clone();
            diffDate.sqlTableName = sqlTableName;
            diffDate.sqlNameColumn1 = sqlNameColumn1;
            diffDate.sqlNameColumn2 = sqlNameColumn2;
            diffDate.nativeDescription = nativeDescription;
            diffDate.diffDateOptions = cloneList(diffDateOptions);
            return diffDate;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
