import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, p = 1_000_000_007;
    private static long ans;
    private static int[] arr;
    private static long[] pow2;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        Arrays.sort(arr);
        
        pow2 = new long[n];
        pow2[0] = 1;
        for (int i = 1; i<n; i++) pow2[i] = (pow2[i-1] * 2) % p;
        
        ans = 0L;
        for (int i = 0; i < n; i++) {
            long maxCnt = pow2[i];
            long minCnt = pow2[n-1-i];
            long share = (maxCnt-minCnt + p) % p;
            ans += share * (arr[i] % p);
            ans %= p;
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