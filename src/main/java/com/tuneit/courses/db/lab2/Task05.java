package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "task05")
@XmlAccessorType(XmlAccessType.FIELD)
public class Task05 extends LabTask {
    @XmlElement(name = "subtask05", required = true)
    private List<Subtask05> subtasks05 = new ArrayList<>();

    @Override
    protected void updateAnswer(Table table, Task task) {
        Subtask05 subtask05 = getRandomType(task);
        Option05 option05 = getRandomSubtype(task, subtask05);

        answer.append("SELECT EXTRACT(")
                .append(option05.getTime().trim())
                .append(" FROM ")
                .append(subtask05.getColumn1().trim())
                .append(" - ")
                .append(subtask05.getColumn2().trim())
                .append(") FROM ")
                .append(subtask05.getTable().trim())
                .append(";");
    }

    @Override
    protected void updateQuery(Table table, Task task) {
        Subtask05 subtask05 = getRandomType(task);
        Option05 option05 = getRandomSubtype(task, subtask05);

        query.append(prolog.trim())
                .append(" ")
                .append(subtask05.getDescription().trim())
                .append(" ")
                .append(option05.getValue().trim());
    }

    private Subtask05 getRandomType(Task task) {
        int typeRandomIndex = getRandom(task).nextInt(subtasks05.size());
        return subtasks05.get(typeRandomIndex);
    }

    private Option05 getRandomSubtype(Task task, Subtask05 subtask05) {
        int timeRandomIndex = getRandom(task).nextInt(subtask05.getOptions05().size());
        return subtask05.getOptions05().get(timeRandomIndex);
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Subtask05 {
        @XmlAttribute(name = "table")
        private String table;

        @XmlAttribute(name = "column1")
        private String column1;

        @XmlAttribute(name = "column2")
        private String column2;

        @XmlAttribute(name = "description")
        private String description;

        @XmlElement(name = "option05", required = true)
        private List<Option05> options05;

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

        public List<Option05> getOptions05() {
            return options05;
        }

        public void setOptions05(List<Option05> options05) {
            this.options05 = options05;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Option05 {
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
}
