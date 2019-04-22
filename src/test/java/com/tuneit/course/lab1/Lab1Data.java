package com.tuneit.course.lab1;

public class Lab1Data {
    static String[] getQueryTask1() {
        return new String[]{"Выведите содержимое всех полей таблицы AIRPORTS."};
    }

    static String[] getAnswerTask1() {
        return new String[]{"select * from AIRPORTS as a;"};
    }

    //
    static String[] getQueryTask2() {
        String queryVariant0 = "Выведите содержимое полей: коды воздушных судов (aircraft_code), номера мест (seat_no) для таблицы SEATS.";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask2() {
        String answerVariant0 = "select aircraft_code, seat_no from SEATS;";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask3() {
        String queryVariant0 = "Выведите все уникальные номера мест (seat_no) из таблицы SEATS.";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask3() {
        String answerVariant0 = "select distinct seat_no from SEATS;";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask4() {
        String queryVariant0 = "Выведите содержимое полей: города (city), имена (airport_name) для таблицы AIRPORTS. Отсортировать результат по столбцу временные зоны (timezone) в порядке убывания.";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask4() {
        String answerVariant0 = "select airport_name, city from AIRPORTS ORDER BY timezone desc;";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask5() {
        String queryVariant0 = "Выведите содержимое всех полей из таблицы аэропортов, которые удовлетворяют условию: название города должно быть равно \"Домодедово\".";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask5() {
        String answerVariant0 = "select * from airports where city=\'Домодедово\';";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask6() {
        String queryVariant0 = "Выведите содержимое всех полей из таблицы рейсов воздушного судна, которые удовлетворяют условию: реальное время прибытия самолёта должно быть не задано.";
        //Выведите все рейсы воздушных судов, которые удовлетворяют условию: реальное время вылета самолёта должно быть не задано.
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask6() {
        String answerVariant0 = "select * from flights where actual_arrival is null;";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask7() {
        String queryVariant0 = "Выведите содержимое всех полей из таблицы рейсов воздушного судна, которые удовлетворяют условию: время планируемого отправления рейса должно быть равно \"2017-09-10 12:00:00.000000\" или номер рейса должен быть равен \"PG0667\".";
        //Выведите все забронированые билеты, которые удовлетворяют условию: общая сумма покупки должна быть больше, чем "12000" или бронь должна быть сделана позже "2017-07-21".
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask7() {
        String answerVariant0 = "select * from flights where flight_no = 'PG0667' OR scheduled_departure = '2017-09-10 12:00:00.000000'";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask8() {
        String queryVariant0 = "Выведите самый дешёвый билет, соответствующий условию: идентификатор рейса должен быть равен \"25223\".";
        //Выведите самый дорогой билет, соответствующий условию: бронь должна быть сделана позже "2017-08-12".
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask8() {
        String answerVariant0 = "SELECT min(amount) FROM ticket_flights WHERE flight_id = '25223';";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask9() {
        String queryVariant0 = "Выведите содержимое полей: тарифы, номера билетов из таблицы состоявшихся полетов. Отсортировать результат по столбцу идентификаторы рейсов в порядке убывания. Вывести первые 3 строки.";
        //Выведите все номера, номера мест из таблицы посадочных талонов. Отсортировать результат по столбцу идентификаторы рейсов в порядке убывания. Вывести последние 3 строки.
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask9() {
        String answerVariant0 = "select fare_conditions, ticket_no from ticket_flights order by flight_id DESC limit 3";
        return new String[]{answerVariant0};
    }


    //

    static String[] getQueryTask10() {
        String queryVariant0 = "Подсчитайте планируемое время каждого полёта для всех воздушных судов(в минутах).";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask10() {
        String answerVariant0 = "\n" +
                "SELECT EXTRACT(EPOCH FROM scheduled_arrival - scheduled_departure) /60 FROM flights;";
        return new String[]{answerVariant0};
    }
}
