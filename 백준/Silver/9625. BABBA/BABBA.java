import java.io.*;
import java.util.*;

public class Main {
    
    private static int k, a, b, na, nb;
    
    public static void main(String... args) throws IOException {
        k = readInt();
        
        a = 1;
        for (int i = 1; i <= k; i++) {
            na = b;
            nb = a + b;
            a = na;
            b = nb;
        }
            
        System.out.print(a + " " + b);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') c = System.in.read();
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return negative ? -r : r;
    }
}