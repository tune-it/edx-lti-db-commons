package com.tuneit.courses.db;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author serge
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Lab02 extends Lab {
    @XmlElements({
        @XmlElement(name="task01", type=Task01.class),
        @XmlElement(name="task02", type=Task02.class),
        @XmlElement(name="task03", type=Task03.class),
        @XmlElement(name="task04", type=Task04.class),
        @XmlElement(name="task05", type=Task05.class),
        @XmlElement(name="task06", type=Task06.class),
        @XmlElement(name="task07", type=Task07.class),
        @XmlElement(name="task08", type=Task08.class),
        @XmlElement(name="task09", type=Task09.class),
        @XmlElement(name="task10", type=Task10.class),
        @XmlElement(name="task11", type=Task11.class),
        @XmlElement(name="task12", type=Task12.class)
    })
    private List<LabTask> labTask = new ArrayList<>();

    @Override
    public List<LabTask> getLabTask() {
        return labTask;
    }

    public void setLabTask(List<LabTask> labTask) {
        this.labTask = labTask;
    }

    @Override
    public String toString() {
        return "Lab02{" + super.toString()+", labTask=" + labTask + '}';
    }

    public static class Task01 extends LabTask {

        @Override
        public String toString() {
            return "Task01{" + super.toString()+ ", forbiddenList=" + forbiddenList + '}';
        }

        @Override
        public LabTaskQA generate(Schema schema, Task task) {
            Table table = getRandomTable(schema, task).clone();

            return new LabTaskQA(task.getId(), getProlog() + table.getTableName() + getEpilog(),
                    "SELECT * FROM " + table.getTableName() + ";");
        }
    }

    public static class Task02 extends LabTask {
    }

    public static class Task03 extends LabTask {
        @Override
        public String toString() {
            return "Task01{" + super.toString() + ", forbiddenList=" + forbiddenList + '}';
        }

        @Override
        protected void updateQuery(Table table, Task task) {
            updateQueryPL(table, task);
        }

    }

    public static class Task04 extends LabTask {
        @Override
        public String toString() {
            return "Task01{" + super.toString() + ", forbiddenList=" + forbiddenList + '}';
        }

        @Override
        protected void updateAnswer(Table table, Task task) {
            List<Column> columns = table.getColumns();

            answer.append("SELECT DISTINCT ");
            answer.append(columns.get(getRandom(task).nextInt(columns.size())).getColumnName());
            answer.append(" FROM ").append(table.getTableName()).append(';');
        }

        @Override
        protected void updateQuery(Table table, Task task) {
            updateQueryPL(table, task);
        }

        @Override
        protected void updateQueryPL(Table table, Task task) {
            List<Column> columns = table.getColumns();
            query.append(getProlog());
            query.append(columns.get(getRandom(task).nextInt(columns.size())).getNamePlural());
            query.append(getEpilog()).append(table.getNameGenitive()).append('.');
        }
    }

    public static class Task05 extends LabTask {
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
                    .append(option05.getValue().trim())
                    .append(".");
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

    public static class Task06 extends LabTask {
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

    public static class Task07 extends LabTask {
        @XmlElement(name = "subtask07")
        private List<Subtask07> subtasks07;


        @Override
        protected void updateAnswer(Table table, Task task) {
            Subtask07 randomSubtask06 = subtasks07.get(getRandom(task).nextInt(subtasks07.size())).clone();

            answer.append("SELECT ");

            String columnName = randomSubtask06.tableAndColumn.trim().split(":")[1];

            table.getColumns().removeIf(column -> column.getColumnName().equalsIgnoreCase(columnName));

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

            answer.append(" FROM ")
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

            table.getColumns().removeIf(column -> column.getColumnName().equalsIgnoreCase(columnName));

            writeColumnFromTablePL(query, table.getColumns(), task);

            query.append(" для таблицы ")
                    .append(table.getNameGenitive())
                    .append(", ")
                    .append(columnNamePL)
                    .append(" которых содержат в названии \'")
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

    public static class Task08 extends LabTask {
        @XmlElement(name = "subtask08")
        private List<Subtask08> subtasks08;


        @Override
        protected void updateAnswer(Table table, Task task) {
            Subtask08 randomSubtask08 = subtasks08.get(getRandom(task).nextInt(subtasks08.size())).clone();

            answer.append("SELECT ");

            String columnName = randomSubtask08.tableAndColumn.trim().split(":")[1];

            table.getColumns().removeIf(column -> column.getColumnName().equalsIgnoreCase(columnName));

            writeColumnFromTable(answer, table.getColumns(), task);

            int randomPosition = getRandom(task).nextInt(randomSubtask08.leftPosition.size());
            String position = randomSubtask08.leftPosition.get(randomPosition);

            answer.append(" FROM ")
                    .append(table.getTableName())
                    .append(" WHERE ")
                    .append(columnName)
                    .append(" LIKE \'")
                    .append(position)
                    .append("%\' ORDER BY ")
                    .append(columnName)
                    .append(";");
        }

        @Override
        protected void updateQuery(Table table, Task task) {
            Subtask08 randomSubtask08 = subtasks08.get(getRandom(task).nextInt(subtasks08.size())).clone();

            query.append(prolog.trim())
                    .append(" ");

            String columnName = randomSubtask08.tableAndColumn.trim().split(":")[1];

            String columnNamePL = table.getColumns().stream()
                    .filter(column -> column.getColumnName().equalsIgnoreCase(columnName))
                    .findFirst().get().getNamePlural();

            table.getColumns().removeIf(column -> column.getColumnName().equalsIgnoreCase(columnName));

            writeColumnFromTablePL(query, table.getColumns(), task);

            int randomPosition = getRandom(task).nextInt(randomSubtask08.leftPosition.size());
            String position = randomSubtask08.leftPosition.get(randomPosition);

            query.append(", ")
                    .append(columnNamePL)
                    .append(" которых начинаются на \'")
                    .append(position)
                    .append("\'. Отсортировать по столбцу \'")
                    .append(columnNamePL)
                    .append("\' в порядке возрастания.");
        }

        @Override
        protected Table getRandomTable(Schema schema, Task task) {
            if (!allowed.containsKey(schema.getName())) {
                allowed.put(schema.getName(), removeForbidenElements(schema, forbiddenList));
            }

            Subtask08 randomSubtask08 = subtasks08.get(getRandom(task).nextInt(subtasks08.size())).clone();
            String tableName = randomSubtask08.tableAndColumn.trim().split(":")[0];

            return findAllowedTable(schema, tableName);
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        private static class Subtask08 implements Cloneable {
            @XmlAttribute(name = "for")
            private String tableAndColumn;


            @XmlElement(name = "left-position")
            @XmlList
            private List<String> leftPosition;

            @XmlElement(name = "right-position")
            @XmlList
            private List<String> rightPosition;

            @Override
            protected Subtask08 clone() {
                try {
                    Subtask08 subtaskClone07 = (Subtask08) super.clone();
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

    public static class Task09 extends LabTask {
        @XmlElement(name = "subtask09")
        private List<Subtask09> subtasks09;


        @Override
        protected void updateAnswer(Table table, Task task) {
            Subtask09 randomSubtask09 = subtasks09.get(getRandom(task).nextInt(subtasks09.size())).clone();


            String columnName = randomSubtask09.tableAndColumn.trim().split(":")[1];

            table.getColumns().removeIf(column -> column.getColumnName().equalsIgnoreCase(columnName));

            int randomPosition = getRandom(task).nextInt(randomSubtask09.rightPosition.size());
            String position = randomSubtask09.rightPosition.get(randomPosition);

            answer.append("SELECT ");
            writeColumnFromTable(answer, table.getColumns(), task);
            answer.append(" FROM ")
                    .append(table.getTableName())
                    .append(" WHERE ")
                    .append(columnName)
                    .append(" LIKE \'%")
                    .append(position)
                    .append("\' ORDER BY 1;");
        }

        @Override
        protected void updateQuery(Table table, Task task) {
            Subtask09 randomSubtask09 = subtasks09.get(getRandom(task).nextInt(subtasks09.size())).clone();

            query.append(prolog.trim())
                    .append(" ");

            String columnName = randomSubtask09.tableAndColumn.trim().split(":")[1];

            String columnNamePL = table.getColumns().stream()
                    .filter(column -> column.getColumnName().equalsIgnoreCase(columnName))
                    .findFirst().get().getNamePlural();

            table.getColumns().removeIf(column -> column.getColumnName().equalsIgnoreCase(columnName));

            writeColumnFromTablePL(query, table.getColumns(), task);

            int randomPosition = getRandom(task).nextInt(randomSubtask09.rightPosition.size());
            String position = randomSubtask09.rightPosition.get(randomPosition);

            query.append(", ")
                    .append(columnNamePL)
                    .append(" которых заканчиваются на \'")
                    .append(position)
                    .append("\'. Отсортировать по столбцу 1.");
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

    public static class Task10 extends LabTask {
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
            answer.append(";");
        }

        private List<String> getRandomOptions(List<String> options, Task task) {
            Collections.shuffle(options, getRandom(task));

            List<String> randomOption = new ArrayList<>();
            randomOption.add(options.get(0));
            for (int i = 1; i < getRandom(task).nextInt(options.size()); i++) {
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

            String columnNameCreative = table.getColumns().stream()
                    .filter(column -> column.getColumnName().equalsIgnoreCase(columnName))
                    .findFirst().get().getNameCreativePlural();

            writeColumnFromTablePL(query, table.getColumns(), task);

            query.append(" c ")
                    .append(columnNameCreative)
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

    public static class Task11 extends LabTask {
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
        }

        @Override
        protected void updateQuery(Table table, Task task) {
            Subtask11 randomSubtask11 = subtasks11.get(getRandom(task).nextInt(subtasks11.size())).clone();

            String columnName = randomSubtask11.tableAndColumn.trim().split(":")[1];
            String columnNameCreativePlural = table.getColumns().stream()
                    .filter(column -> column.getColumnName().equalsIgnoreCase(columnName))
                    .findFirst().get().getNameCreativePlural();


            int randomPosition = getRandom(task).nextInt(randomSubtask11.options.size());
            String option = randomSubtask11.options.get(randomPosition);

            query.append(prolog.trim())
                    .append(" ")
                    .append(table.getNameGenitive())
                    .append(" c ")
                    .append(columnNameCreativePlural)
                    .append(" \'")
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

    public static class Task12 extends LabTask {
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
}

