package com.lxinet.jeesns.dao.group;

import com.lxinet.jeesns.model.group.Match;
import com.lxinet.jeesns.model.group.MatchMember;

import java.lang.reflect.Field;
import java.util.Date;

public class Tools {
    public static void main(String[] args) {
        Class clazz = Match.class;
        System.out.println("==============");
        for (Field o : clazz.getDeclaredFields()) {
            System.out.print(o.getName() +",");
        }
        System.out.println();
        System.out.println("==============");
        for (Field o : clazz.getDeclaredFields()) {
            System.out.print("#{" +o.getName() +"},");
        }
        System.out.println();
        System.out.println("==============");
        for (Field o : clazz.getDeclaredFields()) {
            System.out.println("<if test=\""+o.getName() +" != null\">"+o.getName() +" = #{"+o.getName() +"},</if>");
        }
        System.out.println("==============");

        System.out.println();
        System.out.println("==============");
        for (Field o : clazz.getDeclaredFields()) {
            String name = o.getName();
            String type = "";
            Class<?> tc = o.getType();
            if (tc ==  Integer.class) {
                type = "INTEGER";
            }
            if (tc ==  String.class) {
                type = "VARCHAR";
            }
            if (tc ==  Date.class) {
                type = "TIMESTAMP";
            }
            System.out.println("<id column=\""+name+"\" jdbcType=\""+type+"\" property=\""+name+"\" />");
        }
        System.out.println("==============");
    }
}
