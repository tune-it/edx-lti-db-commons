package com.tuneit.courses.db.lab2;


import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
public class Task07 extends LabTask {
    @XmlElement(name = "subtask07")
    private List<Subtask07> subtasks07;


    @Override
    protected void updateAnswer(Table table, Task task) {
        Subtask07 randomSubtask06 = subtasks07.get(getRandom(task).nextInt(subtasks07.size())).clone();

        answer.append("SELECT ");

        String columnName = randomSubtask06.tableAndColumn.trim().split(":")[1];

        writeColumnFromTable(answer, table.getColumns(), task);

        int randomLeftOrRightPosition = getRandom(task).nextInt(2);

        String position;
        if (randomLeftOrRightPosition == 1) {
            int randomLeftPosition = getRandom(task).nextInt(randomSubtask06.leftPosition.size());
            position = randomSubtask06.leftPosition.get(randomLeftPosition);
        } else {
            int randomRightPosition = getRandom(task).nextInt(randomSubtask06.rightPosition.size());
            position = randomSubtask06.rightPosition.get(randomRightPosition);
        }

        answer.append(", ")
                .append(columnName)
                .append(" FROM ")
                .append(table.getTableName())
                .append(" WHERE ")
                .append(columnName)
                .append(" LIKE \'%")
                .append(position)
                .append("%\';");
    }

    @Override
    protected void updateQuery(Table table, Task task) {
        Subtask07 randomSubtask06 = subtasks07.get(getRandom(task).nextInt(subtasks07.size())).clone();

        query.append(prolog.trim())
                .append(" ");

        String columnName = randomSubtask06.tableAndColumn.trim().split(":")[1];

        String columnNamePL = table.getColumns().stream()
                .filter(column -> column.getColumnName().equalsIgnoreCase(columnName))
                .findFirst().get().getNamePlural();

        writeColumnFromTablePL(query, table.getColumns(), task);

        query.append(", ")
                .append(columnNamePL)
                .append(" которых содержащат в названии \'")
                .append(randomSubtask06.leftPosition.get(0))
                .append("\'.");
    }

    @Override
    protected Table getRandomTable(Schema schema, Task task) {
        if (!allowed.containsKey(schema.getName())) {
            allowed.put(schema.getName(), removeForbidenElements(schema, forbiddenList));
        }

        Subtask07 randomSubtask07 = subtasks07.get(getRandom(task).nextInt(subtasks07.size())).clone();
        String tableName = randomSubtask07.tableAndColumn.trim().split(":")[0];

        return findAllowedTable(schema, tableName);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Subtask07 implements Cloneable {
        @XmlAttribute(name = "for")
        private String tableAndColumn;


        @XmlElement(name = "left-position")
        @XmlList
        private List<String> leftPosition;

        @XmlElement(name = "right-position")
        @XmlList
        private List<String> rightPosition;

        @Override
        protected Subtask07 clone() {
            try {
                Subtask07 subtaskClone07 = (Subtask07) super.clone();
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
