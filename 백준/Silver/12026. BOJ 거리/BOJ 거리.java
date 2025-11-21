import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n, INF = Integer.MAX_VALUE;
    private static long[] dp;
    private static char[] arr, boj = {'B', 'O', 'J'};
    
    public static void main(String... args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();
        
        dp = new long[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (!canGo(arr[i], arr[j])) continue;
                if (dp[j] == INF) continue;
                dp[i] = Math.min(dp[i], dp[j] + (i-j)*(i-j));
            }
        }

        System.out.print(dp[n-1] == INF ? -1 : dp[n-1]);
    }
    
    private static boolean canGo(char cur, char prev) {
        if (cur == 'B' && prev == 'J') return true;
        if (cur == 'O' && prev == 'B') return true;
        if (cur == 'J' && prev == 'O') return true;
        
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