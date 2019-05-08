package com.tuneit.courses.lab.schema;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.*;

import static com.tuneit.courses.lab.schema.Schema.cloneList;

/**
 * @author serge
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Table implements Cloneable, Clone<Table> {

    @XmlTransient
    protected boolean sqlNameInUpperCase = false;
    @XmlAttribute(name = "sql-name")
    protected String sqlName;
    @XmlAttribute(name = "name")
    @Getter
    @Setter
    protected String name;
    @XmlAttribute(name = "name-genitive")
    @Getter
    @Setter
    protected String nameGenitive; //род.падеж мн.ч.
    @XmlElement(name = "column")
    @Getter
    @Setter
    protected List<Column> columns;

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

    @Override
    public Table clone() {
        try {
            Table tableClone = (Table) super.clone();
            tableClone.setName(name);
            tableClone.setNameGenitive(nameGenitive);
            tableClone.setTableName(getTableName());
            tableClone.setColumns(cloneList(columns));
            return tableClone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Column> getRandomColumns(Random random, int minColumns) {
        if (minColumns > columns.size()) {
            throw new IllegalArgumentException(minColumns + " columns don't exist.");
        }
        Collections.shuffle(columns, random);

        List<Column> resultList = new ArrayList<>();

        for (int i = 0; minColumns > 0; minColumns--) {
            resultList.add(columns.get(i));
            columns.remove(i);

        }
        if (columns.size() - minColumns > 0) {
            for (int i = 0; i < random.nextInt(columns.size() - minColumns); i++) {
                resultList.add(columns.get(i));
            }
        }

        return resultList;
    }
}
