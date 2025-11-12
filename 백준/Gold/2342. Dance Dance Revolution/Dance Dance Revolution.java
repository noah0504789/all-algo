import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, ans, INF = Integer.MAX_VALUE;
    private static List<Integer> list;     
    private static int[][][] dp;// idx/l/r
    
    public static void main(String... args) throws IOException {
        list = new ArrayList<>();
        
        while ((n = readInt()) > 0) list.add(n);                    
        
        n= list.size();
        dp= new int[n+1][5][5];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 5; j++) Arrays.fill(dp[i][j], INF);
        }
        
        dp[0][0][0] = 0;
        
        for (int i = 1; i <= n; i++) { 
            int step = list.get(i-1);
            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    int prev = dp[i-1][l][r];
                    if (prev >= INF) continue;
                                        
                    // 왼발로 누르기
                    if (r != step) {
                        int nl = step, nr = r;
                        dp[i][nl][nr] = Math.min(dp[i][nl][nr], prev + cost(l, step));
                    }
                    
                    // 오른발로 누르기
                    if (l != step) {
                        int nl = l, nr = step;
                        dp[i][nl][nr] = Math.min(dp[i][nl][nr], prev + cost(r, step));
                    }
                }
            }           
        }
        
        ans = INF;
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) ans = Math.min(ans, dp[n][j][k]);
        }

        System.out.print(ans);
    }
    
    private static int cost(int from, int to) {
        if (from == to) return 1;
        if (from == 0) return 2;
        if ((from+to)%2 == 0) return 4;
        return 3;
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