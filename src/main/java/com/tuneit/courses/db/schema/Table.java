package com.tuneit.courses.db.schema;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Table implements Cloneable {
    
    @XmlAttribute(name="sql-name") private String sqlName;
    @XmlAttribute(name="name") private String name;
    @XmlAttribute(name="name-rpl") private String nameRPL; //род.падеж мн.ч.
    @XmlTransient boolean sqlNameInUpperCase = false;
    
    @XmlElement(name="column")
    private List<Column> columns;

    @Override
    public String toString() {
        return "Table{" + "sqlName=" + sqlName + ", name=" + name + ", columns=" + columns + '}';
    }

    public String getTableName() {
        if (!sqlNameInUpperCase) {
            sqlName = sqlName.toUpperCase().trim();
        }
        return sqlName;
    }

    public void setTableName(String sqlName) {
        this.sqlName = sqlName.toUpperCase().trim();
        sqlNameInUpperCase = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameRPL() {
        return nameRPL;
    }

    public void setNameRPL(String nameRPL) {
        this.nameRPL = nameRPL;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public Table clone() {
        try {
            Table tableClone = (Table) super.clone();
            tableClone.setName(name);
            tableClone.setNameRPL(nameRPL);
            tableClone.setTableName(getTableName());
            tableClone.setColumns(new ArrayList<>());
            return tableClone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
