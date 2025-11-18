import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n;
    private static List<Integer> arr;
    private static long[][] dp; // 한개알약,반개알약 남은 갯수별 경우의 수
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        arr = new ArrayList<>();
        while ((n = readInt()) != 0) arr.add(n);
        
        dp = new long[31][31];
        for (int t : arr) sb.append(solve(t, 0)+"\n");

        System.out.print(sb);
    }
    
    private static long solve(int w, int h) {
        if (w < 0 || h < 0) return 0;
        if (w == 0) return 1;
        if (dp[w][h]>0) return dp[w][h];
        
        return dp[w][h] = solve(w, h-1) + solve(w-1, h+1);
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