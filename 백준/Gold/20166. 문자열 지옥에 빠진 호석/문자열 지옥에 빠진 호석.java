import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb;
    private static String[] input;
    private static int n, m, k;
    private static long ans;
    private static String t;
    private static char[][] arr;
    private static int[][] dir = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0},
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };
    private static long[][][] dp;
    private static Map<String, Long> map;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);
        
        arr = new char[n][m];
        for (int i = 0; i < n; i++) arr[i] = br.readLine().toCharArray();
        
        map = new HashMap<>();
        for (int tc = 0; tc < k; tc++) {
            t = br.readLine();            
            
            if (map.containsKey(t)) {
                ans = map.get(t);
            } else {
                dp = new long[n][m][t.length()];
                ans = 0;
                
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < m; c++) ans += dfs(r, c, 0, t);
                }    
                
                map.put(t, ans);
            }
            
            sb.append(ans+"\n");
        }        

        System.out.print(sb);
    }
    
    private static long dfs(int r, int c, int i, String t) {
        if (arr[r][c] != t.charAt(i)) return 0;        
        if (i == t.length()-1) return 1;        
        if (dp[r][c][i] > 0) return dp[r][c][i];
        
        long sum = 0;
        for (int[] d : dir) {
            int nr = (r + d[0] + n) % n, nc = (c + d[1] + m) % m;
            sum += dfs(nr, nc, i+1, t);
        }
        
        return dp[r][c][i] = sum;
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