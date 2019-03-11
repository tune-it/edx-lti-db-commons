package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

public class Task09 extends LabTask {
    @XmlElement(name = "subtask09")
    private List<Subtask09> subtasks09;


    @Override
    protected void updateAnswer(Table table, Task task) {
        Subtask09 randomSubtask09 = subtasks09.get(getRandom(task).nextInt(subtasks09.size())).clone();

        answer.append("SELECT ");

        String columnName = randomSubtask09.tableAndColumn.trim().split(":")[1];

        table.getColumns().removeIf(column -> column.getName().equalsIgnoreCase(columnName));

        writeColumnFromTable(answer, table.getColumns(), task);

        int randomPosition = getRandom(task).nextInt(randomSubtask09.rightPosition.size());
        String position = randomSubtask09.rightPosition.get(randomPosition);

        answer.append(", ")
                .append(columnName)
                .append(" FROM ")
                .append(table.getTableName())
                .append(" WHERE ")
                .append(columnName)
                .append(" LIKE \'%")
                .append(position)
                .append("\';");
        System.out.println(answer);
    }

    @Override
    protected void updateQuery(Table table, Task task) {
        Subtask09 randomSubtask09 = subtasks09.get(getRandom(task).nextInt(subtasks09.size())).clone();

        query.append(prolog.trim())
                .append(" ");

        String columnName = randomSubtask09.tableAndColumn.trim().split(":")[1];

        String columnNamePL = table.getColumns().stream()
                .filter(column -> column.getColumnName().equalsIgnoreCase(columnName))
                .findFirst().get().getNamePL();

        table.getColumns().removeIf(column -> column.getName().equalsIgnoreCase(columnName));

        writeColumnFromTablePL(query, table.getColumns(), task);

        query.append(", ")
                .append(columnNamePL)
                .append(", содержащих в названии \'")
                .append(randomSubtask09.leftPosition.get(0))
                .append("\'.");
    }

    @Override
    protected Table getRandomTable(Schema schema, Task task) {
        if (!allowed.containsKey(schema.getName())) {
            allowed.put(schema.getName(), removeForbidenElements(schema, forbiddenList));
        }

        Subtask09 randomSubtask09 = subtasks09.get(getRandom(task).nextInt(subtasks09.size())).clone();
        String tableName = randomSubtask09.tableAndColumn.trim().split(":")[0];

        return findAllowedTable(schema, tableName);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Subtask09 implements Cloneable {
        @XmlAttribute(name = "for")
        private String tableAndColumn;


        @XmlElement(name = "left-position")
        @XmlList
        private List<String> leftPosition;

        @XmlElement(name = "right-position")
        @XmlList
        private List<String> rightPosition;

        @Override
        protected Subtask09 clone() {
            try {
                Subtask09 subtaskClone07 = (Subtask09) super.clone();
                subtaskClone07.tableAndColumn = tableAndColumn;
                subtaskClone07.leftPosition = copyPosition(leftPosition);
                subtaskClone07.rightPosition = copyPosition(rightPosition);
                return subtaskClone07;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }

        private List<String> copyPosition(List<String> positions) {
            if (positions != null) {
                return new ArrayList<>(positions);
            } else {
                return null;
            }
        }
    }
}
