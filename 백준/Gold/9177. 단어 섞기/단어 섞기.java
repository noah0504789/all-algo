import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static int tc, n, m, k;
    private static String[] input;
    private static String a, b, c;
    private static Boolean[][] dp;
    
    public static void main(String... args) throws IOException {
        tc = Integer.parseInt(br.readLine());
        for (int i = 1; i <= tc; i++) {
            input = br.readLine().split(" ");
            
            a = input[0];
            b = input[1];
            c = input[2];
            
            n = a.length();
            m = b.length();
            k = c.length();
            
            dp = new Boolean[n][m];
            
            sb.append("Data set " + i + ": ");
            sb.append(dfs(0, 0) ? "yes" : "no");
            sb.append("\n");
        }        

        System.out.print(sb);
    }
    
    private static boolean dfs(int i, int j) {
        if (i == n && j == m) return true;
        if (i == n) return b.substring(j).equals(c.substring(i+j));
        if (j == m) return a.substring(i).equals(c.substring(i+j));
        if (dp[i][j] != null) return dp[i][j];
        
        boolean res = false;
        if (a.charAt(i) == c.charAt(i+j)) res = dfs(i+1, j);
        if (!res && b.charAt(j) == c.charAt(i+j)) res = dfs(i, j+1);
        
        return dp[i][j] = res;
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