import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String[] input;
    private static int[] input2;
    private static int n, m, h, p = 10_007;    
    private static List<Integer>[] block;
    private static int[][] dp;
    
    public static void main(String... args) throws IOException {
        input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        h = Integer.parseInt(input[2]);
        
        block = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            block[i] = new ArrayList<>();
            
            input2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(input2);
            
            block[i].add(0);
            for (int b : input2) block[i].add(b);
        }
        
        dp = new int[n][h+1];
        for (int i = 0; i < block[0].size(); i++) dp[0][block[0].get(i)] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= h; j++) {
                long val = 0;
                for (int h_ : block[i]) {
                    if (j-h_ >= 0) val += (dp[i-1][j-h_] % p);
                }
                
                dp[i][j] = (int)(val % p);
            }
        }

        System.out.print(dp[n-1][h]);
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