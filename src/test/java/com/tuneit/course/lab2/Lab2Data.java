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
        return new String[]{"Выведите все уникальные значения номеров рейсов из таблицы рейсов воздушного судна, которые совершались на самолётах с количеством мест более 97."};
    }

    static String[] getAnswerTask3() {
        return new String[]{"SELECT distinct flight_no FROM flights\n" +
                "WHERE Flights.aircraft_code IN (\n" +
                "SELECT aircraft_code FROM Seats\n" +
                "GROUP BY aircraft_code\n" +
                "HAVING COUNT(seat_no)>97 ); "};
    }

    static String[] getQueryTask4() {
        return new String[]{"Напишите запрос для получения атрибутов из указанных таблиц, применив фильтры по указанным условиям. " +
                "Таблицы: рейсов воздушного судна, воздушных судов. " +
                "Атрибуты: все уникальные значения статусов из таблицы рейсов воздушного судна. " +
                "Фильтры: максимальная дальность полёта самолёта должна быть больше, чем \"4200\". " +
                "В запросе должен использоваться INNER JOIN."};
    }

    static String[] getAnswerTask4() {
        return new String[]{"select distinct status from flights join AIRCRAFTS on flights.AIRCRAFT_CODE = AIRCRAFTS.AIRCRAFT_CODE " +
                "where range > 4200;"};
    }

    static String[] getQueryTask5() {
        return new String[]{"Выведите самый дорогой билет (все поля таблицы), соответствующий следующему условию: " +
                "код бронирования должен быть равен \"044F42\" (данные из таблицы проданных билетов). " +
                "В запросе должен использоваться INNER JOIN."};
    }

    static String[] getAnswerTask5() {
        return new String[]{"select max(TICKET_FLIGHTS.amount) from TICKET_FLIGHTS inner join TICKETS on TICKET_FLIGHTS.ticket_no = TICKETS.ticket_no where TICKETS.book_ref = '044F42';"};
    }
}