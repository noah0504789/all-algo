import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[][] dp;
    private static int[] prefixSum;
    private static int tc, k;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        tc = readInt();

        while (tc-- > 0) {
            k = readInt();

            prefixSum = new int[k+1];
            for (int i = 1; i <= k; i++) prefixSum[i] += (readInt() + prefixSum[i-1]);

            dp = new int[k+1][k+1];
            for (int size = 1; size < k; size++) {
                for (int i = 1; i <= k-size; i++) {
                    int j = i+size;
                    if (j > k) break;
                    
                    dp[i][j] = Integer.MAX_VALUE;
                    
                    for (int k = i; k < j; k++) dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k+1][j]+prefixSum[j]-prefixSum[i-1]);
                }
            }
            
            bw.write(dp[1][k]+"\n");
        }

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
