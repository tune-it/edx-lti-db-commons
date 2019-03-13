package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Task12 extends LabTask {
    @XmlElement(name = "subtask12")
    private List<Subtask12> subtasks12;


    @Override
    protected void updateAnswer(Table table, Task task) {
        Subtask12 randomSubtask11 = subtasks12.get(getRandom(task).nextInt(subtasks12.size())).clone();

        String columnName = randomSubtask11.tableAndColumn.trim().split(":")[1];

        answer.append("SELECT COUNT(")
                .append(columnName)
                .append(") FROM ")
                .append(table.getTableName())
                .append(" GROUP BY ")
                .append(columnName)
                .append(" ORDER BY COUNT(")
                .append(columnName);

        if (getRandom(task).nextBoolean()) {
            answer.append(") ASC LIMIT 5;");
        } else {
            answer.append(") DESC LIMIT 5;");
        }
    }

    @Override
    protected void updateQuery(Table table, Task task) {
        Subtask12 randomSubtask11 = subtasks12.get(getRandom(task).nextInt(subtasks12.size())).clone();

        String columnName = randomSubtask11.tableAndColumn.trim().split(":")[1];
        String columnNameGenitivePlural = table.getColumns().stream()
                .filter(column -> column.getColumnName().equalsIgnoreCase(columnName))
                .findFirst().get().getNameGenitivePlural();

        query.append(prolog.trim())
                .append(" ")
                .append(columnNameGenitivePlural)
                .append(" ")
                .append(table.getNameGenitive())
                .append(". Результат ");
        if (getRandom(task).nextBoolean()) {
            query.append("расположить по возрастанию. ");
        } else {
            query.append("расположить по убыванию. ");
        }

        query.append("Привести 5");

        if (getRandom(task).nextBoolean()) {
            query.append(" первых результатов запроса.");
        } else {
            query.append(" последних результатов запроса.");
        }
    }

    @Override
    protected Table getRandomTable(Schema schema, Task task) {
        if (!allowed.containsKey(schema.getName())) {
            allowed.put(schema.getName(), removeForbidenElements(schema, forbiddenList));
        }

        Subtask12 randomSubtask12 = subtasks12.get(getRandom(task).nextInt(subtasks12.size())).clone();
        String tableName = randomSubtask12.tableAndColumn.trim().split(":")[0];

        return findAllowedTable(schema, tableName);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Subtask12 implements Cloneable {
        @XmlAttribute(name = "for")
        private String tableAndColumn;

        @Override
        protected Subtask12 clone() {
            try {
                Subtask12 subtaskClone07 = (Subtask12) super.clone();
                subtaskClone07.tableAndColumn = tableAndColumn;
                return subtaskClone07;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
