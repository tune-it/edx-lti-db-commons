package com.tuneit.course.lab2;

public class Lab2Data {
    static String[] getQueryTask1() {
        return new String[]{"Выдать номера, номера билетов из таблицы посадочных талонов, номера мест которых заканчиваются на \"F\"."};
    }

    static String[] getAnswerTask1() {
        return new String[]{"select boarding_no, ticket_no from BOARDING_PASSES as a where seat_no like '%F';"};
    }

    static String[] getQueryTask2() {
        return new String[]{"Выведите коды воздушных судов, тарифы из таблицы посадочных мест, заменив значения тарифов 'Comfort' и 'Business' на 'Дорого', а 'Economy' на 'Дёшево'."};
    }

    static String[] getAnswerTask2() {
        return new String[]{"select aircraft_code, " +
                "case " +
                "when fare_conditions in ('Business', 'Comfort') then 'Дорого' " +
                "else 'Дешёво' " +
                "end " +
                "from seats;"};
    }

    static String[] getQueryTask3() {
        return new String[]{"Выведите все времена планируемых прибытий, идентификаторы рейсов, времена прибытий, номера рейсов из таблицы рейсов воздушного судна, которые совершались на самолётах с количеством мест менее 130."};
    }

    static String[] getAnswerTask3() {
        return new String[]{"SELECT SCHEDULED_Arrival, FLIGHT_NO FROM Flights\n" +
                "WHERE Flights.aircraft_code IN (\n" +
                "SELECT aircraft_code FROM Seats\n" +
                "GROUP BY aircraft_code\n" +
                "HAVING COUNT(seat_no)<116 ); "};
    }

    static String[] getQueryTask4() {
        return new String[]{"Сделать запрос для получения атрибутов из указанных таблиц, применив фильтры по указанным условиям." +
                " Таблицы: посадочных мест, воздушных судов." +
                " Атрибуты: все номера мест из таблицы посадочных мест." +
                " Фильтры: максимальная дальность полёта самолёта должна быть больше, чем \"3000\"." +
                " В запросе должен использоваться INNER JOIN."};
    }

    static String[] getAnswerTask4() {
        return new String[]{"select seat_no from SEATS join AIRCRAFTS on SEATS.AIRCRAFT_CODE = AIRCRAFTS.AIRCRAFT_CODE " +
                "where range > 3000;"};
    }

    static String[] getQueryTask5() {
        return new String[]{"Выведите самое дешёвое бронирование, соответствующее условию: " +
                "идентификатор пассажира должен быть равен \"3293 031739\"" +
                "(данные из таблицы проданных билетов). " +
                "В запросе должен использоваться INNER JOIN."};
    }

    static String[] getAnswerTask5() {
        return new String[]{"select min(BOOKINGS.total_amount) from BOOKINGS inner join TICKETS on BOOKINGS.BOOK_REF = TICKETS.BOOK_REF where TICKETS.passenger_id = '3293 031739';"};
    }
}