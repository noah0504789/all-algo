import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int last;
    private static long tc, k;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
                
        tc = readLong();
        for (int turn = 1; turn <= tc; turn++) {
            k = readLong();  
            
            last = 64 - Long.numberOfLeadingZeros(k);
            
            sb.append(rst(turn, solve(k, last, 0))).append("\n");
        }

        System.out.print(sb);
    }
    
    private static int solve(long k, int idx, int toggle) {
        if (idx == 1) return toggle == 0 ? 0 : 1;
        
        long l = pow(2, idx-1)-1;
        
        if (k <= l) return solve(k, idx-1, toggle);
        if (k == l+1) return toggle == 0 ? 0 : 1;
        
        long p = k - (l+1);
        long mirror = l - p +1;
        
        return solve(mirror, idx-1, toggle^1);
    }
    
    private static long pow(long a, long b) {
        long res = 1;
        while (b>0) {
            if ((b&1)!=0) res*=a;
            a*=a;
            b>>=1;
        }
        return res;
    }
    
    private static String rst(int turn, int ans) {
        return "Case #"+turn+": "+ans;
    }

    public static long readLong() throws IOException {
        long r = 0, c = System.in.read();
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