package com.tuneit.courses.lab2.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Aggregation;
import com.tuneit.courses.db.schema.Condition;
import com.tuneit.courses.db.schema.ConditionTable;
import com.tuneit.courses.lab2.Lab2Task;
import com.tuneit.courses.lab2.schema.Reference;
import com.tuneit.courses.lab2.schema.Schema02;

import java.util.Random;

public class Lab2Task5 extends Lab2Task {
    @Override
    public LabTaskQA generate(Schema02 schema02, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        Aggregation aggregation = schema02.getRandomAggregation(random);
        Reference.ChainTable chainTable = schema02.getRandomChainTable(random,
                schema02.findTableBySqlName(aggregation.getTableSqlName()));

        schema02.getConditionTables().removeIf(conditionTable ->
                !conditionTable.getSqlTableName().equalsIgnoreCase(chainTable.getRightTable().getTableName()));
        ConditionTable conditionTable = schema02.getConditionTables().get(0);
        conditionTable.getConditions().removeIf(condition ->
                condition.getSqlColumnName().equalsIgnoreCase(chainTable.getRightColumn().getColumnName()));
        Condition condition = conditionTable.getRandomCondition(random);
        String option = condition.getRandomOption(random);
        Condition.PairSign conditionSign = condition.getConditionSign(random);

        query.append("Выведите ")
                .append(aggregation.getColumnFunctionNativeName())
                .append(": ").append(condition.getNativeColumnName())
                .append(conditionSign.getSignConditionNative())
                .append("\"")
                .append(option)
                .append("\"(данные из таблицы ")
                .append(chainTable.getRightTable().getNameGenitive())
                .append("). В запросе должен использоваться INNER JOIN.");

        answer.append("select ")
                .append(aggregation.getFunctionSqlName())
                .append("(")
                .append(chainTable.getLeftTable().getTableName())
                .append(".")
                .append(aggregation.getColumnSqlName())
                .append(") from ")
                .append(chainTable.getLeftTable().getTableName())
                .append(" inner join ")
                .append(chainTable.getRightTable().getTableName())
                .append(" on ")
                .append(chainTable.getLeftTable().getTableName())
                .append(".")
                .append(chainTable.getLeftColumn().getColumnName())
                .append(" = ")
                .append(chainTable.getRightTable().getTableName())
                .append(".")
                .append(chainTable.getRightColumn().getColumnName())
                .append(" where ")
                .append(chainTable.getRightTable().getTableName())
                .append(".")
                .append(condition.getSqlColumnName())
                .append(conditionSign.getSignConditionSql())
                .append("'")
                .append(option)
                .append("';");

        System.out.println(query);
        System.out.println(answer);

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}