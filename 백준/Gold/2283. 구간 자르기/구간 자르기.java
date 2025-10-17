import java.io.*;
import java.util.*;

public class Main {
    
    private static final int SIZE = 1_000_000;
    private static int n, s, e, l, maxR, cur;
    private static long k, sum;
    private static int[] diff, cover;    
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readLong();
        
        diff = new int[SIZE+1];        
        
        maxR = 0;        
        for (int i = 0; i < n; i++) {
            s = readInt();
            e = readInt();
                        
            diff[s]++;
            diff[e]--;
            
            maxR = Math.max(maxR, e);
        }
        
        cur = 0;
        cover = new int[SIZE];
        for (int i = 0; i < maxR; i++) {
            cur += diff[i];
            cover[i] = cur;
        }
                
        l = 0;
        sum = 0;
        for (int r = 0; r < maxR; r++) {
            sum += cover[r];
            
            while (sum >= k) {
                if (sum == k) {
                    System.out.print(l + " " + (r+1));
                    return;                    
                }
                
                sum -= cover[l++];
            }
        }

        System.out.print("0 0");
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
    
    public static long readLong() throws IOException {
        long r = 0;
        int c = System.in.read();
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