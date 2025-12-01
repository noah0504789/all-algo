import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<Integer> w, b;
    private static int n, INF = Integer.MAX_VALUE;
    private static long[][][] dp; // i줄에서 w는 j개, b는 k개 뽑았을 때 n줄까지 최대합
    
    public static void main(String... args) throws IOException {
        w = new ArrayList<>();
        b = new ArrayList<>();
        
        while (true) {
            String input = br.readLine();
            if (input == null) break;
            
            String[] input_ = input.split(" ");
            w.add(Integer.parseInt(input_[0]));
            b.add(Integer.parseInt(input_[1]));
        }
        
        n = w.size();
        
        // n명의 경우에서 조합했을 때 어떤 수가 가장 합이 클것인가
        // 1 ~ n
        // 30 행에서 15행씩 각 15개씩 뽑고 두 합이 젤 큰거로
        // dp[i][j] = i줄까지 j줄 뽑았을 때 최대합
        
        dp = new long[n][16][16];
        
        System.out.print(dfs(0, 0, 0));
    }
    
    private static long dfs(int i, int j, int k) {
        if (i == n) return j == 15 && k == 15 ? 0 : -INF;
        if (dp[i][j][k] != 0) return dp[i][j][k];
        
        long max = dfs(i+1, j, k);
        
        if (j+1 <= 15) max = Math.max(max, w.get(i) + dfs(i+1, j+1, k));
        if (k+1 <= 15) max = Math.max(max, b.get(i) + dfs(i+1, j, k+1));
        
        return dp[i][j][k] = max;
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