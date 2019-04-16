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
        String queryVariant0 = "Выведите все забронированые билеты, которые удовлетовярют условию. " +
                "Общая сумма покупки должна быть больше, чем \"12000\" или бронь должна быть сделана позже \"2017-07-21\".";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask7() {
        String answerVariant0 = "select * from bookings where total_amount > 12000 OR book_date > \'2017-07-21\'";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask8() {
        String queryVariant0 = "Выведите самый дорогой билет, исходя из условия. Бронь должна быть сделана позже \"2017-08-12\".";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask8() {
        String answerVariant0 = "SELECT max(total_amount) FROM bookings WHERE book_date > '2017-08-12';";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask9() {
        String queryVariant0 = "Выведите все номера, номера мест для таблицы посадочных талонов. Отсортированные по столбцу идентификаторы рейсов по убыванию. Вывести последние 3 строки.";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask9() {
        String answerVariant0 = "select boarding_no, seat_no from boarding_passes order by flight_id DESC limit 3";
        return new String[]{answerVariant0};
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
