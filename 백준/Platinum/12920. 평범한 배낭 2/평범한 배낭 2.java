import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, v, c, k, size;
    private static List<Integer> vList, cList;
    private static int[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        vList = new ArrayList<>();
        cList = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            v = readInt();
            c = readInt();
            k = readInt();
            int cnt = 1;
            while (k > 0) {
                int take = Math.min(cnt, k);
                vList.add(v*take);
                cList.add(c*take);
                k-=take;
                cnt <<= 1;
            }
        }
        
        size = vList.size();
        
        dp = new int[m+1];
        for (int idx = 0; idx < size; idx++) {
            v = vList.get(idx);
            c = cList.get(idx);
            
            for (int i = m; i >= v; i--) dp[i] = Math.max(dp[i], dp[i-v] + c);
        }
        
        System.out.print(dp[m]);
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