import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, w;
    private static char[][] arr;
    
    public static void main(String... args) throws IOException {        
        sb = new StringBuilder();
        
        n = readInt();
        w = 1<<n;
        arr = new char[w][w];
        for (int i = 0; i < w; i++) Arrays.fill(arr[i], ' ');
        
        star(n, 0, 0, w);
        
        for (int i = 0; i < w; i++) {
            int last = -1;
            for (int j = w-1; j >= 0; j--) {
                if (arr[i][j] == '*') {
                    last = j;
                    break;
                }
            }
            if (last != -1) sb.append(arr[i], 0, last+1);             
            if (i < w-1) sb.append('\n');
        }

        System.out.print(sb);
    }
    
    private static void star(int n, int r, int c, int w) {
        if (n == 0) {
            arr[r][c] = '*';
            return;
        }
        
        w >>= 1;
        
        star(n-1, r, c, w);
        star(n-1, r, c+w, w);
        star(n-1, r+w, c, w);
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