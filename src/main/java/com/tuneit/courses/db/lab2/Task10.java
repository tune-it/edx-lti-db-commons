package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task10 extends LabTask {
    @XmlElement(name = "subtask10")
    private List<Subtask10> subtasks10;


    @Override
    protected void updateAnswer(Table table, Task task) {
        Subtask10 randomSubtask10 = subtasks10.get(getRandom(task).nextInt(subtasks10.size())).clone();

        answer.append("SELECT ");

        String columnName = randomSubtask10.tableAndColumn.trim().split(":")[1];

        writeColumnFromTable(answer, table.getColumns(), task);

        List<String> randomOptions = getRandomOptions(randomSubtask10.options, task);

        answer.append(" FROM ")
                .append(table.getTableName())
                .append(" WHERE ");


        for (int i = 0; i < randomOptions.size(); i++) {
            String option = randomOptions.get(i);
            answer.append(columnName)
                    .append(" = \'")
                    .append(option)
                    .append("\'");
            if (i != randomOptions.size() - 1) {
                answer.append(" OR ");
            }
        }
        answer.append("\';");
    }

    private List<String> getRandomOptions(List<String> options, Task task) {
        Collections.shuffle(options, getRandom(task));

        List<String> randomOption = new ArrayList<>();
        for (int i = 0; i < getRandom(task).nextInt(options.size()); i++) {
            randomOption.add(options.get(i));
        }

        return randomOption;
    }

    @Override
    protected void updateQuery(Table table, Task task) {
        Subtask10 randomSubtask10 = subtasks10.get(getRandom(task).nextInt(subtasks10.size())).clone();

        query.append(prolog.trim())
                .append(" ");

        String columnName = randomSubtask10.tableAndColumn.trim().split(":")[1];

        String columnNamePL = table.getColumns().stream()
                .filter(column -> column.getColumnName().equalsIgnoreCase(columnName))
                .findFirst().get().getName();

        writeColumnFromTablePL(query, table.getColumns(), task);

        query.append(" где ")
                .append(columnNamePL)
                .append(" ");

        List<String> randomOptions = getRandomOptions(randomSubtask10.options, task);
        for (int i = 0; i < randomOptions.size(); i++) {
            String option = randomOptions.get(i);
            query.append("\'")
                    .append(option)
                    .append("\'");

            if (i != randomOptions.size() - 1) {
                query.append(", ");
            }
        }

        query.append(".");
    }

    @Override
    protected Table getRandomTable(Schema schema, Task task) {
        if (!allowed.containsKey(schema.getName())) {
            allowed.put(schema.getName(), removeForbidenElements(schema, forbiddenList));
        }

        Subtask10 randomSubtask10 = subtasks10.get(getRandom(task).nextInt(subtasks10.size())).clone();
        String tableName = randomSubtask10.tableAndColumn.trim().split(":")[0];

        return findAllowedTable(schema, tableName);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Subtask10 implements Cloneable {
        @XmlAttribute(name = "for")
        private String tableAndColumn;

        @XmlElement(name = "option")
        @XmlList
        private List<String> options;

        @Override
        protected Subtask10 clone() {
            try {
                Subtask10 subtaskClone07 = (Subtask10) super.clone();
                subtaskClone07.tableAndColumn = tableAndColumn;
                subtaskClone07.options = copyOption(options);
                return subtaskClone07;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }

        private List<String> copyOption(List<String> positions) {
            if (positions != null) {
                return new ArrayList<>(positions);
            } else {
                return null;
            }
        }
    }
}
