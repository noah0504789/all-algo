import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static String pattern, str;
    private static int tc, n, m;
    private static boolean[][] dp; // pattern 0~i가 str 0~j와 매칭하는지 여부
    
    public static void main(String... args) throws IOException {
        pattern = br.readLine();
        n = pattern.length();
        
        tc = Integer.parseInt(br.readLine());
        while (tc-->0) {
            str = br.readLine();
            m = str.length();
            
            dp = new boolean[n+1][m+1];
            dp[0][0] = true;
            for (int i = 1; i <= n; i++) {
                if (pattern.charAt(i-1) == '*') dp[i][0] = dp[i-1][0];
                else dp[i][0] = false;
            }
            
            for (int i = 1; i <= n; i++) {
                char p = pattern.charAt(i-1);
                for (int j = 1; j <= m; j++) {
                    char c = str.charAt(j-1);
                    if (p == '*') dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    else dp[i][j] = (p==c) && dp[i-1][j-1];
                }
            }
            
            if(dp[n][m]) sb.append(str + "\n");
        }

        System.out.print(sb);
    }    
}