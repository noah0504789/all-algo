import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, c0, c1, money, tailLen, len;
    private static int[] costs, num;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        costs = new int[n];
        for (int i = 0; i < n; i++) costs[i] = readInt();        
        
        m = readInt();
        
        c0 = 0;
        for (int d= 1; d < n; d++) if(costs[d] < costs[c0]) c0 = d;
        
        c1 = -1;
        for (int d= 1; d < n; d++) if(c1 == -1 || costs[d] < costs[c1]) c1 = d;
        
        if (c1 == -1 || costs[c1] > m) {
            System.out.print(0);
            return;
        }
        
        money = m - costs[c1];
        tailLen = money / costs[c0];
        len = tailLen + 1;
        
        num = new int[len];        
        num[0] = c1;
        Arrays.fill(num, 1, len, c0);
        money -= costs[c0] * tailLen;
        
        for (int i = 0; i < len; i++) {
            for (int d = n-1; d >= 0; d--) {
                if (i == 0 && d == 0) continue;
                int diff = costs[d] - costs[num[i]];
                if (diff <= money) {
                    money -= diff;
                    num[i] = d;
                    break;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int d : num) sb.append(d);

        System.out.print(sb);
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