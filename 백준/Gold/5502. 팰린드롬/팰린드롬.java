import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static char[] input;
    private static int[][] dp;
    private static int n, size;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        input = br.readLine().toCharArray();

        size = input.length;

        dp = new int[size][size];

        for (int i = 0; i < n; i++) {
            if (i+1 < size && input[i] == input[i+1]) dp[i][i+1] = 0;
            else if (i+1 < size) dp[i][i+1] = 1;
        }

        for (int lng = 3; lng <= size; lng++) {
            for (int start = 0; start < size; start++) {
                int end = start+lng-1;
                if (end >= size) break;

                dp[start][end] = Math.min(dp[start+1][end], dp[start][end-1]) + 1;

                if (input[start] == input[end]) dp[start][end] = Math.min(dp[start][end], dp[start+1][end-1]);
            }
        }

        bw.write(dp[0][size-1]+"");
        bw.flush();
    }
}