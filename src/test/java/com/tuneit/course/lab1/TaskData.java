package com.tuneit.course.lab1;

public class TaskData {
    static String[] getQueryTask1() {
        return new String[]{"Выведите содержимое всех полей таблицы BOARDING_PASSES."};
    }

    static String[] getAnswerTask1() {
        return new String[]{"select * from BOARDING_PASSES as a;"};
    }

    //
    static String[] getQueryTask2() {
        String queryVariant0 = "Выведите все коды (book_ref), суммы (total_amount) для таблицы BOOKINGS.";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask2() {
        String answerVariant0 = "select book_ref, total_amount from BOOKINGS;";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask3() {
        String queryVariant0 = "Выведите все уникальные значения столбца идентификаторы пассажиров (passenger_id) из таблицы TICKETS.";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask3() {
        String answerVariant0 = "select distinct PASSENGER_ID from TICKETS;";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask4() {
        String queryVariant0 = "Выведите все номера (ticket_no), контактные данные (contact_data), номера (ticket_no) " +
                "для таблицы TICKETS. Отсортированные по столбцу имена пассажиров (passenger_name) по возрастанию.";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask4() {
        String answerVariant0 = "select ticket_no, contact_data, ticket_no from TICKETS ORDER BY passenger_name;";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask5() {
        String queryVariant0 = "Выведите все воздушные судна, которые удовлетовярют условию. Код самолёта должен быть равен \"CR2\".";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask5() {
        String answerVariant0 = "select * from aircrafts where aircraft_code=\'CR2\';";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask6() {
        String queryVariant0 = "Выведите все рейсы воздушных судов, которые удовлетовярют условию. Реальное время вылета самолёта должно быть не задано.";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask6() {
        String answerVariant0 = "select * from flights where actual_departure isnull;";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask7() {
        String queryVariant0 = "Выведите все рейсы воздушных судов, которые удовлетовярют условию." +
                " Идентификатор рейса должен быть больше, чем \"10000\" или реальное время вылета самолёта должно быть равно \"2017-07-16 02:53:00.000000\".";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask7() {
        String answerVariant0 = "select * from flights where flight_id > 10000 OR actual_departure = \'2017-07-16 02:53:00.000000\'";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask8() {
        String queryVariant0 = "Выведите самый дорогой билет, исходя из условия. Сумма забронированных билетов должна быть равна \"2017-08-12\".";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask8() {
        String answerVariant0 = "SELECT max(total_amount) FROM bookings WHERE book_date = '2017-08-12';";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask9() {
        String queryVariant0 = "Выдать номера рейсов, аэропорты отправления, идентификаторы рейсов, времена прибытий, времена планируемых отправлений из таблицы рейсов воздушного судна, статусы которых заканчиваются на 'C'. Отсортировать по столбцу 1 в порядке возрастания.";
        String queryVariant1 = "Выдать имена из таблицы аэропортов, города которых заканчиваются на 'ово'. Отсортировать по столбцу 1 в порядке возрастания.";
        String queryVariant2 = "Выдать суммы, номера билетов из таблицы состоявшихся полетов, тарифы которых заканчиваются на 'ess'. Отсортировать по столбцу 1 в порядке возрастания.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask9() {
        String answerVariant0 = "select flight_no, departure_airport, flight_id, actual_arrival, scheduled_departure from flights where status like '%C' order by 1;";
        String answerVariant1 = "select airport_name from airports where city like '%ово' order by 1;";
        String answerVariant2 = "select amount, ticket_no from ticket_flights where fare_conditions like '%ess' order by 1;";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }


    //

    static String[] getQueryTask10() {
        String queryVariant0 = "Вывести из таблицы рейсов воздушного судна времена прибытий, аэропорты отправления, времена отправлений, статусы, аэропорты прибытия с номерами рейсов: 'PG0490'.";
        String queryVariant1 = "Вывести из таблицы рейсов воздушного судна времена прибытий, аэропорты отправления, коды воздушных судов, времена отправлений с аэропортами прибытия: 'OVB', 'VKO'.";
        String queryVariant2 = "Вывести из таблицы рейсов воздушного судна аэропорты прибытия, коды воздушных судов, времена планируемых отправлений, времена планируемых прибытий с аэропортами отправления: 'TBW', 'KRR', 'EGO', 'REN'.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask10() {
        String answerVariant0 = "select actual_arrival, departure_airport, actual_departure, status, arrival_airport from flights where flight_no = 'PG0490';";
        String answerVariant1 = "select actual_arrival, departure_airport, aircraft_code, actual_departure from flights where arrival_airport in ('OVB', 'VKO')";
        String answerVariant2 = "select arrival_airport, aircraft_code, scheduled_departure, scheduled_arrival from flights where departure_airport in ('TBW', 'KRR', 'EGO', 'REN')";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }
}
