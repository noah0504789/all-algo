import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String s;
    private static int n;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        s = br.readLine();
        n = s.length();
        
        dp = new long[n];
        Arrays.fill(dp, -1);

        System.out.print(dfs(0));
    }
    
    private static long dfs(int i) {
        if (i == n) return 1;                
        if (dp[i] != -1) return dp[i];
        if (s.charAt(i) == '0') return dp[i] = 0;        
        
        long res = 0;
        int v1 = s.charAt(i)-'0';
        if (v1>=1 && v1<=9) res += dfs(i+1);
        
        if (i+1 < n) {
            int v2 = (s.charAt(i)-'0') * 10 + s.charAt(i+1)-'0';
            if (v2 >= 10 && v2 <= 34) res += dfs(i+2);
        }
        
        return dp[i] = res;
    }
}