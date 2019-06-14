package hall_management.config;

import java.util.List;

public class Printer {

    public static void printList(List<?> list) {
        System.out.println("<><>PRINTING LIST<Inside Printer Class>... Size : " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        System.out.println("<><>PRINTING DONE!! \n\n");
    }

    public static void printLine() {
        System.out.println("");
        int hor = 40;
        int star = 15;
        for (int i = 0; i < hor; i++) {
            System.out.print("-");
        }
        for (int i = 0; i < star; i++) {
            System.out.print("*");
        }
        for (int i = 0; i < hor; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }

}
