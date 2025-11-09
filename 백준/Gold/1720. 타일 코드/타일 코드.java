import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static long ans;
    private static long[] f;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        f = new long[n+1];
        f[0] = f[1] = 1;
        for (int i = 2; i <= n; i++) f[i] = f[i-1] + 2*f[i-2];
        
        ans = f[n];
        if (n%2==1) {
            ans += f[(n-1)/2];
        } else {
            ans += f[n/2];
            ans += 2*f[n/2-1];
        }

        System.out.print(ans/2);
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