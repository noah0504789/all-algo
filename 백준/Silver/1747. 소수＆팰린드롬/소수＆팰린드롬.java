import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        if (n == 1) {
            System.out.print(2);
            System.exit(0);
        }
                
        while (true) {
            if (isPrime(n) && isPalidrome(n)) break;
            
            n++;
        }
        
        System.out.print(n);
    }
    
    public static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        
        return true;
    }
    
    public static boolean isPalidrome(int n) {
        String str = String.valueOf(n);
        
        for (int i = 0; i < str.length()/2; i++) {
            if (str.charAt(i) != str.charAt(str.length()-1-i)) return false;
        }
        
        return true;
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