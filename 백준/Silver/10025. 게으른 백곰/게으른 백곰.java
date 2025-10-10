import java.io.*;
import java.util.*;

public class Main {
    
    private static final int MAX = 1_000_000;
    private static int n, k, g, x, maxIdx;
    private static int ans, sum, total;    
    private static int[] gArr;
    
    public static void main(String... args) throws IOException {
        sum = total = maxIdx = 0;
        
        n = readInt();
        k = readInt();
        
        gArr = new int[MAX+1];
        
        for (int i = 0; i < n; i++) {
            g = readInt();
            x = readInt();
            
            gArr[x] = g;
            total += g;
            if (x <= k*2) sum+=g;
            
            maxIdx = Math.max(maxIdx, x);
        }
        
        if (sum == total) {
            System.out.print(total);
            return;
        }
        
        ans = sum;
        
        int l = 0, r = k*2;
        while (r < maxIdx) {
            sum -= gArr[l++];
            sum += gArr[++r];
            
            ans = Math.max(ans, sum);
        }

        System.out.print(ans);
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

