import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n, INF = Integer.MAX_VALUE;
    private static char[] s;
    private static List<Integer>[] pos;
    private static int[][][] move; // 알파벳i를 다 출력했을 떄, s~e 구간에서 이동하는 최소 이동 비용
    private static long[][] memo;
    
    public static void main(String... args) throws IOException {
        s = br.readLine().toCharArray();        
        n = s.length;
        
        pos = new ArrayList[26];
        for (int i = 0; i < 26; i++) pos[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) pos[s[i]-'a'].add(i);
        
        move = new int[26][n][n];
        for (int c = 0; c < 26; c++) {
            for (int i = 0; i < n; i++) Arrays.fill(move[c][i], INF);
        }
        
        for (int c = 0; c < 26; c++) { 
            if (pos[c].isEmpty()) continue;
            
            int k = pos[c].size();
            int[] p = new int[k];
            for (int i = 0; i < k; i++) p[i] = pos[c].get(i);
            
            for (int s = 0; s < n; s++) {
                int[][][] d = new int[k][k][2];
                for (int i = 0; i < k; i++) {
                    for (int j = 0; j < k; j++) Arrays.fill(d[i][j], i==j ? Math.abs(s-p[i]) : INF);
                }
                
                for (int len = 1; len <= k; len++) {
                    for (int l = 0; l+len-1<k; l++) {
                        int r = l+len-1;
                        int cur = d[l][r][0];
                        if (cur < INF) {
                            if (l > 0 && cur+p[l]-p[l-1] < d[l-1][r][0]) d[l-1][r][0] = cur+p[l]-p[l-1];
                            if (r < k-1 && cur+p[r+1]-p[l] < d[l][r+1][1]) d[l][r+1][1] = cur+p[r+1]-p[l];
                        }
                        
                        cur = d[l][r][1];
                        if (cur < INF) {
                            if (l > 0 && cur+p[r]-p[l-1] < d[l-1][r][0]) d[l-1][r][0] = cur+p[r]-p[l-1];
                            if (r < k-1 && cur+p[r+1]-p[r] < d[l][r+1][1]) d[l][r+1][1] = cur+p[r+1]-p[r];
                        }
                    }
                }
                
                int leftEndCost = d[0][k-1][0], rightEndCost = d[0][k-1][1];
                
                for (int e = 0; e < n; e++) {
                    int best = INF;
                    
                    best = Math.min(best, leftEndCost+Math.abs(p[0]-e));
                    best = Math.min(best, rightEndCost+Math.abs(p[k-1]-e));
                    
                    move[c][s][e] = best;
                }
            }
        }
        
        memo = new long[26][n];
        for (int i = 0; i < 26; i++) Arrays.fill(memo[i], -1);
        
        System.out.print(dfs(0, 0));
    }
    
    private static long dfs(int letter, int i) {
        if (letter == 26) return 0;                
        if (memo[letter][i] != -1) return memo[letter][i];                
        if (pos[letter].isEmpty()) return memo[letter][i] = dfs(letter+1, i);
        
        int k = pos[letter].size();
        long best = INF;
        
        for (int e = 0; e < n; e++) {
            int m = move[letter][i][e];
            if (m >= INF) continue;
            
            best = Math.min(best, m + k + dfs(letter+1, e));
        }
        
        return memo[letter][i] = best;
    }
}