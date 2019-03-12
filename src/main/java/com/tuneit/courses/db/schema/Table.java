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
    @XmlAttribute(name="name-genitive") private String nameGenitive; //род.падеж мн.ч.
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

    public String getNameGenitive() {
        return nameGenitive;
    }

    public void setNameGenitive(String nameGenitive) {
        this.nameGenitive = nameGenitive;
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
            tableClone.setNameGenitive(nameGenitive);
            tableClone.setTableName(getTableName());
            tableClone.setColumns(copyColumn(this.columns));
            return tableClone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Column> copyColumn(List<Column> columns) {
        ArrayList<Column> columnsClone = new ArrayList<>();
        for (Column column : columns) {
            columnsClone.add(column.clone());
        }

        return columnsClone;
    }
}
