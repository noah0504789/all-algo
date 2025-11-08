import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br;
    private static int n, s, g, p, d;
    private static long ans;
    private static char[] arr;
    private static String[] input;
    private static int[] l, u;
    private static int[][] dp;     
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());

        input = br.readLine().split(" ");     
        s = Integer.parseInt(input[0]);
        g = Integer.parseInt(input[1]);
        p = Integer.parseInt(input[2]);
        d = Integer.parseInt(input[3]);
        
        l = new int[n+1];
        u = new int[n+1];
        
        arr = br.readLine().toCharArray();
        for (int i = 1; i <= n; i++) {
            switch (arr[i-1]) {
                case 'B': l[i] = 0; u[i] = s-1; break;
                case 'S': l[i] = s; u[i] = g-1; break;
                case 'G': l[i] = g; u[i] = p-1; break;
                case 'P': l[i] = p; u[i] = d-1; break;
                case 'D': l[i] = d; u[i] = 2*d; break;
            }
        }
        
        dp = new int[n+1][d+1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -1);
        
        for (int i = 0; i <= d; i++) {
            if (l[1] <= i && i <= u[1]) dp[1][i] = i;
        }
        
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= d; j++) {
                if (dp[i-1][j] == -1) continue;
                
                int lo = Math.max(0, l[i]-j);
                int hi = Math.min(d, u[i]-j);
                
                for (int k = lo; k <= hi; k++) dp[i][k] = Math.max(dp[i][k], dp[i-1][j] + k);
            }
        }
        
        ans = 0;
        for (int i = 0; i <= d; i++) ans =Math.max(ans, dp[n][i]);
        
        System.out.print(ans);
    }    
}