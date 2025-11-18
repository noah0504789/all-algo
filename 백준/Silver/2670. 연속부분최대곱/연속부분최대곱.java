import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n;
    private static double ans;
    private static double[] arr, dp;
    
    public static void main(String... args) throws IOException {
        n = Integer.parseInt(br.readLine());
        
        arr = new double[n];
        for (int i = 0; i < n; i++) arr[i] = Double.parseDouble(br.readLine());
        
        dp = new double[n];
        dp[0] = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i-1]*arr[i], arr[i]);
            ans = Math.max(ans, dp[i]);
        }

        System.out.printf("%.3f", ans);
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