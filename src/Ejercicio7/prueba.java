package Ejercicio7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class prueba {

    public static void main(String[] args) {

        Random random = new Random();

        char a = (char) Math.floor(Math.random()*(122-97+1)+97);

        int b = (int)a;

        char n = 'r';

        char m = 'r';

        System.out.println(a);
        System.out.println(b);

        System.out.println(n==m);

        List<Character>l = new ArrayList<>();

        l.add(n);
        l.add(n);
        l.add(n);
        l.add(n);

        System.out.println(l);


    }
}
