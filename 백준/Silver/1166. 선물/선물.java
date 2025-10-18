import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, l, w, h;
    private static long size;
    private static double a;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        l = readInt();
        w = readInt();
        h = readInt();
                       
        double lo = 0.0, hi = Math.max(l, Math.max(w, h));
        for (int it = 0; it < 100; it++) {
            double mid = (lo+hi)/2.0;
            if (can(mid)) lo = mid;
            else hi = mid;
        }
        
        System.out.printf("%.12f%n", lo);        
    }
    
    private static boolean can(double a) {
        if (a <= 0) return true;
        long x = (long)(l/a);
        long y = (long)(w/a);
        long z = (long)(h/a); // 내림
        if (x == 0 || y == 0 || z == 0) return false;
        
        long needXY = ceilDiv(n, z); // 올림
        long needX = ceilDiv(needXY, y);
        
        return x >= needX; // x >= 올림
    }
    
    private static long ceilDiv(long a, long b) {
        return (a-1)/b + 1;
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