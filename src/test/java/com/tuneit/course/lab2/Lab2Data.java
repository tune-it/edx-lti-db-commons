package com.tuneit.course.lab2;

public class Lab2Data {
    static String[] getQueryTask1() {
        return new String[]{"Выдать номера, номера билетов из таблицы посадочных талонов, номера мест которых заканчиваются на \"F\"."};
    }

    static String[] getAnswerTask1() {
        return new String[]{"select boarding_no, ticket_no from BOARDING_PASSES as a where seat_no like '%F';"};
    }
}