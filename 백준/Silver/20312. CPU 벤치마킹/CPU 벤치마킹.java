import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, p = 1_000_000_007;
    private static long g, ans;    
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        for (int i = 0; i < n-1; i++) {
            m = readInt() % p;
            g = (m * ((g+1) % p)) % p;
            
            ans = (ans + g) % p;            
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