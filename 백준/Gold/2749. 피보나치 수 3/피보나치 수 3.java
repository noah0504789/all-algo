import java.io.*;
import java.util.*;

public class Main {
    
    private static final long p = 1_000_000;
    private static long n;
    
    public static void main(String... args) throws IOException {
        // 1. 피보나치 행렬(m)
        // 1 1
        // 1 0
        
        // 피보나치 행렬을 n 거듭제곱하면
        // F(n+1) F(n)
        // F(n) F(n-1)
        
        // 이렇게 나와야 함
        
        // 2. fast doubling
        // m^n = F(n+1) F(n)
        //       F(n) F(n-1)
        
        // n = 2k라 가정하면
        // m^k^2 = [F(n+1) F(n)  곱하기 [F(n+1) F(n)
        //          F(n) F(n-1)]       F(n) F(n-1)]
        
        // 3. 전개하기 (m^n과 m^k^2은 같음. 같은 자리에 같은 값)
        // F_2k+1 = F_k+1^2 + F_k^2 
        // F_2k   = F_k * (F_k+1 + F_k-1)
        //        = F_k * (F_k+1 + (F_k+1 - F_k))
        //        = F_k * (2 * F_k+1 - F_k)        

        System.out.print(fib(readLong())[0]);
    }
    
    private static long[] fib(long n) {
        if (n == 0) return new long[]{0, 1};
        
        long[] p_ = fib(n>>1);
        long a = p_[0], b = p_[1];
        
        long c = 2 * b;
        c %= p;
        c += (p - a);
        c %= p;
        c *= a;
        c %= p;
        
        long d = a * a;
        d %= p;
        d += b * b;
        d %= p;
        
        if (n%2==0) return new long[]{c, d};
        
        return new long[]{d, (c+d)%p};
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