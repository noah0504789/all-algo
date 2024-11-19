import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static String a, b;
    private static int[][] dp;
    private static int aLen, bLen, ans;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        a = br.readLine();
        b = br.readLine();

        aLen = a.length();
        bLen = b.length();

        ans = 0;

        dp = new int[aLen+1][bLen+1];

        for (int i = 1; i <= aLen; i++) {
            for (int j = 1; j <= bLen; j++) {
                if (a.charAt(i-1) != b.charAt(j-1)) continue;

                dp[i][j] = dp[i - 1][j - 1] + 1;
                ans = Math.max(ans, dp[i][j]);
            }
        }

        bw.write(ans+"");
        bw.flush();
    }
}