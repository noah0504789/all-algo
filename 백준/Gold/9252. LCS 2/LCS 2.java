import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringBuilder sb;

    private static char[] x, y;
    private static int[][] dp;
    private static int xLen, yLen, r, c;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        x = br.readLine().toCharArray();
        y = br.readLine().toCharArray();

        xLen = x.length;
        yLen = y.length;

        dp = new int[xLen+1][yLen+1];

        for (int i = 1; i <= xLen; i++) {
            for (int j = 1; j <= yLen; j++) {
                if (x[i-1] == y[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        r = xLen;
        c = yLen;

        while (r > 0 && c > 0) {
            if (x[r-1] == y[c-1]) {
                sb.insert(0, x[r-1]);
                r--;
                c--;
            } else if(dp[r-1][c] >= dp[r][c-1]) {
                r--;
            } else {
                c--;
            }
        }

        bw.write(dp[xLen][yLen]+"");
        bw.write("\n");
        bw.write(sb.toString());

        bw.flush();
    }
}