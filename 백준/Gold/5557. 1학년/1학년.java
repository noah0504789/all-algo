import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, s, e;
    private static int[] arr;
    private static long[] dp, next;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();

        dp = new long[21];
        dp[arr[0]] = 1;
        
        for (int i = 1; i < n-1; i++) {
            next = new long[21];
            
            for (int s = 0; s<=20; s++) {
                int plus = s+arr[i], minus = s-arr[i];
                if (plus <= 20) next[plus] += dp[s];
                if (minus >= 0) next[minus] += dp[s];
            }
            dp = next;
        }
                
        System.out.print(dp[arr[n-1]]);
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