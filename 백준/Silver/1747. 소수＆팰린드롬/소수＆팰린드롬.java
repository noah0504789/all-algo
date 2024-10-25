import java.io.*;
import java.util.*;

public class Main {
    
    private static boolean[] noPrime;
    private static int n;
    
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        if (n == 1) {
            System.out.print(2);
            System.exit(0);
        }
        
        noPrime = new boolean[2_000_001];
        noPrime[0] = noPrime[1] = true;
        
        for (int i = 2; i <= Math.sqrt(2_000_000); i++) {
            if (noPrime[i]) continue;
            
            for (int j = i * i; j <= 2_000_000; j += i) noPrime[j] = true;
        }
                
        while (true) {
            if (isPrime(n) && isPalidrome(n)) break;
            
            n++;
        }
        
        System.out.print(n);
    }
    
    public static boolean isPrime(int n) {
        return !noPrime[n];
    }
    
    public static boolean isPalidrome(int n) {
        int N = n, k = 0;
        
        while (N > 0) {
            k *= 10;
            k += N % 10;
            N /= 10;
        }

        return n == k;
    }
    
    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        
        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }
        
        return r;
    }
}