import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k;
    private static long sum, l, r, m;
    private static int[] arr, preSum;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        arr = new int[n];
        sum = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = readInt();
            sum += arr[i];
        }
        
        if (sum == 0) {
            System.out.println(0);
            return;
        }
        
        l = 0;
        r = sum+1;        
        while (l < r) {
            m = l + ((r-l)>>>1);
            if (can(m)) {
                l = m+1;                
            } else {
                r = m;
            }
        }

        System.out.print(l-1);
    }
    
    private static boolean can(long T) {
        if (T == 0) return true;
        
        long acc = 0;
        int cnt = 0;
        for (int x : arr) {
            acc += x;
            if (acc >= T) {
                cnt++;
                acc = 0;
                if (cnt >= k) return true;
            }
        }
        
        return false;
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