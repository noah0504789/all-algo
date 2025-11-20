import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n;
    private static String s;
    private static String[] a;
    private static Boolean[] dp;
    
    public static void main(String... args) throws IOException {
        s = br.readLine();
        n = Integer.parseInt(br.readLine());
        a = new String[n];
        for (int i = 0; i < n; i++) a[i] = br.readLine();
        dp = new Boolean[s.length()];

        System.out.print(dfs(0) ? 1 : 0);
    }
    
    private static boolean dfs(int i) {
        if (i == s.length()) return true;
        if (dp[i] != null) return dp[i];
        
        for (String a_ : a) {
            if (i+a_.length()>s.length()) continue;
            if (!s.startsWith(a_, i)) continue;
            
            if (dfs(i+a_.length())) return dp[i] = true;
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