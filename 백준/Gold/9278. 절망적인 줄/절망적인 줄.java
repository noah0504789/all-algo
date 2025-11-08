import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static BufferedReader br;
    private static int n, max, fo, fc, p = 1_000_000;
    private static String input;
    private static char[] arr;
    private static int[][] dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        
        while ((input = br.readLine()) != null) {
            arr = input.toCharArray();
            n = arr.length;
            
            max = n/2;
            
            fo = 0;
            fc = 0;
            for (char c : arr) {
                if (c == '(') fo++;
                else if (c == ')') fc++;
            }
            
            dp = new int[n+1][max+1];
            dp[0][0] = 1;
            
            for (int i = 1; i <= n; i++) {
                char c = arr[i-1];
                for (int o = 0; o <= max; o++) {
                    int ways = dp[i-1][o];
                    if (ways == 0) continue;                    
                    if (i-1-o > max) continue;
                    
                    if (c == '(') {
                        if (o+1 <= max) dp[i][o+1] = (dp[i][o+1] + ways) % p;
                    } else if (c == ')') {
                        if (2 * o >= i) dp[i][o] = (dp[i][o] + ways) % p;
                    } else {
                        if (o+1 <= max) dp[i][o+1] = (dp[i][o+1] + ways) % p;
                        if (o*2 >= i) dp[i][o] = (dp[i][o] + ways) % p;
                    }
                }
            }            
            
            sb.append((dp[n][max] % p)+"\n");
        }

        System.out.print(sb);
    }
}