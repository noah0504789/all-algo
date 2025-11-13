import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static List<Integer> list;
    private static int n, maxN;
    private static long[][] dp; // n일까지 / n일의 조각 (0-반조각, 1-한조각)
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        list = new ArrayList<>();
        while ((n = readInt()) > 0) list.add(n);
        
        dp = new long[31][31];
        for (int h = 0; h <= 30; h++) dp[0][h] = 1;
        
        for (int w = 1; w <= 30; w++) {
            for (int h = 0; h <= 30; h++) {
                if (h > 0) dp[w][h] += dp[w][h-1];
                if (h + 1 <= 30) dp[w][h] += dp[w-1][h+1];
            }
        }
        
        for (int v : list) sb.append((dp[v][0]) +"\n");

        System.out.print(sb);
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