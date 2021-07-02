package com.company;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static int[] monthToDays = {
            31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public static String[] months = {
            "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
    };

    public static String[] days = {
            "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"
    };
    static int[] january = new int[monthToDays[0]];
    static int[] february = new int[monthToDays[1]];
    static int[] march = new int[monthToDays[2]];
    static int[] april = new int[monthToDays[3]];
    static int[] may = new int[monthToDays[4]];
    static int[] june = new int[monthToDays[5]];
    static int[] july = new int[monthToDays[6]];
    static int[] august = new int[monthToDays[7]];
    static int[] september = new int[monthToDays[8]];
    static int[] october = new int[monthToDays[9]];
    static int[] november = new int[monthToDays[10]];
    static int[] december = new int[monthToDays[11]];

    static int calendar[][] = {
            january,
            february,
            march,
            april,
            may,
            june,
            july,
            august,
            september,
            october,
            november,
            december
    };

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Въведете число от 1-7 за първи ден на година: ");
        int firstDay = input.nextInt();
        input.nextLine();

        System.out.println("Въведете дата: ");
        String date = input.nextLine();

        int day = getDay(date);
        int month = getMonth(date);


        fillCalendar(calendar, firstDay);

//        for (int i = 0; i < calendar.length; i++) {
//            for (int j = 0; j < calendar[i].length; j++) {
//                System.out.print(calendar[i][j] + " ");
//            }
//            System.out.println(" ");
//        }

        System.out.println("Describe day: ");
        String d = input.nextLine();
        System.out.println("--- " + getDateFromDescription(d) + " ---");
//
//        String d = getDateFromDescription("last friday of march");
//        System.out.println("--- " + d + " ---");

        System.out.println(date + " was a " + getWeekDay(day, month));

        printCalendar(getMonth(date));

    }

    private static void fillCalendar(int calendar[][], int startingDay) {

        int lastDay = startingDay;
        for (int month = 0; month < calendar.length; month++) {
            for (int day = 0; day < calendar[month].length; day++) {
                calendar[month][day] = lastDay;

                lastDay += (lastDay == 7) ? -6 : 1;
            }
        }
    }

    private static String getDateFromDescription(String description) {
        String[] descriptionTokens = description.split(" ");
        int week = 0;
        switch (descriptionTokens[0]) {
            case "first":
            case "First":
                week = 1;
                break;
            case "second":
            case "Second":
                week = 2;
                break;
            case "third":
            case "Third":
                week = 3;
                break;
            case "fourth":
            case "Fourth":
                week = 4;
                break;
            case "last":
            case "Last":
                week = 5;
                break;
            default:
                week = 0;
        }

        int month = 0;

        for (; month < months.length; month++) {
            if (descriptionTokens[3].equalsIgnoreCase(months[month])) break;
        }

        int elapsedWeeks = 0;
        int day = 0;
        for (; day < calendar[month].length; day++) {
            int weekDay = Arrays.asList(days).indexOf(descriptionTokens[1].toLowerCase(Locale.ROOT)) + 1;

            if (calendar[month][day] == weekDay) elapsedWeeks++;

            if (elapsedWeeks == week) break;
        }

        return (day + 1) + "/" + (month + 1) + "/" + "2021";
    }


    public static int getDay(String date) {
        return (Integer.parseInt(date.substring(0, 2)));
    }

    public static int getMonth(String date) {
        return Integer.parseInt(date.substring(3, 5));
    }

    public static String getWeekDay(int day, int month) {
        int dayOfWeek = calendar[month - 1][day - 1];
        return days[dayOfWeek - 1];
    }
    public static void printCalendar(int month){
        int[] monthCalendar = calendar[month-1];

        System.out.println("\t\t  " + months[month-1] + "\t\t\t");
        System.out.println("M\tT\tW\tT\tF\tS\tS");
        int currentDay = monthCalendar[0];
        System.out.print(getTabs(currentDay - 1));
        for(int day = 0; day < monthCalendar.length; day++) {
            currentDay = monthCalendar[day];
            if(currentDay == 7) {
                System.out.print(day + 1 + "\n");
            }
            else {
                System.out.print(day + 1 + "\t");
            }

        }
    }

    public static String getTabs(int howMany) {
        String tabs = "";
        for(int n = 0; n < howMany; n++) {
            tabs += "\t";
        }

        return tabs;
    }
}

