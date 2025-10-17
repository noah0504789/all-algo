import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, idx, l, r, m;
    private static long cnt;
    private static int[] a, b, c, d, ab, cd;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        a = new int[n];
        b = new int[n];
        c = new int[n];
        d = new int[n];
        
        for (int i = 0; i < n; i++) {
            a[i] = readInt();
            b[i] = readInt();
            c[i] = readInt();
            d[i] = readInt();
        }
        
        m = n*n;       
        ab = new int[m];
        cd = new int[m];
        
        idx = 0;
        for (int i : a) {
            for (int j : b) ab[idx++] = i+j;
        }
        
        idx = 0;
        for (int i : c) {
            for (int j : d) cd[idx++] = i+j;
        }        
        
        Arrays.sort(ab);
        Arrays.sort(cd);

        cnt = 0L;
         
        l = 0;
        r = m-1;
                
        while (l < m && r >= 0) {
            int sum = ab[l] + cd[r];
            if (sum == 0) {
                int valAB = ab[l], valCD = cd[r];
                long cntAB = 0, cntCD = 0;
                
                while (l < m && ab[l] == valAB) {l++; cntAB++;}
                while (r >= 0 && cd[r] == valCD) {r--; cntCD++;}
                
                cnt += (long)(cntAB * cntCD);
            } else if (sum < 0) {
                l++;
            } else {
                r--;
            }
        }
                
        System.out.print(cnt);
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