package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.parser.LabTask;
import com.tuneit.courses.db.parser.Subtype;
import com.tuneit.courses.db.parser.Type;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.NONE)
class Task05 extends LabTask {
    @Override
    protected void updateAnswer(Table table, Task task) {
        Type type = getRandomType(task);
        Subtype subtype = getRandomSubtype(task, type);

        answer.append("SELECT EXTRACT(")
                .append(subtype.getTime().trim())
                .append(" FROM ")
                .append(type.getColumn1().trim())
                .append(" - ")
                .append(type.getColumn2().trim())
                .append(") FROM ")
                .append(type.getTable().trim())
                .append(";");
    }

    @Override
    protected void updateQuery(Table table, Task task) {
        Type type = getRandomType(task);
        Subtype subtype = getRandomSubtype(task, type);

        query.append(prolog.trim())
                .append(" ")
                .append(type.getDescription().trim())
                .append(" ")
                .append(subtype.getValue().trim());
    }

    private Type getRandomType(Task task) {
        int typeRandomIndex = getRandom(task).nextInt(types.size());
        return types.get(typeRandomIndex);
    }

    private Subtype getRandomSubtype(Task task, Type type) {
        int timeRandomIndex = getRandom(task).nextInt(type.getSubtypes().size());
        return type.getSubtypes().get(timeRandomIndex);
    }
}
