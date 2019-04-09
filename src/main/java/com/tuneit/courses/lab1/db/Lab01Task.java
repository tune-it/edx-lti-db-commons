package com.tuneit.courses.lab1.db;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.lab1.db.schema.Column;
import com.tuneit.courses.lab1.db.schema.Schema01;
import com.tuneit.courses.lab1.db.schema.Table;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * @author serge
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Lab01Task implements LabTask {
    @XmlTransient
    protected static HashMap<String, List<Table>> allowed = new HashMap<>();
    protected StringBuilder query = new StringBuilder();
    protected StringBuilder answer = new StringBuilder();
    @XmlAttribute(name = "description")
    protected String description;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlElement(name = "prolog")
    protected String prolog;
    @XmlElement(name = "epilog")
    protected String epilog;
    @XmlElement(name = "forbidden-list")
    protected List<String> forbiddenList = new ArrayList<>();

    protected static List<Table> removeForbiddenElements(Schema01 s, List<String> forbidenElements) {
        ArrayList<Table> allowed = new ArrayList<>();
        for (Table table : s.getTables()) {
            if (forbidenElements.stream().noneMatch(str -> str.equalsIgnoreCase(table.getTableName()))) {
                Table allowedTable = table.clone();

                allowed.add(allowedTable);
            }
        }
        return allowed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProlog() {
        return prolog;
    }

    public void setProlog(String prolog) {
        this.prolog = prolog;
    }

    public String getEpilog() {
        return epilog;
    }

    public void setEpilog(String epilog) {
        this.epilog = epilog;
    }

    @Override
    public String toString() {
        return "Lab01Task{" + "description=" + description + ", id=" + id +
                ", prolog=" + prolog + ", epilog=" + epilog + '}';
    }

    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Table table = getRandomTable(schema01, task).clone();

        Collections.shuffle(table.getColumns(), getRandom(task));

        updateQuery(table.clone(), task);

        updateAnswer(table.clone(), task);

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

    protected void updateQuery(Table table, Task task) {
        List<Column> columns = table.getColumns();
        query.append(getProlog());
        query.append(" ");
        writeColumnFromTablePL(query, columns, task);
        query.append(" ")
                .append(getEpilog())
                .append(" ")
                .append(table.getTableName())
                .append('.');
    }

    protected void updateQueryPL(Table table, Task task) {
        List<Column> columns = table.getColumns();
        query.append(getProlog());
        query.append(" ");
        writeColumnFromTablePL(query, columns, task);
        query.append(" ")
                .append(getEpilog())
                .append(" ")
                .append(table.getNameGenitive())
                .append('.');
    }

    protected void updateAnswer(Table table, Task task) {
        List<Column> columns = table.getColumns();
        answer.append("SELECT ");
        writeColumnFromTable(answer, columns, task);
        answer.append(" FROM ").append(table.getTableName()).append(';');
    }

    protected void writeColumnFromTable(StringBuilder string, List<Column> columns, Task task) {
        Collections.shuffle(columns, getRandom(task));
        string.append(columns.get(0).getColumnName());
        for (int i = 1; i < getRandom(task).nextInt(columns.size()); i++) {
            string.append(", ").append(columns.get(i).getColumnName());
        }
    }

    protected void writeColumnFromTablePL(StringBuilder string, List<Column> columns, Task task) {
        Collections.shuffle(columns, getRandom(task));
        string.append(columns.get(0).getNamePlural());
        for (int i = 1; i < getRandom(task).nextInt(columns.size()); i++) {
            string.append(", ")
                    .append(columns.get(i).getNamePlural());
        }
    }

    protected Table getRandomTable(Schema01 schema01, Task task) {
        if (!allowed.containsKey(schema01.getName())) {
            allowed.put(schema01.getName(), removeForbiddenElements(schema01, forbiddenList));
        }

        List<Table> tables = allowed.get(schema01.getName());
        return tables.get(getRandom(task).nextInt(tables.size()));
    }

    protected Table findAllowedTable(Schema01 schema01, String tableName) {
        List<Table> tables = allowed.get(schema01.getName());
        Optional<Table> tableOptional = tables.stream()
                .filter(table -> table.getTableName().equalsIgnoreCase(tableName)).findFirst();

        if (tableOptional.isPresent()) {
            return tableOptional.get();
        } else {
            throw new IllegalArgumentException("Table with name \"" + tableName + "\" don't found.");
        }
    }

    protected Random getRandom(Task task) {
        int seed = task.getId().toUpperCase().hashCode();
        return new Random(seed);
    }

}
