import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static char[] input;
    private static boolean[][] isPalindrome;
    private static int[] dp;
    private static int size;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input = br.readLine().toCharArray();
        size = input.length;

        isPalindrome = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            isPalindrome[i][i] = true;

            if (i+1<size && input[i] == input[i+1]) isPalindrome[i][i+1] = true;
        }

        for (int length = 3; length <= size; length++) {
            for (int start = 0; start < size; start++) {
                int end = start + length - 1;
                if (end >= size) break;

                if (input[start] == input[end]) isPalindrome[start][end] = isPalindrome[start+1][end-1];
            }
        }

        dp = new int[size];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int end = 0; end < size; end++) {
            for (int start = 0; start <= end; start++) {
                if (!isPalindrome[start][end]) continue;

                dp[end] = start == 0 ? 1 : Math.min(dp[end], dp[start-1] + 1);
            }
        }

        bw.write(dp[size-1]+"");
        bw.flush();
    }
}