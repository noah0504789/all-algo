import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, n, m, nMax, mMax, p = 1_000_000_009;
    private static int[] nArr, mArr;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        nArr = new int[tc];
        mArr = new int[tc];
        for (int i = 0; i < tc; i++) {
            nArr[i] = readInt();
            mArr[i] = readInt();
            
            nMax = Math.max(nMax, nArr[i]);
            mMax = Math.max(mMax, mArr[i]);
        }
        
        dp = new long[nMax+1][mMax+1];
        dp[0][0] = 1;
        for (int i = 1; i <= nMax; i++) {
            for (int j = 1; j <= mMax; j++) {
                long b = i-2>= 0 ? dp[i-2][j-1] : 0;
                long c = i-3>= 0 ? dp[i-3][j-1] : 0;
                long v = (dp[i-1][j-1] + b + c) % p;
                dp[i][j] = v;
            }
        }
        
        for (int i = 0; i < tc; i++) {
            long v = 0;
            for (int j = 1; j <= mArr[i]; j++) {
                v += dp[nArr[i]][j];
                v %= p;
            }
            
            sb.append(v).append("\n");
        }

        System.out.print(sb);
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