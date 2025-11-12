import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br;
    private static int n;
    private static double[] arr, dp;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        arr = new double[n];
        for (int i = 0; i < n ; i++) arr[i] = Double.parseDouble(br.readLine());
        
        dp = new double[n];
        int l = 0;
        double mul = arr[l], ans = 0.0d;
        for (int r = 1; r < n; r++) {
            double cur = arr[r];
            mul *= cur;
            
            if (mul < cur) mul = cur;
            
            ans = Math.max(ans, mul);
        }

        System.out.print(String.format("%.3f", ans));
    }
}