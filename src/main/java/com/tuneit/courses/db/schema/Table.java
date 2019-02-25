package com.tuneit.courses.db.schema;

import java.util.List;
import javax.xml.bind.annotation.*;

/**
 *
 * @author serge
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Table {
    
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
    



}
