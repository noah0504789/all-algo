import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;
    private static long n, a, ans;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        n = readLong();
        a = readLong();
        
        sb.append((n-a)%n).append(" ");
        
        long[] result = xGcd(a, n);
        sb.append(result[0] == 1 ? ((result[1] % n) + n) % n : -1);
        
        System.out.print(sb);
    }
    
    public static long[] xGcd(long a, long b) {
        if (b == 0) return new long[]{a, 1, 0};
        
        long[] values = xGcd(b, a % b);
        long gcd = values[0], x = values[2], y = values[1] - (a/b) * values[2];
        
        return new long[]{gcd, x, y};
    }
    
    public static long readLong() throws IOException {
        long r = 0, c = System.in.read();
        
        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }
        
        return r;
    }
}