import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static int tc, n, m, maxN, maxM, p = 1_000_000_009;
    private static int[] nArr, mArr;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        tc = readInt();
        nArr = new int[tc];
        mArr = new int[tc];
        for (int i = 0; i < tc; i++) {
            nArr[i] = readInt();
            mArr[i] = readInt();
            
            maxN = Math.max(maxN, nArr[i]);
            maxM = Math.max(maxM, mArr[i]);
        }
        
        dp = new long[maxN+1][maxM+1];
        dp[0][0] = 1;
        for (int i = 1; i <= maxN; i++) {
            for (int j = 1; j <= maxM; j++) {
                long a = i-1>=0 ? dp[i-1][j-1] : 0;
                long b = i-2>=0 ? dp[i-2][j-1] : 0;
                long c = i-3>=0 ? dp[i-3][j-1] : 0;
                
                dp[i][j] = (a + b + c) % p;
            }
        }
        
        for (int i = 0; i < tc; i++) sb.append(dp[nArr[i]][mArr[i]]+"\n");
        
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