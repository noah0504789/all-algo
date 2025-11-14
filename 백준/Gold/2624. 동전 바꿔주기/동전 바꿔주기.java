import java.io.*;
import java.util.*;

public class Main {
    
    private static int t, k, p, n;
    private static Coin[] coins;
    private static int[] dp, ndp;
    
    public static void main(String... args) throws IOException {
        t = readInt();
        k = readInt();
        
        coins = new Coin[k];
        for (int i = 0; i < k; i++) coins[i] = new Coin(readInt(), readInt());        
        
        dp = new int[t+1];
        dp[0] = 1;
        for (int i = 0; i < k; i++) {
            Coin coin = coins[i];
            int value = coin.value;
            int cnt = coin.cnt;
            // 현재 코인에서 1~cnt개까지 코인을 사용하였을 때 해당 값을 만드는 경우의 수
            ndp = new int[t+1];
            
            for (int s = 0; s <= t; s++) {
                if (dp[s] == 0) continue;
                
                for (int use = 0; use <= cnt; use++) {
                    int ns = s + use*value;
                    if (ns > t) break;
                    ndp[ns] += dp[s];
                }
            }
            
            dp = ndp;
        }

        System.out.print(dp[t]);
    }
    
    static class Coin implements Comparable<Coin> {
        int value, cnt;
        Coin(int value, int cnt) {
            this.value = value;
            this.cnt = cnt;
        }
        
        public int compareTo(Coin o) {
            return this.value - o.value;
        }
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