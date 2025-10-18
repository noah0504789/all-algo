import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, n, m, cnt;
    private static int[] a, b;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        tc = readInt();
        
        while (tc-- > 0) {
            n = readInt();
            m = readInt();
            a = new int[n];
            for (int i = 0; i < n; i++) a[i] = readInt();
            b = new int[m];
            for (int i = 0; i < m; i++) b[i] = readInt();
            
            Arrays.sort(b);
            
            cnt = 0;
            
            for (int target : a) {
                cnt += lowerBound(target);
            }
            
            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
    }
    
    private static int lowerBound(int target) {
        int l = 0, r = b.length;
        while (l < r) {
            int m = (l+r)>>>1;
            if (b[m] >= target) r = m;
            else l = m+1;
        }
        
        return l;
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