import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int[] dp;
    private static int n, m, c, p;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = (int) (Double.parseDouble(st.nextToken()) * 100+0.5);

            if (n == 0 && m == 0) break;

            dp = new int[m+1];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());

                c = Integer.parseInt(st.nextToken());
                p = (int) (Double.parseDouble(st.nextToken()) * 100+0.5);

                for (int j = p; j <= m; j++) dp[j] = Math.max(dp[j], dp[j-p] + c);
            }

            bw.write(dp[m]+"");
            bw.write("\n");
        }

        bw.flush();
    }
}