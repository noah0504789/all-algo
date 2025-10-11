import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, l, r, maxSum, ans;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = readInt();
            if (i < k) maxSum += arr[i];
        }
        
        l = 0; 
        r = k-1;
        
        ans = maxSum;
        
        while (r+1 < n) {
            maxSum += arr[++r];
            maxSum -= arr[l++];
            
            ans = Math.max(ans, maxSum);
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