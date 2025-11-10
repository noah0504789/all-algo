import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, ans, p = 1_000_000;
    private static int[][] prev, next;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        prev = new int[2][3];
        prev[0][0] = 1;
        
        for (int day = 0; day < n; day++) {
            next = new int[2][3];
            
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    int cur = prev[i][j];
                    if (cur == 0) continue;
                    
                    next[i][0] = (next[i][0]+cur)%p;
                    
                    if (j < 2) next[i][j+1] = (next[i][j+1]+cur)%p;
                    if (i < 1) next[i+1][0] = (next[i+1][0]+cur)%p;
                }
            }
            
            prev = next;
        }
        
        for (int[] v : prev) {
            ans += v[0]%p;
            ans += v[1]%p;
            ans += v[2]%p;
        }

        System.out.print(ans % p);
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