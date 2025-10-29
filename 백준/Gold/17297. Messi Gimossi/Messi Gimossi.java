import java.io.*;
import java.util.*;

public class Main {
    
    private static final String FIRST = "Messi";
    private static final String SECOND = "Messi Gimossi";
    private static final String BLANK = "Messi Messi Gimossi";
    private static StringBuilder sb;    
    private static int[] dp;
    private static int m, limit, last;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        m = readInt();
                
        dp = new int[1000];
        dp[0] = FIRST.length();
        dp[1] = SECOND.length();
        limit = pow(2, 30);
        for (int i = 2; i < 1000; i++) {
            dp[i] = dp[i-1] + 1 + dp[i-2];
            if (dp[i] >= limit) {last = i; break;}
        }
        
        System.out.print(solve(m, last));
    }
    
    private static String solve(int m, int idx) {
        if (idx <= 1) return SECOND.charAt(m-1) == ' ' ? BLANK : SECOND.charAt(m-1)+"";
        if (m <= dp[idx-1]) return solve(m, idx-1); //왼쪽
        if (m > dp[idx-1]+1) return solve(m-dp[idx-1]-1, idx-2); //오른쪽
                
        return BLANK;        
    }
    
    private static int pow(int a, int b) {
        int res = 1;
        while (b>0) {
            if((b&1)!=0) res*=a;
            a*=a;
            b>>=1;
        }
        return res;
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