import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] t, p;
    private static int[] dp;

    public static void main(String... args) throws IOException {
        n = readInt();
        t = new int[n + 2]; // 1 ~ n
        p = new int[n + 2];
        dp = new int[n + 2]; // 1 ~ n+1

        for (int i = 1; i <= n; i++) {
            t[i] = readInt();
            p[i] = readInt();
        }

        // dp[i] = i일부터 시작했을 때 얻을 수 있는 최대 수익
        // 뒤에서 앞으로 채운다
        for (int i = n; i >= 1; i--) {
            int next = i + t[i]; // 이 상담이 끝난 다음 날

            if (next <= n + 1) {
                // 오늘 상담을 하는 경우 vs 안 하는 경우
                dp[i] = Math.max(p[i] + dp[next], dp[i + 1]);
            } else {
                // 오늘 상담을 시작하면 퇴사 전에 못 끝남 → 오늘 상담은 못 함
                dp[i] = dp[i + 1];
            }
        }

        System.out.println(dp[1]);
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
