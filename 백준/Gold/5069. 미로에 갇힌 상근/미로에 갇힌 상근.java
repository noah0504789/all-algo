import java.io.*;
import java.util.*;

public class Main {
    
    private static int tc, n, maxN, size, offset;
    private static int[] tArr, ans;
    private static int[][] dir = {
        {1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, -1}, {-1, 1}
    };
    private static int[][][] dp;
    private static StringBuilder sb;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        tArr = new int[tc];
        for (int i = 0; i < tc; i++) {
            tArr[i] = readInt();
            maxN = Math.max(maxN, tArr[i]);
        }
        
        size = maxN*2+1;
        offset = maxN;
                
        dp = new int[maxN+1][size][size];
        dp[0][offset][offset] = 1;
        
        for (int step = 0; step < maxN; step++) {
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    int ways = dp[step][r][c];
                    if (ways == 0) continue;
                    
                    for (int[] d : dir) {
                        int nr = r + d[0], nc = c + d[1];
                        if (nr < 0 || nr >= size || nc < 0 || nc >= size) continue;
                        
                        dp[step+1][nr][nc] += ways;
                    }
                }
            }
        }
        
        ans = new int[maxN+1];
        for (int i = 0; i <= maxN; i++) ans[i] = dp[i][offset][offset];
        
        for (int t : tArr) sb.append(ans[t]+"\n");

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