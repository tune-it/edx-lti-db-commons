package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

public class Task11 extends LabTask {
    @XmlElement(name = "subtask11")
    private List<Subtask11> subtasks11;


    @Override
    protected void updateAnswer(Table table, Task task) {
        Subtask11 randomSubtask11 = subtasks11.get(getRandom(task).nextInt(subtasks11.size())).clone();

        String columnName = randomSubtask11.tableAndColumn.trim().split(":")[1];

        int randomPosition = getRandom(task).nextInt(randomSubtask11.options.size());
        String options = randomSubtask11.options.get(randomPosition);

        answer.append("SELECT \'")
                .append(options)
                .append("\' || \' = \' || COUNT(")
                .append(columnName)
                .append(") FROM ")
                .append(table.getTableName())
                .append(" WHERE ")
                .append(columnName)
                .append(" = \'")
                .append(options)
                .append("\' GROUP BY ")
                .append(columnName)
                .append(";");

        System.out.println(answer);
    }

    @Override
    protected void updateQuery(Table table, Task task) {
        Subtask11 randomSubtask11 = subtasks11.get(getRandom(task).nextInt(subtasks11.size())).clone();

        String columnName = randomSubtask11.tableAndColumn.trim().split(":")[1];
        String columnNamePL = table.getColumns().stream()
                .filter(column -> column.getColumnName().equalsIgnoreCase(columnName))
                .findFirst().get().getName();


        int randomPosition = getRandom(task).nextInt(randomSubtask11.options.size());
        String option = randomSubtask11.options.get(randomPosition);

        query.append(prolog.trim())
                .append(" ")
                .append(table.getNameRPL())
                .append(" где ")
                .append(columnNamePL)
                .append(" равен \'")
                .append(option)
                .append("\'. Ответ выдать в виде \'")
                .append(option)
                .append(" = 10\'.");
    }

    @Override
    protected Table getRandomTable(Schema schema, Task task) {
        if (!allowed.containsKey(schema.getName())) {
            allowed.put(schema.getName(), removeForbidenElements(schema, forbiddenList));
        }

        Subtask11 randomSubtask11 = subtasks11.get(getRandom(task).nextInt(subtasks11.size())).clone();
        String tableName = randomSubtask11.tableAndColumn.trim().split(":")[0];

        return findAllowedTable(schema, tableName);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Subtask11 implements Cloneable {
        @XmlAttribute(name = "for")
        private String tableAndColumn;

        @XmlElement(name = "option")
        @XmlList
        private List<String> options;

        @Override
        protected Subtask11 clone() {
            try {
                Subtask11 subtaskClone07 = (Subtask11) super.clone();
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
