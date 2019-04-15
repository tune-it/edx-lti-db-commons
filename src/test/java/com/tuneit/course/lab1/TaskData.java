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
        String queryVariant0 = "Вывести в один столбец следующие параметры: код, город, временная зона, имя аэропортов. Вывод представить в формате 'Я аэропорт YKS г. Якутск временная зона - Asia/Yakutsk ЯКУТСК'.";
        String queryVariant1 = "Вывести в один столбец следующие параметры: идентификатор пассажира, имя пассажира, номер, код бронирования проданных билетов. Вывод представить в формате 'Я билет 8149 name - V. TIKHONOV №0005432000987 06B046'.";
        String queryVariant2 = "Вывести в один столбец следующие параметры: максимально возможная дистанция, код, модель воздушных судов. Вывод представить в формате 'Я самолёт 11100 773 Боинг 777-300'.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask6() {
//        String answerVariant0 = "select 'Я самолёт ' || aircraft_code || ' ' || range || ' ' || split_part(model, ' ', 1) from aircrafts;";
        String answerVariant0 = "select 'Я аэропорт ' || airport_code || ' г. ' || city || ' временная зона - ' || timezone  || ' ' || upper(airport_name) from airports;";
        String answerVariant1 = "select 'Я билет ' || split_part(passenger_id, ' ', 1) || ' name - ' || substr(passenger_name, 0, 2) || '. ' || split_part(passenger_name, ' ', 2) || ' №' || ticket_no || ' ' || book_ref from tickets;";
        String answerVariant2 = "select 'Я самолёт ' || range || ' ' || aircraft_code || ' ' || model from aircrafts;";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }

    //

    static String[] getQueryTask7() {
        String queryVariant0 = "Выдать коды из таблицы аэропортов, города которых содержат в названии 'ово'.";
        String queryVariant1 = "Выдать коды воздушных судов, идентификаторы рейсов, времена прибытий, аэропорты отправления, времена планируемых прибытий, времена отправлений, аэропорты прибытия из таблицы рейсов воздушного судна, статусы которых содержат в названии 'A'.";
        String queryVariant2 = "Выдать максимально возможные дистанции из таблицы воздушных судов, модели которых содержат в названии '100'.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask7() {
        String answerVariant0 = "select airport_code from airports where city like '%ово%'";
        String answerVariant1 = "select aircraft_code, flight_id, actual_arrival, departure_airport, scheduled_arrival, actual_departure, arrival_airport from flights where status like '%A%'";
        String answerVariant2 = "select range from aircrafts where model like '%100%'";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }

    //

    static String[] getQueryTask8() {
        String queryVariant0 = "Выдать номера билетов из таблицы посадочных талонов, номера мест которых начинаются на '14'. Отсортировать по столбцу номера мест в порядке возрастания.";
        String queryVariant1 = "Выдать идентификаторы пассажиров из таблицы проданных билетов, имена пассажиров которых начинаются на 'AL'. Отсортировать по столбцу имена пассажиров в порядке возрастания.";
        String queryVariant2 = "Выдать тарифы из таблицы посадочных мест, номера мест которых начинаются на '12'. Отсортировать по столбцу номера мест в порядке возрастания.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask8() {
        String answerVariant0 = "select ticket_no from boarding_passes where seat_no like '14%' order by seat_no asc;";
        String answerVariant1 = "select passenger_id from tickets where passenger_name like 'AL%' order by passenger_name asc;";
        String answerVariant2 = "select fare_conditions from seats where seat_no like '12%' order by seat_no;";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
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

    //

    static String[] getQueryTask11() {
        String queryVariant0 = "Вывести количество рейсов воздушного судна c кодами воздушных судов 'CR2'. Ответ выдать в виде 'Количество найденных рейсов воздушного судна: 10'.";
        String queryVariant1 = "Вывести количество рейсов воздушного судна c статусами 'Departed'. Ответ выдать в виде 'Количество найденных рейсов воздушного судна: 10'.";
        String queryVariant2 = "Вывести количество рейсов воздушного судна c аэропортами отправления 'AER'. Ответ выдать в виде 'Количество найденных рейсов воздушного судна: 10'.";
        return new String[]{queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask11() {
        String answerVariant0 = "select 'Количество найденных рейсов воздушного судна: ' || count(*) from flights where aircraft_code = 'CR2'";
        String answerVariant1 = "select 'Количество найденных рейсов воздушного судна: ' || count(*) from flights where status = 'Departed'";
        String answerVariant2 = "select 'Количество найденных рейсов воздушного судна: ' || count(*) from flights where arrival_airport = 'AER'";
        return new String[]{answerVariant0, answerVariant1, answerVariant2};
    }

    //

    static String[] getQueryTask12() {
        String queryVariant0 = "Вывести количество различных номеров мест из таблицы посадочных мест. Результат расположить по возрастанию. Привести 5 первых результатов запроса.";
        String queryVariant1 = "Вывести количество различных кодов воздушных судов из таблицы рейсов воздушного судна. Результат расположить по возрастанию. Привести 5 первых результатов запроса.";
        String queryVariant2 = "Вывести количество различных кодов воздушных судов из таблицы посадочных мест. Результат расположить по возрастанию. Привести 5 первых результатов запроса.";
        return new String[]{queryVariant0, queryVariant1, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask12() {
        String answerVariant0 = "select count(seat_no) from seats group by seat_no order by 1 limit 5;";
        String answerVariant1 = "select count(aircraft_code) from flights group by aircraft_code order by 1 limit 5;";
        String answerVariant2 = "select count(aircraft_code) from seats group by aircraft_code order by 1 asc limit 5;";
        return new String[]{answerVariant0, answerVariant1, answerVariant1, answerVariant2};
    }
}
