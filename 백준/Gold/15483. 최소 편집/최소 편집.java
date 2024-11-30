import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static String a, b;
    private static long[][] dp;
    private static int aSize, bSize;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        a = br.readLine();
        b = br.readLine();

        aSize = a.length();
        bSize = b.length();

        dp = new long[aSize+1][bSize+1];

        for (int i = 0; i <= aSize; i++) dp[i][0] = i;
        for (int j = 0; j <= bSize; j++) dp[0][j] = j;

        for (int i = 1; i <= aSize; i++) {
            for (int j = 1; j <= bSize; j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1])+1;
            }
        }

        bw.write(dp[aSize][bSize]+"");
        bw.flush();
    }
}
