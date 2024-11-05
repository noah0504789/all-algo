import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;

    private static char[] medicine;
    private static int[][][] dp;
    private static int n, ans;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        medicine = br.readLine().toCharArray();
        
        dp = new int[medicine.length][medicine.length][3];
        
        for (int[][] rows : dp) {
            for (int[] row : rows) Arrays.fill(row, -1);
        }
       
        System.out.print(dfs(0, 0, medicine.length-1));
    }

    public static int dfs(int cnt, int start, int end) {
        if (start > end || cnt == medicine.length) return cnt;

        int curIdx = cnt % 3;
        
        if (dp[start][end][curIdx] > -1) return dp[start][end][curIdx];
        
        char cur = ' ';
        
        if (curIdx == 0) cur = 'B';
        else if (curIdx == 1) cur = 'L';
        else cur = 'D';
        
        int maxCnt = cnt;

        if (medicine[start] == cur) maxCnt = Math.max(maxCnt, dfs(cnt + 1, start + 1, end));
        if (medicine[end] == cur) maxCnt = Math.max(maxCnt, dfs(cnt + 1, start, end - 1));

        return dp[start][end][curIdx] = maxCnt;
    }
}