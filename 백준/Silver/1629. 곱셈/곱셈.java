import java.io.*;
import java.util.*;

public class Main {
    
    private static long a, b, c;
    
    public static void main(String... args) throws IOException {
        a = readLong();
        b = readLong();
        c = readLong();

        System.out.print(pow(a, b));
    }
    
    private static long pow(long a, long b) {
        long res = 1;
        while (b>0) {
            if((b&1)!=0) {
                res *= a;
                res %= c;                
            }
            a *= a;
            a %= c;
            b>>=1;
        }
        return res;
    }

    public static long readLong() throws IOException {
        long r = 0, c = System.in.read();
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