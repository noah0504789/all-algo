import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k;
    private static long max, l, r, m, cnt;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        Arrays.sort(arr);
        
        if (n == k) {
            System.out.print(arr[0]);
            return;
        }
        
        max = 0;
                
        l = 1;
        r = arr[n-1];
        while (l <= r) {
            m = (l+r)>>>1;
            cnt = 0;
            for (int i = 0; i < n; i++) cnt += arr[i] / m;
            if (cnt >= k) {                
                l = m+1;
                max = m;
            } else {
                r = m-1;
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