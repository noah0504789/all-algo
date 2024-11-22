import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[] travels, tickets, icFees, icPrices;
    private static long sum, cnt;
    private static int n, m, s, e;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        travels = new long[n+1];

        s = readInt();

        for (int i = 1; i < m; i++) {
            e = readInt();

            if (s < e) {
                travels[s]++;
                travels[e]--;
            } else {
                travels[e]++;
                travels[s]--;
            }

            s = e;
        }

        tickets = new long[n];
        icFees = new long[n];
        icPrices = new long[n];

        for (int i = 1; i < n; i++) {
            tickets[i] = readInt();
            icFees[i] = readInt();
            icPrices[i] = readInt();
        }

        sum = 0;
        cnt = 0;

        for (int i = 1; i < n; i++) {
            cnt += travels[i];
            sum += Math.min(tickets[i] * cnt, icFees[i] * cnt + icPrices[i]);
        }

        bw.write(sum+"");
        bw.flush();
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
