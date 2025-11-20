import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb;
    private static int n, m, k;
    private static long sum;
    private static String[] input, tc;
    private static char[][] arr;
    private static int[][] dir = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0},
        {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
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
        
        tc = new String[k];
        for (int i = 0; i < k; i++) tc[i] = br.readLine();
        
        map = new HashMap<>();
        
        for (String t : tc) {
            if (map.containsKey(t)) {
                sum = map.get(t);
            } else {
                dp = new long[n][m][t.length()];
                sum = 0;            
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < m; c++) {                        
                        sum += t.length() > 1 ? dfs(r, c, 0, t) : arr[r][c] == t.charAt(0) ? 1 : 0;
                    }
                }
                map.put(t, sum);
            }
            
            sb.append(sum+"\n");
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
}