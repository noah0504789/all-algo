import java.io.*;
import java.util.*;

public class Main {
    
    private static int m, n, l, r, mid, max;
    private static long cnt;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        m = readInt();
        n = readInt();
        arr = new int[n];
        l = 1;
        r = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = readInt();
            r = Math.max(r, arr[i]);
        }       
        
        max = 0;
        while (l <= r) {
            mid = (l+r)>>>1;
            cnt = 0;
            for (int c : arr) cnt += c / mid;
            
            if (cnt >= m) {
                l = mid+1;                
                max = mid;
            } else {
                r = mid-1;
            }
        }

        System.out.print(max);
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