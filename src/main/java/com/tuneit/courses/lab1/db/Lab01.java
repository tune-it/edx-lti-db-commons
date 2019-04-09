package com.tuneit.courses.lab1.db;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.Lab;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Table;
import com.tuneit.courses.lab1.db.schema.Schema01;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * @author serge
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Lab01 extends Lab {
    @XmlElements({
            @XmlElement(name = "task01", type = Task01.class),
            @XmlElement(name = "task02", type = Task02.class),
            @XmlElement(name = "task03", type = Task03.class),
            @XmlElement(name = "task04", type = Task04.class),
            @XmlElement(name = "task05", type = Task05.class),
            @XmlElement(name = "task06", type = Task06.class),
            @XmlElement(name = "task07", type = Task07.class),
            @XmlElement(name = "task08", type = Task08.class),
            @XmlElement(name = "task09", type = Task09.class),
            @XmlElement(name = "task10", type = Task10.class),
            @XmlElement(name = "task11", type = Task11.class),
            @XmlElement(name = "task12", type = Task12.class)
    })
    private List<Lab01Task> lab01Task = new ArrayList<>();

    @Override
    public List<Lab01Task> getLabTask() {
        return lab01Task;
    }

    @Override
    public String toString() {
        return "Lab01{" + super.toString() + ", lab01Task=" + lab01Task + '}';
    }

    public static class Task01 extends Lab01Task {

        @Override
        public String toString() {
            return "Task01{" + super.toString() + ", forbiddenList=" + forbiddenList + '}';
        }

        @Override
        public LabTaskQA generate(Schema01 schema01, Task task) {
            Table table = getRandomTable(schema01, task).clone();

            return new LabTaskQA(task.getId(), getProlog().trim() + " " + table.getTableName() + getEpilog().trim(),
                    "SELECT * FROM " + table.getTableName() + ";");
        }
    }

    private static class Task02 extends Lab01Task {
    }

    public static class Task03 extends Lab01Task {
        @Override
        public String toString() {
            return "Task01{" + super.toString() + ", forbiddenList=" + forbiddenList + '}';
        }

        @Override
        protected void updateQuery(Table table, Task task) {
            updateQueryPL(table, task);
        }

    }

    public static class Task04 extends Lab01Task {
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
            query.append(getProlog())
                    .append(" ")
                    .append(columns.get(getRandom(task).nextInt(columns.size())).getNamePlural())
                    .append(" ")
                    .append(getEpilog())
                    .append(" ")
                    .append(table.getNameGenitive())
                    .append('.');
        }
    }

    public static class Task05 extends Lab01Task {
        @XmlElement(name = "subtask05", required = true)
        private List<Subtask05> subtasks05 = new ArrayList<>();

        @Override
        protected void updateAnswer(Table table, Task task) {
            Subtask05 subtask05 = getRandomType(task);
            Option05 option05 = getRandomSubtype(task, subtask05);

            answer.append("SELECT EXTRACT(EPOCH FROM ")
                    .append(subtask05.column1.trim())
                    .append(" - ")
                    .append(subtask05.column2.trim())
                    .append(")")
                    .append(option05.time.trim())
                    .append(" FROM ")
                    .append(subtask05.table.trim())
                    .append(";");
        }

        @Override
        protected void updateQuery(Table table, Task task) {
            Subtask05 subtask05 = getRandomType(task);
            Option05 option05 = getRandomSubtype(task, subtask05);

            query.append(prolog.trim())
                    .append(" ")
                    .append(subtask05.description.trim())
                    .append(" ")
                    .append(option05.value.trim())
                    .append(".");
        }

        private Subtask05 getRandomType(Task task) {
            int typeRandomIndex = getRandom(task).nextInt(subtasks05.size());
            return subtasks05.get(typeRandomIndex);
        }

        private Option05 getRandomSubtype(Task task, Subtask05 subtask05) {
            int timeRandomIndex = getRandom(task).nextInt(subtask05.options05.size());
            return subtask05.options05.get(timeRandomIndex);
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        static class Subtask05 {
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

        }

        @XmlAccessorType(XmlAccessType.FIELD)
        static class Option05 {
            @XmlElement(name = "time")
            private String time;

            @XmlElement(name = "value")
            private String value;
        }
    }

    public static class Task06 extends Lab01Task {
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
                    .append(". Вывод представить в формате \'")
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
        protected Table getRandomTable(Schema01 schema01, Task task) {
            if (!allowed.containsKey(schema01.getName())) {
                allowed.put(schema01.getName(), removeForbiddenElements(schema01, forbiddenList));
            }

            Subtask06 randomSubtask06 = subtasks06.get(getRandom(task).nextInt(subtasks06.size())).clone();
            String tableName = randomSubtask06.table.trim();

            return findAllowedTable(schema01, tableName);
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

    public static class Task07 extends Lab01Task {
        @XmlElement(name = "subtask07")
        private List<Subtask07> subtasks07;


        @Override
        protected void updateAnswer(Table table, Task task) {
            Subtask07 randomSubtask07 = subtasks07.get(getRandom(task).nextInt(subtasks07.size())).clone();

            String columnName = randomSubtask07.tableAndColumn.trim().split(":")[1];
            table.findColumnAndDelete(columnName);

            String position = getRandomPosition(randomSubtask07, task);

            answer.append("SELECT ");
            writeColumnFromTable(answer, table.getColumns(), task);
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
            Subtask07 randomSubtask07 = subtasks07.get(getRandom(task).nextInt(subtasks07.size())).clone();

            String columnName = randomSubtask07.tableAndColumn.trim().split(":")[1];
            String columnNamePL = table.findColumnAndDelete(columnName).getNamePlural();

            String position = getRandomPosition(randomSubtask07, task);

            query.append(prolog.trim())
                    .append(" ");
            writeColumnFromTablePL(query, table.getColumns(), task);
            query.append(" из таблицы ")
                    .append(table.getNameGenitive())
                    .append(", ")
                    .append(columnNamePL)
                    .append(" которых содержат в названии \'")
                    .append(position)
                    .append("\'.");
        }

        private String getRandomPosition(Subtask07 randomSubtask07, Task task) {
            boolean randomLeftOrRightPosition = getRandom(task).nextBoolean();

            if (randomLeftOrRightPosition) {
                int randomLeftPosition = getRandom(task).nextInt(randomSubtask07.leftPosition.size());
                return randomSubtask07.leftPosition.get(randomLeftPosition);
            } else {
                int randomRightPosition = getRandom(task).nextInt(randomSubtask07.rightPosition.size());
                return randomSubtask07.rightPosition.get(randomRightPosition);
            }
        }

        @Override
        protected Table getRandomTable(Schema01 schema01, Task task) {
            if (!allowed.containsKey(schema01.getName())) {
                allowed.put(schema01.getName(), removeForbiddenElements(schema01, forbiddenList));
            }

            Subtask07 randomSubtask07 = subtasks07.get(getRandom(task).nextInt(subtasks07.size())).clone();
            String tableName = randomSubtask07.tableAndColumn.trim().split(":")[0];

            return findAllowedTable(schema01, tableName);
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

    public static class Task08 extends Lab01Task {
        @XmlElement(name = "subtask08")
        private List<Subtask08> subtasks08;


        @Override
        protected void updateAnswer(Table table, Task task) {
            Subtask08 randomSubtask08 = subtasks08.get(getRandom(task).nextInt(subtasks08.size())).clone();

            String columnName = randomSubtask08.tableAndColumn.trim().split(":")[1];
            table.findColumnAndDelete(columnName);
            table.getColumns().removeIf(
                    column -> column.getColumnName().equalsIgnoreCase(columnName) || randomSubtask08.forbidden.contains(column.getColumnName().toLowerCase()));

            int randomPosition = getRandom(task).nextInt(randomSubtask08.leftPosition.size());
            String position = randomSubtask08.leftPosition.get(randomPosition);

            answer.append("SELECT ");
            writeColumnFromTable(answer, table.getColumns(), task);
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

            String columnName = randomSubtask08.tableAndColumn.trim().split(":")[1];
            String columnNamePL = table.findColumnAndDelete(columnName).getNamePlural();
            table.getColumns().removeIf(
                    column -> randomSubtask08.forbidden.contains(column.getColumnName().toLowerCase()));

            int randomPosition = getRandom(task).nextInt(randomSubtask08.leftPosition.size());
            String position = randomSubtask08.leftPosition.get(randomPosition);

            query.append(prolog.trim())
                    .append(" ");
            writeColumnFromTablePL(query, table.getColumns(), task);
            query.append(" из таблицы ")
                    .append(table.getNameGenitive())
                    .append(", ")
                    .append(columnNamePL)
                    .append(" которых начинаются на \'")
                    .append(position)
                    .append("\'. Отсортировать по столбцу ")
                    .append(columnNamePL)
                    .append(" в порядке возрастания.");
        }

        @Override
        protected Table getRandomTable(Schema01 schema01, Task task) {
            if (!allowed.containsKey(schema01.getName())) {
                allowed.put(schema01.getName(), removeForbiddenElements(schema01, forbiddenList));
            }

            Subtask08 randomSubtask08 = subtasks08.get(getRandom(task).nextInt(subtasks08.size())).clone();
            String tableName = randomSubtask08.tableAndColumn.trim().split(":")[0];

            return findAllowedTable(schema01, tableName);
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

            @XmlElement(name = "forbidden")
            @XmlList
            private List<String> forbidden = new ArrayList<>();

            @Override
            protected Subtask08 clone() {
                try {
                    Subtask08 subtaskClone07 = (Subtask08) super.clone();
                    subtaskClone07.tableAndColumn = tableAndColumn;
                    subtaskClone07.leftPosition = copyList(leftPosition);
                    subtaskClone07.rightPosition = copyList(rightPosition);
                    subtaskClone07.forbidden = copyList(forbidden);
                    return subtaskClone07;
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            private List<String> copyList(List<String> positions) {
                if (positions != null) {
                    return new ArrayList<>(positions);
                } else {
                    return null;
                }
            }
        }
    }

    public static class Task09 extends Lab01Task {
        @XmlElement(name = "subtask09")
        private List<Subtask09> subtasks09;


        @Override
        protected void updateAnswer(Table table, Task task) {
            Subtask09 randomSubtask09 = subtasks09.get(getRandom(task).nextInt(subtasks09.size())).clone();


            String columnName = randomSubtask09.tableAndColumn.trim().split(":")[1];

            table.getColumns().removeIf(
                    column -> column.getColumnName().equalsIgnoreCase(columnName) || randomSubtask09.forbidden.contains(column.getColumnName().toLowerCase()));

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

            String columnName = randomSubtask09.tableAndColumn.trim().split(":")[1];
            String columnNamePL = table.findColumnAndDelete(columnName).getNamePlural();
            table.getColumns().removeIf(
                    column -> randomSubtask09.forbidden.contains(column.getColumnName().toLowerCase()));

            int randomPosition = getRandom(task).nextInt(randomSubtask09.rightPosition.size());
            String position = randomSubtask09.rightPosition.get(randomPosition);

            query.append(prolog.trim())
                    .append(" ");
            writeColumnFromTablePL(query, table.getColumns(), task);
            query.append(" из таблицы ")
                    .append(table.getNameGenitive())
                    .append(", ")
                    .append(columnNamePL)
                    .append(" которых заканчиваются на \'")
                    .append(position)
                    .append("\'. Отсортировать по столбцу 1 в порядке возрастания.");//TODO add random for ordering
        }

        @Override
        protected Table getRandomTable(Schema01 schema01, Task task) {
            if (!allowed.containsKey(schema01.getName())) {
                allowed.put(schema01.getName(), removeForbiddenElements(schema01, forbiddenList));
            }

            Subtask09 randomSubtask09 = subtasks09.get(getRandom(task).nextInt(subtasks09.size())).clone();
            String tableName = randomSubtask09.tableAndColumn.trim().split(":")[0];

            return findAllowedTable(schema01, tableName);
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

            @XmlElement(name = "forbidden")
            @XmlList
            private List<String> forbidden = new ArrayList<>();

            @Override
            protected Subtask09 clone() {
                try {
                    Subtask09 subtaskClone09 = (Subtask09) super.clone();
                    subtaskClone09.tableAndColumn = tableAndColumn;
                    subtaskClone09.leftPosition = copyList(leftPosition);
                    subtaskClone09.rightPosition = copyList(rightPosition);
                    subtaskClone09.forbidden = copyList(forbidden);
                    return subtaskClone09;
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            private List<String> copyList(List<String> positions) {
                if (positions != null) {
                    return new ArrayList<>(positions);
                } else {
                    return null;
                }
            }
        }
    }

    public static class Task10 extends Lab01Task {
        @XmlElement(name = "subtask10")
        private List<Subtask10> subtasks10;


        @Override
        protected void updateAnswer(Table table, Task task) {
            Subtask10 randomSubtask10 = subtasks10.get(getRandom(task).nextInt(subtasks10.size())).clone();

            String columnName = randomSubtask10.tableAndColumn.trim().split(":")[1];
            table.findColumnAndDelete(columnName);

            List<String> randomOptions = getRandomOptions(randomSubtask10.options, task);

            answer.append("SELECT ");
            writeColumnFromTable(answer, table.getColumns(), task);
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

        @Override
        protected void updateQuery(Table table, Task task) {
            Subtask10 randomSubtask10 = subtasks10.get(getRandom(task).nextInt(subtasks10.size())).clone();

            String columnName = randomSubtask10.tableAndColumn.trim().split(":")[1];
            String columnNameCreativePlural = table.findColumnAndDelete(columnName).getNameCreativePlural();

            List<String> randomOptions = getRandomOptions(randomSubtask10.options, task);

            query.append(prolog.trim())
                    .append(" из таблицы ")
                    .append(table.getNameGenitive())
                    .append(" ");
            writeColumnFromTablePL(query, table.getColumns(), task);
            query.append(" ")
                    .append(getWithPreposition(columnNameCreativePlural))
                    .append(" ")
                    .append(columnNameCreativePlural)
                    .append(": ");

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

        private String getWithPreposition(String word) {
            boolean rule1 = withPrepositionRuleConsonant(word.charAt(0)) && isConsonant(word.charAt(1));
            boolean rule2 = word.charAt(0) == 'щ';
            boolean rule3 = word.toLowerCase().startsWith("ль") && isConsonant(word.charAt(1));

            return rule1 || rule2 || rule3 ? "со" : "с";
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
        protected Table getRandomTable(Schema01 schema01, Task task) {
            if (!allowed.containsKey(schema01.getName())) {
                allowed.put(schema01.getName(), removeForbiddenElements(schema01, forbiddenList));
            }

            Subtask10 randomSubtask10 = subtasks10.get(getRandom(task).nextInt(subtasks10.size())).clone();
            String tableName = randomSubtask10.tableAndColumn.trim().split(":")[0];

            return findAllowedTable(schema01, tableName);
        }

        private boolean isConsonant(char letter) {
            Set<Character> vowelLetters = new HashSet<>(Arrays.asList('а', 'я', 'о', 'ё', 'у', 'ю', 'е', 'э', 'и', 'ы'));
            return !vowelLetters.contains(Character.toLowerCase(letter));
        }

        private boolean withPrepositionRuleConsonant(char letter) {
            Set<Character> vowelLetters = new HashSet<>(Arrays.asList('с', 'з', 'ш', 'ж', 'л', 'р', 'м', 'в'));
            return vowelLetters.contains(Character.toLowerCase(letter));
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

    public static class Task11 extends Lab01Task {
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
            String columnNameCreativePlural = table.findColumn(columnName).getNameCreativePlural();

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
        protected Table getRandomTable(Schema01 schema01, Task task) {
            if (!allowed.containsKey(schema01.getName())) {
                allowed.put(schema01.getName(), removeForbiddenElements(schema01, forbiddenList));
            }

            Subtask11 randomSubtask11 = subtasks11.get(getRandom(task).nextInt(subtasks11.size())).clone();
            String tableName = randomSubtask11.tableAndColumn.trim().split(":")[0];

            return findAllowedTable(schema01, tableName);
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

    public static class Task12 extends Lab01Task {
        @XmlElement(name = "subtask12")
        private List<Subtask12> subtasks12 = new ArrayList<>();


        @Override
        protected void updateAnswer(Table table, Task task) {
            Subtask12 randomSubtask11 = subtasks12.get(getRandom(task).nextInt(subtasks12.size())).clone();

            String columnName = randomSubtask11.tableAndColumn.trim().split(":")[1];

            String ordering = getRandom(task).nextBoolean() ? ") ASC LIMIT 5;" : ") DESC LIMIT 5;";

            answer.append("SELECT COUNT(")
                    .append(columnName)
                    .append(") FROM ")
                    .append(table.getTableName())
                    .append(" GROUP BY ")
                    .append(columnName)
                    .append(" ORDER BY COUNT(")
                    .append(columnName)
                    .append(ordering);
        }

        @Override
        protected void updateQuery(Table table, Task task) {
            Subtask12 randomSubtask12 = subtasks12.get(getRandom(task).nextInt(subtasks12.size())).clone();

            String columnName = randomSubtask12.tableAndColumn.trim().split(":")[1];
            String columnNameGenitivePlural = table.findColumn(columnName).getNameGenitivePlural();

            String ordering = getRandom(task).nextBoolean() ?
                    "расположить по возрастанию. " : "расположить по убыванию. ";

            query.append(prolog.trim())
                    .append(" ")
                    .append(columnNameGenitivePlural)
                    .append(" из таблицы ")
                    .append(table.getNameGenitive())
                    .append(". Результат ")
                    .append(ordering)
                    .append("Привести 5")
                    .append(" первых результатов запроса.");
        }

        @Override
        protected Table getRandomTable(Schema01 schema01, Task task) {
            if (!allowed.containsKey(schema01.getName())) {
                allowed.put(schema01.getName(), removeForbiddenElements(schema01, forbiddenList));
            }

            Subtask12 randomSubtask12 = subtasks12.get(getRandom(task).nextInt(subtasks12.size())).clone();
            String tableName = randomSubtask12.tableAndColumn.trim().split(":")[0];

            return findAllowedTable(schema01, tableName);
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

