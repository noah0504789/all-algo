import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, p = 1_000_000_007;
    private static int[][] allow;
    private static long[] prev, cur;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        prev = new long[5];
        cur = new long[5];
        
        prev[0] = 1;
        prev[1] = prev[2] = prev[3] = prev[4] = 1;
        
        allow = new int[5][];
        allow[0] = new int[]{1, 2, 3, 4};
        allow[1] = new int[]{3, 4};
        allow[2] = new int[]{4};
        allow[3] = new int[]{1};
        allow[4] = new int[]{1, 2};
        
        for (int day = 2; day <= n; day++) {
            Arrays.fill(cur, 0);
            
            for (int j : allow[0]) cur[j] = (cur[j] + prev[0]) % p;
            
            for (int i = 1; i <= 4; i++) {
                cur[0] = (cur[0] + prev[i]) % p;
                for (int j : allow[i]) cur[j] = (cur[j] + prev[i]) % p;
            }
            
            long[] tmp = prev; prev = cur; cur = tmp;
        }
        
        long ans = 0;
        for (int s = 0; s<=4; s++) ans = (ans + prev[s]) % p;
        
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