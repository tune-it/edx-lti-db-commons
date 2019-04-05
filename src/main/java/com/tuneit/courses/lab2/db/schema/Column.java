package com.tuneit.courses.lab2.db.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author serge
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Column implements Cloneable {
    
    @XmlAttribute(name="sql-name") private String sqlName;
    @XmlAttribute(name="name") private String name;
    @XmlAttribute(name="name-plural") private String namePlural; //мн. ч
    @XmlAttribute(name="name-genitive-plural") private String nameGenitivePlural; //род. падеж, мн. ч
    @XmlAttribute(name="name-creative-plural") private String nameCreativePlural; //твор. падеж, мн. ч
    @XmlTransient boolean sqlNameInUpperCase = false;

    @Override
    public String toString() {
        return "Column{" + "sqlName=" + sqlName + ", name=" + name + ", namePlural=" + namePlural +'}';
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamePlural() {
        return namePlural;
    }

    public void setNamePlural(String namePlural) {
        this.namePlural = namePlural;
    }

    @Override
    public Column clone() {
        try {
            Column columnClone = (Column) super.clone();
            columnClone.setColumnName(getColumnName());
            columnClone.setName(name);
            columnClone.setNamePlural(namePlural);
            return columnClone;
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
            return null;
        }
    }

    public String getNameGenitivePlural() {
        return nameGenitivePlural;
    }

    public void setNameGenitivePlural(String nameGenitivePlural) {
        this.nameGenitivePlural = nameGenitivePlural;
    }

    public String getNameCreativePlural() {
        return nameCreativePlural;
    }

    public void setNameCreativePlural(String nameCreativePlural) {
        this.nameCreativePlural = nameCreativePlural;
    }
}
