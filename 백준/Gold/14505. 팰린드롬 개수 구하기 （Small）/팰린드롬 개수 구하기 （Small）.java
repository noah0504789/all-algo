import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int[][] dp;
    private static char[] input;
    private static int size;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input = br.readLine().toCharArray();
        size = input.length;

        dp = new int[size+1][size+1];

        for (int i = 0; i < size; i++) {
            dp[i][i] = 1;

            if (i+1 < size && input[i] == input[i+1]) dp[i][i+1] = 3;
            else if (i+1 < size) dp[i][i+1] = 2;
        }

        for (int len = 3; len <= size; len++) {
            for (int start = 0; start < size; start++) {
                int end = start + len - 1;
                if (end >= size) break;

                if (input[start] == input[end]) dp[start][end] = dp[start+1][end] + dp[start][end-1] + 1;
                else dp[start][end] = dp[start+1][end] + dp[start][end-1] - dp[start+1][end-1];
            }
        }

        bw.write(dp[0][size-1]+"");

        bw.flush();
    }
}