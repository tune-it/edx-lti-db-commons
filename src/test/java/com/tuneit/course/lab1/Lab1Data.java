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
        String queryVariant0 = "Выведите содержимое полей коды воздушных судов (aircraft_code), номера мест (seat_no) для таблицы SEATS.";
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
        String queryVariant0 = "Выведите содержимое полей города (city), имена (airport_name) для таблицы AIRPORTS. Отсортируйте результат по столбцу временные зоны (timezone) в порядке убывания.";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask4() {
        String answerVariant0 = "select airport_name, city from AIRPORTS ORDER BY timezone desc;";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask5() {
        String queryVariant0 = "Выведите содержимое всех полей из таблицы аэропортов, которые удовлетворяют следующему условию: название города должно быть равно \"Сыктывкар\".";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask5() {
        String answerVariant0 = "select * from airports where city=\'Сыктывкар\';";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask6() {
        String queryVariant0 = "Выведите содержимое всех полей из таблицы рейсов воздушного судна, которые удовлетворяют следующему условию: реальное время прибытия самолёта должно быть не задано.";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask6() {
        String answerVariant0 = "select * from flights where actual_arrival is null;";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask7() {
        String queryVariant0 = "Выведите содержимое всех полей из таблицы рейсов воздушного судна, которые удовлетворяют следующему условию: время планируемого отправления рейса должно быть равно \"2017-09-10 12:00:00.000000\" или номер рейса должен быть равен \"PG0667\".";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask7() {
        String answerVariant0 = "select * from flights where flight_no = 'PG0667' OR scheduled_departure = '2017-09-10 12:00:00.000000'";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask8() {
        String queryVariant0 = "Выведите среднюю стоимость перелётов, соответствующих следующему условию: тариф должен быть равен \"Economy\".";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask8() {
        String answerVariant0 = "SELECT avg(amount) FROM ticket_flights WHERE fare_conditions = 'Economy';";
        return new String[]{answerVariant0};
    }

    //

    static String[] getQueryTask9() {
        String queryVariant0 = "Выведите содержимое следующих полей из таблицы состоявшихся полетов: тарифы, номера билетов. Отсортируйте результат по столбцу идентификаторы рейсов в порядке убывания. Выведите первые 3 строки.";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask9() {
        String answerVariant0 = "select fare_conditions, ticket_no from ticket_flights order by flight_id DESC limit 3";
        return new String[]{answerVariant0};
    }


    //

    static String[] getQueryTask10() {
        String queryVariant0 = "Подсчитайте планируемое время каждого полёта для всех воздушных судов (в минутах). Важно: в postgresql результат вычитания двух дат имеет тип interval. Для извлечения количества минут из данных типа interval можно воспользоваться функцией extract.";
        return new String[]{queryVariant0};
    }

    static String[] getAnswerTask10() {
        String answerVariant0 = "\n" +
                "SELECT EXTRACT(EPOCH FROM scheduled_arrival - scheduled_departure) /60 FROM flights;";
        return new String[]{answerVariant0};
    }
}
