package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;

@XmlAccessorType(XmlAccessType.NONE)
public class Task06 extends LabTask {
    @XmlElement(name = "query")
    private List<Query> queries;


    @Override
    protected void updateAnswer(Table table, Task task) {
        Query randomQuery = queries.get(getRandom(task).nextInt(queries.size())).clone();

        Collections.shuffle(randomQuery.queryColumns, getRandom(task));

        answer.append("Select ");

        Random randomForKind = getRandom(task);
        for (int i = 0; i < randomQuery.queryColumns.size(); i++) {
            List<Kind> kinds = randomQuery.queryColumns.get(i).kinds;
            Kind kind = kinds.get(randomForKind.nextInt(kinds.size()));
            answer.append(kind.answerType.trim());
            if (i != randomQuery.queryColumns.size() - 1) {
                answer.append(" || ");
            }
        }


        answer.append(" FROM " + table.getTableName() + ";");
    }

    @Override
    protected void updateQuery(Table table, Task task) {
        Query randomQuery = queries.get(getRandom(task).nextInt(queries.size())).clone();

        Collections.shuffle(randomQuery.queryColumns, getRandom(task));

        query.append(prolog.trim())
                .append(" ");

        for (int i = 0; i < randomQuery.queryColumns.size(); i++) {
            query.append(randomQuery.queryColumns.get(i).name.trim());
            if (i != randomQuery.queryColumns.size() - 1) {
                query.append(", ");
            }
        }

        query.append(" ").append(randomQuery.description.trim()).append(" ");

        Random randomForKind = getRandom(task);
        for (int i = 0; i < randomQuery.queryColumns.size(); i++) {
            List<Kind> kinds = randomQuery.queryColumns.get(i).kinds;
            Kind kind = kinds.get(randomForKind.nextInt(kinds.size()));
            query.append(kind.queryType.trim());
            if (i != randomQuery.queryColumns.size() - 1) {
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

        Query randomQuery = queries.get(getRandom(task).nextInt(queries.size())).clone();
        String tableName = randomQuery.table.trim();

        List<Table> tables = allowed.get(schema.getName());
        Optional<Table> tableOptional = tables.stream()
                .filter(table -> table.getTableName().equalsIgnoreCase(tableName)).findFirst();

        if (tableOptional.isPresent()) {
            return tableOptional.get();
        } else {
            throw new IllegalArgumentException("Table with name \"" + tableName + "\" don't found.");
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    private static class Query implements Cloneable {
        @XmlElement(name = "table")
        String table;
        @XmlElement(name = "description")
        String description;
        @XmlElement(name = "query-column")
        private List<QueryColumn> queryColumns = new ArrayList<>();

        @Override
        public Query clone() {
            try {
                Query queryCopy = (Query) super.clone();
                queryCopy.table = table;
                queryCopy.description = description;
                queryCopy.queryColumns = copyQueryColumns();
                return queryCopy;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }

        public List<QueryColumn> copyQueryColumns() {
            List<QueryColumn> copyQueryColumns = new ArrayList<>();
            for (QueryColumn queryColumn : queryColumns) {
                copyQueryColumns.add(queryColumn.clone());
            }
            return copyQueryColumns;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    private static class QueryColumn implements Cloneable {
        @XmlElement(name = "name")
        String name;
        @XmlElement(name = "kind")
        List<Kind> kinds;

        @Override
        public QueryColumn clone() {
            try {
                QueryColumn queryColumnCopy = (QueryColumn) super.clone();
                queryColumnCopy.name = name;
                queryColumnCopy.kinds = copyKinds();
                return queryColumnCopy;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }

        public List<Kind> copyKinds() {
            List<Kind> copyKinds = new ArrayList<>();
            for (Kind kind : kinds) {
                copyKinds.add(kind.clone());
            }
            return copyKinds;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    private static class Kind implements Cloneable {
        @XmlElement(name = "query-kind")
        String queryType;
        @XmlElement(name = "answer-kind")
        String answerType;

        @Override
        public Kind clone() {
            try {
                Kind kindCopy = (Kind) super.clone();
                kindCopy.queryType = queryType;
                kindCopy.answerType = answerType;
                return kindCopy;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
