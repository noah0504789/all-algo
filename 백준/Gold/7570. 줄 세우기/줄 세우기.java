import java.io.*;
import java.util.*;

public class Main {
        
    private static int n, cur, best;
    private static int[] pos;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        pos = new int[n+1];
        for (int i = 0; i < n; i++) pos[readInt()] = i;
        
        cur = 1;
        best = 1;
        for (int v = 1; v < n; v++) {
            if (pos[v] < pos[v+1]) cur++;
            else {
                best = Math.max(best, cur);
                cur = 1;                
            }
        }

        System.out.print(n-Math.max(best, cur));
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