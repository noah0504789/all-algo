import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int tc = 3;
    private static Coin[] coins;
    private static boolean[] dp;
    private static int[] cnts;
    private static int n, tot, coin, cnt, target;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < tc; i++) {
            n = readInt();

            coins = new Coin[n];
            tot = 0;

            for (int j = 0; j < n; j++) {
                coins[j] = new Coin(readInt(), readInt());

                tot += coins[j].sum();
            }
            
            if (tot % 2 != 0) {
                bw.write("0");
                bw.write("\n");
                continue;
            }

            target = tot / 2;
            dp = new boolean[target + 1];
            dp[0] = true;
            cnts = new int[target + 1];

            for (Coin cur : coins) {
                coin = cur.type;
                cnt = cur.cnt;

                Arrays.fill(cnts, 0);

                for (int j = 0; j <= target - coin; j++) {
                    if (!dp[j]) continue;
                    if (dp[j+coin]) continue;
                    if (cnts[j] == cnt) continue;

                    dp[j+coin] = true;
                    cnts[j+coin] = cnts[j] + 1;
                }
            }

            bw.write(dp[target] ? "1" : "0");
            bw.write("\n");
        }

        bw.flush();
    }

    static class Coin {
        final int type, cnt;

        public Coin(int type, int cnt) {
            this.type = type;
            this.cnt = cnt;
        }

        public int sum() {
            return type*cnt;
        }
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}