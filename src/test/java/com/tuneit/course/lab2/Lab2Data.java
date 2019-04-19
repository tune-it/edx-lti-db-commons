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
}