package me.croshaw;

import me.croshaw.api.peoples.People;

import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        People Bezr = new Employee(0, "Безруков", "Кирилл", "Русланович", "+7953458852", LocalDate.of(2002, Month.FEBRUARY, 15));
        System.out.println(Bezr.toString());
        System.out.println(Bezr.toShortString());
        System.out.println(Bezr.getAge());
    }
}