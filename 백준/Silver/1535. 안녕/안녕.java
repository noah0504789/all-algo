import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[] joy, stamina, dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        stamina = new int[n];
        for (int i = 0; i < n; i++) stamina[i] = readInt();
        
        joy = new int[n];
        for (int i = 0; i < n; i++) joy[i] = readInt();                
        
        dp = new int[100];
        for (int i = 0; i < n; i++) {
            int j_ = joy[i], s = stamina[i];
            for (int j = 99; j >= s; j--) dp[j] = Math.max(dp[j], dp[j-s] + j_);
        }        
        
        System.out.print(dp[99]);
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