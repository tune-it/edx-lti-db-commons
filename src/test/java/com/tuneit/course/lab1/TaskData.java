package com.tuneit.course.lab1;

public class TaskData {
    static String[] getQueryTask1() {
        String queryVariant0 = "Выведите содержимое всех столбцов таблицы AIRCRAFTS.";
        String queryVariant1 = "Выведите содержимое всех столбцов таблицы AIRPORTS.";
        String queryVariant2 = "Выведите содержимое всех столбцов таблицы TICKETS.";

        int set1 = 10;
        int set2 = 88;
        int set3 = 72;
        int set4 = 1;
        String[] result = new String[set1 + set2 + set3 + set4];
        int counter = 0;

        for (int i = 0; i < set1; i++) {
            result[counter++] = queryVariant0;
        }
        for (int i = 0; i < set2; i++) {
            result[counter++] = queryVariant1;
        }
        for (int i = 0; i < set3; i++) {
            result[counter++] = queryVariant0;
        }
        for (int i = 0; i < set4; i++) {
            result[counter++] = queryVariant2;
        }
        return result;
    }

    static String[] getAnswerTask1() {
        String answerVariant0 = "select * from aircrafts as a;";
        String answerVariant1 = "select * from airports;";
        String answerVariant2 = "select * from tickets;";

        int set1 = 10;
        int set2 = 88;
        int set3 = 72;
        int set4 = 1;
        String[] result = new String[set1 + set2 + set3 + set4];
        int counter = 0;

        for (int i = 0; i < set1; i++) {
            result[counter++] = answerVariant0;
        }
        for (int i = 0; i < set2; i++) {
            result[counter++] = answerVariant1;
        }
        for (int i = 0; i < set3; i++) {
            result[counter++] = answerVariant0;
        }
        for (int i = 0; i < set4; i++) {
            result[counter++] = answerVariant2;
        }
        return result;
    }

    //
    static String[] getQueryTask2() {
        String queryVariant0 = "Выведите содержимое столбца (столбцов) контактные данные, коды бронирования, имена пассажиров для таблицы TICKETS.";
        String queryVariant1 = "Выведите содержимое столбца (столбцов) номера, коды бронирования, идентификаторы пассажиров, имена пассажиров для таблицы TICKETS.";
        String queryVariant2 = "Выведите содержимое столбца (столбцов) контактные данные, имена пассажиров для таблицы TICKETS.";
        return new String[]{queryVariant0,
                queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask2() {
        String answerVariant0 = "select contact_data, book_ref, passenger_name from tickets;";
        String answerVariant1 = "select ticket_no, book_ref, passenger_id, passenger_name from tickets;";
        String answerVariant2 = "select contact_data, passenger_name from tickets;";
        return new String[]{answerVariant0,
                answerVariant1, answerVariant2};
    }

    //

    static String[] getQueryTask3() {
        String queryVariant0 = "Выведите все координаты из таблицы аэропортов.";
        String queryVariant1 = "Выведите все коды из таблицы аэропортов.";
        String queryVariant2 = "Выведите все города, координаты, коды, имена из таблицы аэропортов.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask3() {
        String answerVariant0 = "select coordinates from airports;";
        String answerVariant1 = "select airport_code from airports;";
        String answerVariant2 = "select airport_code, coordinates, city, airport_name from airports;";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }

    //

    static String[] getQueryTask4() {
        String queryVariant0 = "Выведите все уникальные максимально возможные дистанции для воздушных судов.";
        String queryVariant1 = "Выведите все уникальные модели для воздушных судов.";
        String queryVariant2 = "Выведите все уникальные даты для бронирований.";
        return new String[]{queryVariant0, queryVariant0, queryVariant0, queryVariant0, queryVariant0, queryVariant0, queryVariant0, queryVariant0, queryVariant0,
                queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask4() {
        String answerVariant0 = "select distinct range from aircrafts;";
        String answerVariant1 = "select distinct model from aircrafts;";
        String answerVariant2 = "select distinct book_date from bookings;";
        return new String[]{answerVariant0, answerVariant0, answerVariant0, answerVariant0, answerVariant0, answerVariant0, answerVariant0, answerVariant0, answerVariant0,
                answerVariant1, answerVariant2};
    }

    //

    static String[] getQueryTask5() {
        String queryVariant0 = "Вычислить планируемое время каждого полёта для всех воздушных судов (в часах).";
        String queryVariant1 = "Вычислить реальное время каждого полёта для всех воздушных судов (в минутах).";

        int set1 = 10;
        int set2 = 69;
        String[] result = new String[set1 + set2];
        int counter = 0;

        for (int i = 0; i < set1; i++) {
            result[counter++] = queryVariant0;
        }
        for (int i = 0; i < set2; i++) {
            result[counter++] = queryVariant1;
        }
        return result;
    }

    static String[] getAnswerTask5() {
        String queryVariant0 = "select extract(EPOCH from scheduled_arrival - scheduled_departure)/60/60 from flights;";
        String queryVariant1 = "select extract(EPOCH from actual_arrival - actual_departure)/60 from flights;";

        int set1 = 10;
        int set2 = 69;
        String[] result = new String[set1 + set2];
        int counter = 0;

        for (int i = 0; i < set1; i++) {
            result[counter++] = queryVariant0;
        }
        for (int i = 0; i < set2; i++) {
            result[counter++] = queryVariant1;
        }
        return result;
    }

    //

    static String[] getQueryTask6() {
        String queryVariant0 = "Вывести в один столбец следующие параметры: код, максимально возможная дистанция, модель воздушных судов. Вывод представить в формате 'Я самолёт 773 11100 Боинг'.";
        String queryVariant1 = "Вывести в один столбец следующие параметры: номер, идентификатор пассажира, имя пассажира, код бронирования проданных билетов. Вывод представить в формате 'Я билет 0005432000987 id - 8149 604011 name - VALERIY TIKHONOV 06B046'.";
        String queryVariant2 = "Вывести в один столбец следующие параметры: максимально возможная дистанция, код, модель воздушных судов. Вывод представить в формате 'Я самолёт 11100 №773 боинг'.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask6() {
        String answerVariant0 = "select 'Я самолёт ' || aircraft_code || ' ' || range || ' ' || split_part(model, ' ', 1) from aircrafts;";
        String answerVariant1 = "select 'Я билет ' || ticket_no || ' id - ' || passenger_id || ' name - ' || passenger_name || ' ' || book_ref from tickets;";
        String answerVariant2 = "select 'Я самолёт ' || range || ' №' || aircraft_code || ' ' || lower(split_part(model, ' ', 1)) from aircrafts;";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }

    //

    static String[] getQueryTask7() {
        String queryVariant0 = "Выдать времена отправлений, времена прибытий, времена планируемых отправлений, времена планируемых прибытий, аэропорты отправления из таблицы рейсов воздушного судна, статусы которых содержат в названии 'Arr'.";
        String queryVariant1 = "Выдать коды, координаты из таблицы аэропортов, имена которых содержат в названии 'Ба'.";
        String queryVariant2 = "Выдать номера билетов из таблицы посадочных талонов, номера мест которых содержат в названии '14'.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask7() {
        String answerVariant0 = "select actual_departure, actual_arrival, scheduled_arrival, scheduled_departure, departure_airport from flights where status like '%Arr%'";
        String answerVariant1 = "select coordinates, airport_code from airports where airport_name like '%Ба%'";
        String answerVariant2 = "select ticket_no from boarding_passes where seat_no like '%14%'";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }

    //

    static String[] getQueryTask8() {
        String queryVariant0 = "Выдать контактные данные из таблицы проданных билетов, имена пассажиров которых начинаются на 'VI'. Отсортировать по столбцу имена пассажиров в порядке возрастания.";
        String queryVariant1 = "Выдать временные зоны из таблицы аэропортов, города которых начинаются на 'Че'. Отсортировать по столбцу города в порядке возрастания.";
        String queryVariant2 = "Выдать номера билетов, суммы из таблицы состоявшихся полетов, тарифы которых начинаются на 'Co'. Отсортировать по столбцу тарифы в порядке возрастания.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask8() {
        String answerVariant0 = "select contact_data from tickets where passenger_name like 'VI%' order by passenger_name asc;";
        String answerVariant1 = "select timezone from airports where city like 'Че%' order by city asc;";
        String answerVariant2 = "select amount, ticket_no from ticket_flights where fare_conditions like 'Co%' order by fare_conditions;";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }

    //

    static String[] getQueryTask9() {
        String queryVariant0 = "Выдать идентификаторы рейсов из таблицы состоявшихся полетов, тарифы которых заканчиваются на 'ort'. Отсортировать по столбцу 1 в порядке возрастания.";
        String queryVariant1 = "Выдать номера билетов, идентификаторы рейсов из таблицы посадочных талонов, номера мест которых заканчиваются на 'D'. Отсортировать по столбцу 1 в порядке возрастания.";
        String queryVariant2 = "Выдать имена, коды из таблицы аэропортов, города которых заканчиваются на 'ск'. Отсортировать по столбцу 1 в порядке возрастания.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask9() {
        String answerVariant0 = "select flight_id from ticket_flights where fare_conditions like '%ort' order by 1;";
        String answerVariant1 = "select ticket_no, flight_id from boarding_passes where seat_no like '%D' order by 1;";
        String answerVariant2 = "select airport_name, airport_code from airports where city like '%ск' order by 1;";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }


    //

    static String[] getQueryTask10() {
        String queryVariant0 = "Вывести из таблицы посадочных мест коды воздушных судов с тарифами: 'Economy'.";
        String queryVariant1 = "Вывести из таблицы посадочных мест коды воздушных судов с номерами мест: '35K', '39B', '18G', '50A', '31K', '48A'.";
        String queryVariant2 = "Вывести из таблицы состоявшихся полетов тарифы, суммы с идентификаторами рейсов: '2354'.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask10() {
        String answerVariant0 = "select aircraft_code from seats where fare_conditions = 'Economy';";
        String answerVariant1 = "select aircraft_code from seats where seat_no in ('35K', '39B', '18G', '50A', '31K', '48A')";
        String answerVariant2 = "select amount, fare_conditions from ticket_flights where flight_id = 2354;";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }

    //

    static String[] getQueryTask11() {
        String queryVariant0 = "Вывести количество рейсов воздушного судна c кодами воздушных судов '773'. Ответ выдать в виде '773 = 10'.";
        String queryVariant1 = "Вывести количество посадочных мест c кодами воздушных судов 'SU9'. Ответ выдать в виде 'SU9 = 10'.";
        String queryVariant2 = "Вывести количество рейсов воздушного судна c статусами 'Arrived'. Ответ выдать в виде 'Arrived = 10'.";
        return new String[]{queryVariant0, queryVariant1, queryVariant0, queryVariant2};
    }

    static String[] getAnswerTask11() {
        String answerVariant0 = "select '773 = ' || count(aircraft_code) from flights where aircraft_code = '773' group by aircraft_code;";
        String answerVariant1 = "select 'SU9 = ' || count(aircraft_code) from seats where aircraft_code = 'SU9' group by aircraft_code;";
        String answerVariant2 = "select 'Arrived = ' || count(status) from flights where status = 'Arrived' group by status;";
        return new String[]{answerVariant0, answerVariant1, answerVariant0, answerVariant2};
    }

    //

    static String[] getQueryTask12() {
        String queryVariant0 = "Вывести количество различных кодов воздушных судов из таблицы рейсов воздушного судна. Результат расположить по убыванию. Привести 5 первых результатов запроса.";
        String queryVariant1 = "Вывести количество различных статусов из таблицы рейсов воздушного судна. Результат расположить по убыванию. Привести 5 первых результатов запроса.";
        String queryVariant2 = "Вывести количество различных аэропортов отправлений из таблицы рейсов воздушного судна. Результат расположить по убыванию. Привести 5 первых результатов запроса.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask12() {
        String answerVariant0 = "select count(aircraft_code) from flights group by aircraft_code order by 1 desc limit 5;";
        String answerVariant1 = "select count(status) from flights group by status order by 1 desc limit 5;";
        String answerVariant2 = "select count(departure_airport) from flights group by departure_airport order by 1 desc limit 5;";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }
}
