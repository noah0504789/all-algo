import java.io.*;

public class Main {
    private static final int MOD = 1_000_000_007;
    private static long[] fact;
    private static int n, k;

    public static void main(String... args) throws IOException {
        n = readInt();        
        k = readInt();
        
        fact = new long[n+1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i-1] * i % MOD;
        
        long nF = fact[n] % MOD;
        long kF_inv = pow(fact[k], MOD-2);
        long n_kF_inv = pow(fact[n-k], MOD-2);
                
        System.out.print( (nF * kF_inv % MOD) * n_kF_inv % MOD );
    }
    
    private static long pow(long a, long b) {
        long result = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                result *= a;
                result %= MOD;
            }
            a *= a;
            a %= MOD;
            
            b /= 2;
        }
        
        return result;
    }

    private static int readInt() throws IOException {
        int result = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();

        while (c >= '0' && c <= '9') {
            result *= 10;
            result += c - '0';
            c = System.in.read();
        }

        return result;
    }
}
