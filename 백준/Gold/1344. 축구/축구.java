import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int inn = 18;
    private static int[] prime = {2, 3, 5, 7, 11, 13, 17};
    private static double pa, pb, qa, qb, ans, prime_a, prime_b;
    private static long[][] comb;    
    
    public static void main(String... args) throws IOException {
        pa = readInt() / 100.0;
        pb = readInt() / 100.0;
        qa = 1 - pa;
        qb = 1 - pb;
        
        comb = new long[inn+1][inn+1];
        for (int i = 1; i <= inn; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
        }
              
        for (int i : prime) {
            prime_a += comb[inn][i] * Math.pow(pa, i) * Math.pow(qa, inn-i);
            prime_b += comb[inn][i] * Math.pow(pb, i) * Math.pow(qb, inn-i);
        }
                        
        ans = 1 - (1-prime_a) * (1-prime_b);

        System.out.printf("%.6f", ans);
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