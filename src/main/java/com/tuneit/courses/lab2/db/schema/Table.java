package com.tuneit.courses.lab2.db.schema;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author serge
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Table implements Cloneable {

    @XmlAttribute(name = "sql-name")
    private String sqlName;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "name-genitive")
    private String nameGenitive; //род.падеж мн.ч.
    @XmlElement(name = "references")
    @XmlList
    private List<String> namesReferences;
    @XmlTransient
    boolean sqlNameInUpperCase = false;

    @XmlTransient
    List<Table> refTables = new ArrayList<>();

    @XmlElement(name = "column")
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

    public List<Table> getRefTables() {
        return refTables;
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

    public Column findColumn(String columnName) {
        Optional<Column> columnOptional = columns.stream()
                .filter(column -> column.getColumnName().equalsIgnoreCase(columnName))
                .findFirst();

        if (columnOptional.isPresent()) {
            return columnOptional.get();
        } else {
            throw new IllegalArgumentException("Column not found. Maybe mistake in xml.");
        }
    }

    public Column findColumnAndDelete(String columnName) {
        Column column = findColumn(columnName);
        columns.remove(column);
        return column;
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

    public List<String> getNamesReferences() {
        return namesReferences;
    }
}
