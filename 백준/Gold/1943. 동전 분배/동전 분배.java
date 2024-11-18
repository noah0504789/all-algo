import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;
    private static final int tc = 3;
    private static int n, totalSum;
    private static boolean[] dp;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < tc; i++) {
            n = readInt();
            totalSum = 0;
            List<Coin> coins = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                int value = readInt();
                int count = readInt();
                coins.add(new Coin(value, count));
                totalSum += value * count;
            }

            if (totalSum % 2 != 0) {
                bw.write("0\n");
                continue;
            }

            int target = totalSum / 2;
            dp = new boolean[target + 1];
            dp[0] = true;

            for (Coin coin : coins) {
                for (int j = target; j >= coin.value; j--) {
                    for (int k = 1; k <= coin.count && k * coin.value <= j; k++) {
                        if (dp[j - k * coin.value]) {
                            dp[j] = true;
                            break;
                        }
                    }
                }
            }

            bw.write(dp[target] ? "1\n" : "0\n");
        }

        bw.flush();
    }

    static class Coin {
        int value, count;

        Coin(int value, int count) {
            this.value = value;
            this.count = count;
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