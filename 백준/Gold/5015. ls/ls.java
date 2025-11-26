import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static String pattern, str;
    private static int tc, n, m;
    private static Boolean[][] dp; // pattern 0~i가 str 0~j와 매칭하는지 여부
    
    public static void main(String... args) throws IOException {
        pattern = br.readLine();
        n = pattern.length();
        
        tc = Integer.parseInt(br.readLine());
        while (tc-->0) {
            str = br.readLine();
            m = str.length();
            
            dp = new Boolean[n+1][m+1];            
            
            if(dfs(0,0)) sb.append(str + "\n");
        }

        System.out.print(sb);
    }    
    
    private static boolean dfs(int i, int j) {
        if (i == n) return j == m;
        if (dp[i][j] != null) return dp[i][j];
        
        char p = pattern.charAt(i);
        boolean res = false;
        if (p=='*') {
            res = dfs(i+1,j);
            if (!res && j+1 <= m) res = dfs(i, j+1);
        } else if (j < m && p == str.charAt(j)) {
            res = dfs(i+1, j+1);
        }        
        
        return dp[i][j] = res;
    }
}