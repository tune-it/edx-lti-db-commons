package com.tuneit.course.lab1;

public class Lab1Data {
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
        //Выведите все уникальные идентификаторы пассажиров (passenger_id) из таблицы TICKETS.
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask3() {
        String answerVariant0 = "select distinct PASSENGER_ID from TICKETS;";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask4() {
        String queryVariant0 = "Выведите все номера (ticket_no), контактные данные (contact_data), номера (ticket_no) для таблицы TICKETS. Отсортированные по столбцу имена пассажиров (passenger_name) по возрастанию.";
        //Выведите все номера (ticket_no), контактные данные (contact_data) из таблицы TICKETS. Отсортировать результат по столбцу имена пассажиров (passenger_name) в порядке возрастания.
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask4() {
        String answerVariant0 = "select ticket_no, contact_data, ticket_no from TICKETS ORDER BY passenger_name;";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask5() {
        String queryVariant0 = "Выведите все воздушные судна, которые удовлетовярют условию. Код самолёта должен быть равен \"CR2\".";
        //Выведите все воздушные судна, которые удовлетворяют условию: код самолёта должен быть равен \"CR2\".
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask5() {
        String answerVariant0 = "select * from aircrafts where aircraft_code=\'CR2\';";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask6() {
        String queryVariant0 = "Выведите все рейсы воздушных судов, которые удовлетовярют условию. Реальное время вылета самолёта должно быть не задано.";
        //Выведите все рейсы воздушных судов, которые удовлетворяют условию: реальное время вылета самолёта должно быть не задано.
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask6() {
        String answerVariant0 = "select * from flights where actual_departure isnull;";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask7() {
        String queryVariant0 = "Выведите все забронированые билеты, которые удовлетовярют условию. Общая сумма покупки должна быть больше, чем \"12000\" или бронь должна быть сделана позже \"2017-07-21\".";
        //Выведите все забронированые билеты, которые удовлетворяют условию: общая сумма покупки должна быть больше, чем "12000" или бронь должна быть сделана позже "2017-07-21".
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask7() {
        String answerVariant0 = "select * from bookings where total_amount > 12000 OR book_date > \'2017-07-21\'";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask8() {
        String queryVariant0 = "Выведите самый дорогой билет, исходя из условия. Бронь должна быть сделана позже \"2017-08-12\".";
        //Выведите самый дорогой билет, соответствующий условию: бронь должна быть сделана позже "2017-08-12".
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask8() {
        String answerVariant0 = "SELECT max(total_amount) FROM bookings WHERE book_date > '2017-08-12';";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask9() {
        String queryVariant0 = "Выведите все номера, номера мест для таблицы посадочных талонов. Отсортированные по столбцу идентификаторы рейсов по убыванию. Вывести последние 3 строки.";
        //Выведите все номера, номера мест из таблицы посадочных талонов. Отсортировать результат по столбцу идентификаторы рейсов в порядке убывания. Вывести последние 3 строки.
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask9() {
        String answerVariant0 = "select boarding_no, seat_no from boarding_passes order by flight_id DESC limit 3";
        return new String[]{answerVariant0};
    }


    //

    static String[] getQueryTask10() {
        String queryVariant0 = "Подсчитайте планируемое время каждого полёта для всех воздушных судов(в часах).";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask10() {
        String answerVariant0 = "\n" +
                "SELECT EXTRACT(EPOCH FROM scheduled_arrival - scheduled_departure) /60/60 FROM flights;";
        return new String[]{answerVariant0};
    }
}
