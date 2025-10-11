import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, sum, ans;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        sum = 0;
        
        arr = new int[n*2];
        for (int i = 0; i < n; i++) {
            arr[i+n] = arr[i] = readInt();
            if (i < k) sum += arr[i];
        }
        
        ans = sum;        
        
        for (int l = 1; l < n; l++) {
            int r = l + k - 1;
            sum -= arr[l-1];
            sum += arr[r];
            ans = Math.max(ans, sum);
        }

        System.out.print(ans);
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