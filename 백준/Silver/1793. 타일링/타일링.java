import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb;
    private static String input;
    private static int n, maxN;
    private static List<Integer> list;
    private static BigInteger[] dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        list = new ArrayList<>();
        
        while ((input = br.readLine()) != null) {
            n = Integer.parseInt(input);
            
            list.add(n);
            
            maxN = Math.max(maxN, n);
        }
        
        dp = new BigInteger[Math.max(3, maxN)+1];
        dp[0] = new BigInteger("1");
        dp[1] = new BigInteger("1");
        dp[2] = new BigInteger("3");
        
        for (int i = 3; i <= maxN; i++) {
            dp[i] = dp[i-1].add(dp[i-2].multiply(new BigInteger("2")));
        }
        
        for (int v : list) sb.append((dp[v])+"\n");

        System.out.print(sb);
    }
}