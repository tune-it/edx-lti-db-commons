package com.tuneit.course.lab2;

public class TaskData {
    static String[] getQueryTask1() {
        String queryVariant0 = "Выведите содержимое всех столбцов таблицы BOARDING_PASSES.";
        String queryVariant1 = "Выведите содержимое всех столбцов таблицы TICKET_FLIGHTS.";
        String queryVariant2 = "Выведите содержимое всех столбцов таблицы SEATS.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask1() {
        String answerVariant0 = "select * from boarding_passes as a;";
        String answerVariant1 = "select * from ticket_flights;";
        String answerVariant2 = "select * from seats;";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }
}
