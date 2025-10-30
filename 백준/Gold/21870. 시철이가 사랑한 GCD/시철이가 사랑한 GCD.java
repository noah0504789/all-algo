import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, max;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();

        System.out.print(solve(0, n-1));
    }
    
    private static int solve(int l, int r) {        
        int size = r-l+1;
        if (size == 1) return arr[l];
        if (size == 2) return arr[l] + arr[r];
        
        int m = l + size/2 -1;
        
        return Math.max(solve(l, m) + gcd2(m+1, r), solve(m+1, r) + gcd2(l, m));
    }
    
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a%b);
    }
    
    private static int gcd2(int l, int r) {
        int res = arr[l];
        for (int i = l+1; i <= r; i++) {
            res = gcd(res, arr[i]);
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
}