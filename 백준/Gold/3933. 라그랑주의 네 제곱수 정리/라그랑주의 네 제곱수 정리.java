import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, maxn = 32_767, lim = 181;
    private static int[] sq, ways;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        sq = new int[lim+1];
        for (int i = 0; i <= lim; i++) sq[i] = i*i;
        
        ways = new int[maxn+1];
        for (int a = 0; a <= lim; a++) {
            int s1 = sq[a];
            if (s1 > maxn) break;
            
            for (int b = a; b <= lim; b++) {
                int s2 = s1 + sq[b];
                if (s2 > maxn) break;
                
                for (int c = b; c <= lim; c++) {
                    int s3 = s2 + sq[c];
                    if (s3 > maxn) break;
                    
                    for (int d = c; d <= lim; d++) {
                        int s = s3 + sq[d];
                        if (s > maxn) break;
                        ways[s]++;
                    }
                }
            }
        }
        
        while ((n = readInt()) != 0) {
            sb.append(ways[n]).append("\n");
        }

        System.out.print(sb);
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