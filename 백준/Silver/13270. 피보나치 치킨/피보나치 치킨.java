import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, setSize;
    private static List<Integer> student, chicken;
    private static int[][][] dp; // 셋트이름/학생수/min-max
    private static final int INF = Integer.MAX_VALUE;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        student = new ArrayList<>();
        student.add(2);
        student.add(3);
        
        chicken = new ArrayList<>();
        chicken.add(1);
        chicken.add(2);
        
        for (int i = 2; i < n; i++) {
            int prev = student.get(i-1);
            int val = student.get(i-2) + student.get(i-1);
            if (val > n) break;
            student.add(val);
            chicken.add(prev);
        }
        
        setSize = chicken.size();
        
        dp = new int[setSize][n+1][2];
        for (int i = 0; i < setSize; i++) {
            for (int j = 1; j <= n; j++) dp[i][j][0] = INF;
            
            dp[i][0][0] = dp[i][0][1] = 0;
        }
        
        for (int i = 0; i < setSize; i++) {
            int st = student.get(i);
            int ch = chicken.get(i);
            
            for (int j = 1; j <= n; j++) {                
                if (i > 0) {
                    dp[i][j][0] = dp[i-1][j][0];
                    dp[i][j][1] = dp[i-1][j][1];
                }
                
                if (j < st) continue;
                if (dp[i][j-st][0] != INF) dp[i][j][0] = Math.min(dp[i][j][0], dp[i][j-st][0] + ch);
                dp[i][j][1] = Math.max(dp[i][j][1], dp[i][j-st][1] + ch);
            }
        }
        
        System.out.print(dp[setSize-1][n][0] + " " + dp[setSize-1][n][1]);
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