package com.tuneit.course.lab2;

public class Lab2Data {
    static String[] getQueryTask1() {
        return new String[]{"Выведите содержимое полей коды, максимально возможные дистанции из таблицы воздушных судов, модели которых заканчиваются на \"100\"."};
    }

    static String[] getAnswerTask1() {
        return new String[]{"select aircraft_code, range from aircrafts as a where model like '%100';"};
    }

    static String[] getQueryTask2() {
        return new String[]{"Выведите содержимое следующих полей из таблицы рейсов воздушного судна: времена планируемых прибытий, аэропорты прибытия, статусы. Замените значения статусов 'Arrived' на 'Прибыл', а остальные на 'Не прибыл'."};
    }

    static String[] getAnswerTask2() {
        return new String[]{"select scheduled_arrival, arrival_airport, " +
                "case " +
                "when status = 'Arrived' then 'Прибыл' " +
                "else 'Не прибыл' " +
                "end " +
                "from flights;"};
    }

    static String[] getQueryTask3() {
        return new String[]{"Выведите все уникальные значения времён прибытий из таблицы рейсов воздушного судна, " +
                "которые совершались на самолётах с количеством мест менее 50."};
    }

    static String[] getAnswerTask3() {
        return new String[]{"SELECT DISTINCT actual_arrival \n" +
                "FROM flights\n" +
                "WHERE aircraft_code IN (\n" +
                "SELECT aircraft_code\n" +
                "FROM seats\n" +
                "GROUP BY aircraft_code\n" +
                "HAVING count(seat_no)<50)"};
    }

    static String[] getQueryTask4() {
        return new String[]{"Напишите запрос для получения полей из объединённых таблиц, удовлетворяющих указанным условиям. " +
                "Таблицы: проданных билетов, состоявшихся полетов. " +
                "Поля: все уникальные значения контактных данных из таблицы проданных билетов. " +
                "Условия: тариф должен быть равен \"Economy\". " +
                "В запросе должен использоваться INNER JOIN."};
    }

    static String[] getAnswerTask4() {
        return new String[]{"select distinct contact_data " +
                "from tickets inner join TICKET_FLIGHTS " +
                "on tickets.ticket_no = TICKET_FLIGHTS.ticket_no " +
                "where TICKET_FLIGHTS.fare_conditions = 'Economy';\n"};
    }

    static String[] getQueryTask5() {
        return new String[]{"Выведите среднюю стоимость перелётов, соответствующих следующему условию: " +
                "код бронирования должен быть равен \"044F42\" (данные из таблицы проданных билетов). " +
                "В запросе должен использоваться INNER JOIN."};
    }

    static String[] getAnswerTask5() {
        return new String[]{"select avg(TICKET_FLIGHTS.amount) from TICKET_FLIGHTS inner join TICKETS on TICKET_FLIGHTS.ticket_no = TICKETS.ticket_no where TICKETS.book_ref = '044F42';"};
    }
}