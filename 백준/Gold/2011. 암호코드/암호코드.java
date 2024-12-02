import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static final int MOD = 1_000_000;
    private static String input;
    private static int[] dp;
    private static int n, prev, cur, twoDigits;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input = br.readLine();
        n = input.length();

        // 암호가 0으로 시작하면 해석 불가능
        if (input.charAt(0) == '0') {
            bw.write("0\n");
            bw.flush();
            return;
        }

        dp = new int[n+1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            cur = input.charAt(i-1)-'0';
            prev = input.charAt(i-2)-'0';

            if (cur > 0) dp[i] = dp[i-1];

            twoDigits = prev * 10 + cur;
            if (twoDigits >= 10 && twoDigits <= 26) dp[i] = (dp[i] + dp[i-2]) % MOD;
        }

        bw.write(dp[n]+"");
        bw.flush();
    }
}
