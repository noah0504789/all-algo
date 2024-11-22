import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] travels, tickets, icFees, icPrices;
    private static int n, m, s, e, sum, cnt;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        travels = new int[n+1];

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

        tickets = new int[n];
        icFees = new int[n];
        icPrices = new int[n];

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
