import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n, m;
    private static String a;
    private static String[] s;
    private static Boolean[] dp;
    
    public static void main(String... args) throws IOException {
        a = br.readLine();
        m = a.length();
        n = Integer.parseInt(br.readLine());
        s = new String[n];
        for (int i = 0; i < n; i++) s[i] = br.readLine();
        
        dp = new Boolean[m+1];

        System.out.print(dfs(0) ? 1 : 0);
    }
    
    private static boolean dfs(int i) {
        if (i == m) return true;
        if (dp[i] != null) return dp[i];
                
        for (String s_ : s) {
            if (i+s_.length() > m) continue;
            if (!a.startsWith(s_, i)) continue;

            int ni = i+s_.length();
            if (dfs(ni)) return dp[i] = true;                
        }
        
        return dp[i] = false;
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