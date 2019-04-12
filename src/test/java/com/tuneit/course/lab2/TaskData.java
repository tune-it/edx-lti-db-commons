package com.tuneit.course.lab2;

public class TaskData {
    static String[] getQueryTask1() {
        String queryVariant0 = "Сделать запрос для получения атрибутов из указанных таблиц, применив фильтры по указанным условиям:\n" +
                "flights, aircrafts.\n" +
                "Вывести атрибуты: flights.scheduled_arrival, aircrafts.model.\n" +
                "Фильтры (AND): \n" +
                "a) aircrafts.range < 5000.\n" +
                "b) flights.status = Scheduled.\n" +
                "Вид соединения: INNER JOIN.";
        String queryVariant1 = "Выведите содержимое всех столбцов таблицы TICKET_FLIGHTS.";
        String queryVariant2 = "Выведите содержимое всех столбцов таблицы SEATS.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask1() {
        String answerVariant0 = "select flights.scheduled_arrival, aircrafts.model " +
                "from aircrafts INNER JOIN flights ON (aircrafts.aircraft_code = flights.aircraft_code) " +
                "where aircrafts.range < 5000 AND flights.status = 'Scheduled';";
        String answerVariant1 = "select * from ticket_flights;";
        String answerVariant2 = "select * from seats;";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }
}
