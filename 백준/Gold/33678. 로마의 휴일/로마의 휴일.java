import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, x, l, sum, max;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        x = readInt();
        
        sum = 0;
        
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = readInt();
            sum += arr[i];
        }
        
        l = 0;
        max = 0;
        
        for (int r = 0; r < n; r++) {
            sum -= arr[r];
            
            while (sum < k && l <= r) {
                sum += (x * arr[l]);
                l++;
            }
            
            max = Math.max(max, r-l+1);
        }

        System.out.print(max == 0 ? "-1" : max);
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