package com.mingrisoft.enumset;

import static com.mingrisoft.enumset.Weeks.MONDAY;
import static com.mingrisoft.enumset.Weeks.THURSDAY;

import java.util.EnumSet;

public class EnumSetTest {
    public static void main(String[] args) {
        EnumSet<Weeks> week = EnumSet.noneOf(Weeks.class);
        week.add(MONDAY);
        System.out.println("EnumSet�е�Ԫ�أ�" + week);
        week.remove(MONDAY);
        System.out.println("EnumSet�е�Ԫ�أ�" + week);
        week.addAll(EnumSet.complementOf(week));
        System.out.println("EnumSet�е�Ԫ�أ�" + week);
        week.removeAll(EnumSet.range(MONDAY, THURSDAY));
        System.out.println("EnumSet�е�Ԫ�أ�" + week);
    }
}
