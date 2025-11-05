import java.io.*;
import java.util.*;

public class Main {
    
    private static final int p = 15_746;
    private static int n, v1, v2, sum;
    private static int[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        if (n <= 2) {
            System.out.print(n);
            return;
        }         
        
        v1 = 1;
        v2 = 2;
        
        for (int i = 2; i < n; i++) {            
            sum = (v1+v2) % p;            
            v1 = v2;
            v2 = sum;            
        }

        System.out.print(sum);
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