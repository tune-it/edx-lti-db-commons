package com.tuneit.courses.db.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author serge
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Column {
    
    @XmlAttribute(name="sql-name") private String sqlName;
    @XmlAttribute(name="name") private String name;
    @XmlAttribute(name="name-pl") private String namePL; //род.падеж
    @XmlTransient boolean sqlNameInUpperCase = false;

    @Override
    public String toString() {
        return "Column{" + "sqlName=" + sqlName + ", name=" + name + ", namePL=" + namePL+'}';
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

    public String getNamePL() {
        return namePL;
    }

    public void setNamePL(String namePL) {
        this.namePL = namePL;
    }
        
}
