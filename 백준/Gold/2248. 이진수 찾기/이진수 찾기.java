import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static int n, l;
    private static long i, cnt0;
    private static long[][] comb; // n자리일때, 켜진 비트가 m개 이하
    
    public static void main(String... args) throws IOException {
        n = readInt();
        l = readInt();
        i = readLong();
        
        comb = new long[32][32];
        for (int i = 0; i < 32; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
        }
        
        while (n-- > 0) {
            cnt0 = countWithLimitOnes(n, l);
            
            if (i <= cnt0) {
                sb.append('0');
            } else {
                sb.append('1');
                i -= cnt0;
                l--;
            }
        }

        System.out.print(sb);
    }
    
    private static long countWithLimitOnes(int n, int l) {
        if (l < 0) return 0;
        if (n == 0) return 1;
        
        long sum = 0;
        for (int i = 0; i <= Math.min(n, l); i++) sum += comb[n][i];
        
        return sum;
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