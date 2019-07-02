package com.tuneit.courses.db.template;

import com.tuneit.courses.db.generate.Clone;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author serge
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Column implements Cloneable, Clone<Column> {

    @XmlTransient
    boolean sqlNameInUpperCase = false;
    @XmlAttribute(name = "sql-name")
    private String sqlName;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "name-plural")
    private String namePlural; //мн. ч
    @XmlAttribute(name = "name-genitive-plural")
    private String nameGenitivePlural; //род. падеж, мн. ч
    @XmlAttribute(name = "name-creative-plural")
    private String nameCreativePlural; //твор. падеж, мн. ч

    @Override
    public String toString() {
        return "Column{" + "sqlName=" + sqlName + ", name=" + name + ", namePlural=" + namePlural + '}';
    }

    public String getColumnName() {
        if (!sqlNameInUpperCase) {
            sqlName = sqlName.toUpperCase().trim();
        }
        return sqlName;
    }

    public void setColumnName(String sqlName) {
        this.sqlName = sqlName.toUpperCase().trim();
        this.sqlName = sqlName;
    }

    @Override
    public Column clone() {
        try {
            Column columnClone = (Column) super.clone();
            columnClone.setColumnName(getColumnName());
            columnClone.setName(name);
            columnClone.setNamePlural(namePlural);
            return columnClone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
