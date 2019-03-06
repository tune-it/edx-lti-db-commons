package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
public class Task05 extends LabTask {
    @XmlElement(name = "type")
    private List<Type> types = new ArrayList<>();

    @Override
    protected void updateAnswer(Table table, Task task) {
        Type type = getRandomType(task);
        Subtype subtype = getRandomSubtype(task, type);

        answer.append("SELECT EXTRACT(")
                .append(subtype.getTime().trim())
                .append(" FROM ")
                .append(type.getColumn1().trim())
                .append(" - ")
                .append(type.getColumn2().trim())
                .append(") FROM ")
                .append(type.getTable().trim())
                .append(";");
    }

    @Override
    protected void updateQuery(Table table, Task task) {
        Type type = getRandomType(task);
        Subtype subtype = getRandomSubtype(task, type);

        query.append(prolog.trim())
                .append(" ")
                .append(type.getDescription().trim())
                .append(" ")
                .append(subtype.getValue().trim());
    }

    private Type getRandomType(Task task) {
        int typeRandomIndex = getRandom(task).nextInt(types.size());
        return types.get(typeRandomIndex);
    }

    private Subtype getRandomSubtype(Task task, Type type) {
        int timeRandomIndex = getRandom(task).nextInt(type.getSubtypes().size());
        return type.getSubtypes().get(timeRandomIndex);
    }

    @XmlAccessorType(XmlAccessType.NONE)
    private static class Subtype {
        @XmlElement(name = "time")
        private String time;

        @XmlElement(name = "value")
        private String value;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    private static class Type {
        @XmlElement(name = "table")
        private String table;

        @XmlElement(name = "column1")
        private String column1;

        @XmlElement(name = "column2")
        private String column2;

        @XmlElement(name = "description")
        private String description;

        @XmlElement(name = "subtype")
        private List<Subtype> subtypes = new ArrayList<>();

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getColumn1() {
            return column1;
        }

        public void setColumn1(String column1) {
            this.column1 = column1;
        }

        public String getColumn2() {
            return column2;
        }

        public void setColumn2(String column2) {
            this.column2 = column2;
        }

        public List<Subtype> getSubtypes() {
            return subtypes;
        }

        public void setSubtypes(List<Subtype> subtypes) {
            this.subtypes = subtypes;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }
}
