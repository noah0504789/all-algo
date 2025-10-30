import java.io.*;
import java.util.*;

public class Main {
    
    private static int init, n;
    private static long l, r;
    private static long[] len, ans; // 3^d
    private static long[][][] tot; // init/d/digit
    private static final int[][] rule= {
            {},
            {1, 3, 2},
            {2, 1, 1},
            {2, 3, 2}
        }; // init/++
    
    public static void main(String... args) throws IOException {
        init = readInt();
        l = readLong();
        r = readLong();
        n = readInt();        
       
        len = new long[21];
        len[0] = 1;
        for (int i = 1; i <= 20; i++) len[i] = len[i-1]*3;
        
        tot = new long[4][21][4];
        for (int s = 1; s <= 3; s++) {
            for (int k = 1; k <= 3; k++) tot[s][0][k] = (s==k) ? 1 : 0;
        }
        
        for (int d = 1; d <= n; d++) {
            for (int s = 1; s <= 3; s++) {            
                for (int c : rule[s]) {
                    tot[s][d][1] += tot[c][d-1][1];
                    tot[s][d][2] += tot[c][d-1][2];
                    tot[s][d][3] += tot[c][d-1][3];
                }
            }
        }
        
        ans = solve(init, n, l, r);
        
        System.out.println(ans[1] + " " + ans[2] + " " + ans[3]);
    }
    
    private static long[] solve(int s, int d, long ql, long qr) {
        long[] res = new long[4];
        if (ql > qr || d < 0) return res;
        
        long blockLen = len[d];
        if (ql == 0 && qr == blockLen-1) {
            res[1] = tot[s][d][1];
            res[2] = tot[s][d][2];
            res[3] = tot[s][d][3];
            return res;
        }
        
        if (d == 0) {
            res[s] = 1;
            return res;
        }
        
        long childLen = len[d-1];
        for (int i = 0; i < 3; i++) {
            int child = rule[s][i];
            long start = childLen * i, end = start + childLen - 1;
            long nl = Math.max(ql, start), nr = Math.min(qr, end);
            
            if (nl <= nr) {
                long[] got = solve(child, d-1, nl-start, nr-start);
                res[1] += got[1];
                res[2] += got[2];
                res[3] += got[3];
            }
        }
        
        return res;
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
    
    public static long readLong() throws IOException {
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