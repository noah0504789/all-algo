import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder(), sb2 = new StringBuilder();
    private static int allow, n, s, e, cur, cnt, d;
    private static long INF = Long.MAX_VALUE/4;
    private static int[] ps, cost, path;
    private static long[] dp;
    private static List<Integer> st;
    
    public static void main(String... args) throws IOException {
        allow = readInt();
        n = readInt();
        s = 0; 
        e = n+1;
        
        ps = new int[e+1];
        for (int i = 1; i <= e; i++) {
            ps[i] = readInt();
            ps[i] += ps[i-1];
        }
        
        cost = new int[e+1];
        for (int i = 1; i <= n; i++) cost[i] = readInt();
        cost[e] = 0;
        
        dp = new long[e+1]; // 0에서 i까지 가는데 드는 최소 비용
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        path = new int[e+1];
        
        for (int i = 0; i < e; i++) {
            if (dp[i] == INF) continue;
            for (int j = i+1; j <= e; j++) {
                int sum = ps[j]-ps[i];
                if (sum > allow) break;
                
                if (dp[j] > dp[i] + cost[j]) {
                    dp[j] = dp[i] + cost[j];
                    path[j] = i;
                }
            }
        }
        
        sb.append(dp[e]+"\n");
        
        d = e;
        cnt = 0;
        while ((d = path[d]) != 0) {
            cnt++;
            sb2.insert(0, d + " ");
        }

        sb.append(cnt).append("\n");
        sb.append(sb2.toString());        
        
        System.out.print(sb);
    }    
    
    // 출장거리가 제한거리를 초과하면 이곳에서 반드시 쉬어야 함
    // 출장거리가 제한거리를 초과하지 않으면, 쉴수도 있고 달릴수도 있음

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