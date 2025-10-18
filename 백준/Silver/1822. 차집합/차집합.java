import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m, cnt;
    private static int[] a, b;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        n = readInt();
        m = readInt();
        a = new int[n];
        b = new int[m];
        for (int i = 0; i < n; i++) a[i] = readInt();
        for (int i = 0; i < m; i++) b[i] = readInt();
        
        Arrays.sort(a);
        Arrays.sort(b);
        cnt = 0;
        for (int target : a) {
            int l = 0, r = m-1;
            boolean flag = false;
            while (l <= r) {
                int m = (l+r)>>>1;
                if (b[m] == target) {flag = true; break;}
                else if (b[m] > target) r = m-1;
                else l = m+1;
            }
            
            if (!flag) {
                cnt++;
                sb.append(target).append(" ");
            }
        }
        
        if (cnt == 0) sb.append("0");
        else sb.insert(0, cnt+"\n");

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