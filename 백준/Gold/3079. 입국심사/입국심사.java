import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m;
    private static long l, r, mid;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        l = 1;
        r = 0;
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = readInt();       
            r = Math.max(r, arr[i]);
        }

        r *= m;
        
        while (l < r) {
            mid = l + ((r-l)>>>1);
            if (can(mid)) {
                r = mid;
            } else {
                l = mid+1;
            }            
        }

        System.out.print(l);
    }
    
    private static boolean can(long target) {
        long cnt = 0;
        for (int t : arr) {
            cnt += target / t;
            if (cnt >= m) return true;            
        }
        
        return cnt >= m;
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