package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@XmlRootElement(name = "task06")
@XmlAccessorType(XmlAccessType.FIELD)
public class Task06 extends LabTask {
    @XmlElement(name = "subtask06", required = true)
    private List<Subtask06> subtasks06;

    @Override
    protected void updateAnswer(Table table, Task task) {
        Subtask06 randomSubtask06 = subtasks06.get(getRandom(task).nextInt(subtasks06.size())).clone();

        Collections.shuffle(randomSubtask06.columns06, getRandom(task));

        answer.append("SELECT '")
                .append(randomSubtask06.description.trim())
                .append(" ' || ");

        Random randomForKind = getRandom(task);
        for (int i = 0; i < randomSubtask06.columns06.size(); i++) {
            List<Option06> options06 = randomSubtask06.columns06.get(i).options06;
            Option06 option06 = options06.get(randomForKind.nextInt(options06.size()));
            answer.append(option06.answer.trim());
            if (i != randomSubtask06.columns06.size() - 1) {
                answer.append(" || ' ' || ");
            }
        }


        answer.append(" FROM ").append(table.getTableName()).append(";");
        System.out.println(answer);
    }

    @Override
    protected void updateQuery(Table table, Task task) {
        Subtask06 randomSubtask06 = subtasks06.get(getRandom(task).nextInt(subtasks06.size())).clone();

        Collections.shuffle(randomSubtask06.columns06, getRandom(task));

        query.append(prolog.trim())
                .append(" ");

        for (int i = 0; i < randomSubtask06.columns06.size(); i++) {
            query.append(randomSubtask06.columns06.get(i).name.trim());
            if (i != randomSubtask06.columns06.size() - 1) {
                query.append(", ");
            }
        }

        query.append(" ")
                .append(table.getNameGenitive())
                .append(" в формате \'")
                .append(randomSubtask06.description.trim())
                .append(" ");

        Random randomForKind = getRandom(task);
        for (int i = 0; i < randomSubtask06.columns06.size(); i++) {
            List<Option06> options06 = randomSubtask06.columns06.get(i).options06;
            Option06 option06 = options06.get(randomForKind.nextInt(options06.size()));
            query.append(option06.query.trim());
            if (i != randomSubtask06.columns06.size() - 1) {
                query.append(" ");
            }
        }
        query.append(epilog.trim());
    }

    @Override
    protected Table getRandomTable(Schema schema, Task task) {
        if (!allowed.containsKey(schema.getName())) {
            allowed.put(schema.getName(), removeForbidenElements(schema, forbiddenList));
        }

        Subtask06 randomSubtask06 = subtasks06.get(getRandom(task).nextInt(subtasks06.size())).clone();
        String tableName = randomSubtask06.table.trim();

        return findAllowedTable(schema, tableName);
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Subtask06 implements Cloneable {
        @XmlAttribute(name = "table")
        String table;
        @XmlAttribute(name = "description")
        String description;
        @XmlElement(name = "column06", required = true)
        List<Column06> columns06 = new ArrayList<>();

        @Override
        public Subtask06 clone() {
            try {
                Subtask06 subtask06Copy = (Subtask06) super.clone();
                subtask06Copy.table = table;
                subtask06Copy.description = description;
                subtask06Copy.columns06 = copyColumns();
                return subtask06Copy;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }

        List<Column06> copyColumns() {
            List<Column06> copyColumns06 = new ArrayList<>();
            for (Column06 column06 : columns06) {
                copyColumns06.add(column06.clone());
            }
            return copyColumns06;
        }
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Column06 implements Cloneable {
        @XmlAttribute(name = "name")
        String name;
        @XmlElement(name = "option06", required = true)
        List<Option06> options06;

        @Override
        public Column06 clone() {
            try {
                Column06 column06Copy = (Column06) super.clone();
                column06Copy.name = name;
                column06Copy.options06 = copyOption();
                return column06Copy;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }

        List<Option06> copyOption() {
            List<Option06> copyOptions06 = new ArrayList<>();
            for (Option06 option06 : options06) {
                copyOptions06.add(option06.clone());
            }
            return copyOptions06;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Option06 implements Cloneable {
        @XmlElement(name = "query")
        String query;
        @XmlElement(name = "answer")
        String answer;

        @Override
        public Option06 clone() {
            try {
                Option06 copyOption06 = (Option06) super.clone();
                copyOption06.query = query;
                copyOption06.answer = answer;
                return copyOption06;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
