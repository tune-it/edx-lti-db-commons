package com.tuneit.course;

public class TaskData {
    static String[] getQueryTask5() {
        String queryVariant0 = "Вычислить реальное время каждого полёта для всех воздушных судов (в днях).";
        String queryVariant1 = "Вычислить количество времени, прошедшего с момента каждой брони билета (в днях).";
        String queryVariant2 = "Вычислить планируемое время каждого полёта для всех воздушных судов (в днях).";
        return new String[]{queryVariant0, queryVariant1, queryVariant0, queryVariant1, queryVariant2};
    }

    static String[] getAnswerTask5() {
        String answerVariant0 = "select extract(day from actual_arrival - actual_departure) from flights;";
        String answerVariant1 = "select extract(day from now() - book_date) from bookings;";
        String answerVariant2 = "select extract(day from scheduled_arrival - scheduled_departure) from flights;";
        return new String[]{answerVariant0, answerVariant1, answerVariant0, answerVariant1, answerVariant2};
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
