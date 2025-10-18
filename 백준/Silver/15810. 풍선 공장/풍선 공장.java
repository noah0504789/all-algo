import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m;
    private static long l, r, mid, cnt, min;
    private static int[] arr; 
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        r = 1_000_000;
        arr = new int[n];        
        for (int i = 0; i < n; i++) {
            arr[i] = readInt();
            r = Math.min(r, arr[i]);
        }
        
        r *= m;        
        l = 1;        
        
        while (l <= r) {
            mid = l+((r-l)>>> 1);
            cnt = 0;
            for (int c : arr) {
                cnt += mid / c;
                if (cnt >= m) break;
            }
            if (cnt >= m) {
                min = mid;
                r = mid-1;
            } else {
                l = mid+1;
            }
        }

        System.out.print(min);
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