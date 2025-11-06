import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, a, b;
    private static int[][] arr;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j< n; j++) arr[i][j] = readInt();
        }
        
        solve(n, 0, 0);

        sb.append(a+"\n").append(b+"\n");
        
        System.out.print(sb);
    }
    
    private static void solve(int w, int r, int c) {
        if (w == 1 || isUniformed(w, r, c)) {
            count(r, c);
            return;
        }
        
        w >>= 1;
        
        solve(w, r, c);
        solve(w, r, c+w);
        solve(w, r+w, c);
        solve(w, r+w, c+w);
    }
    
    private static boolean isUniformed(int w, int r, int c) {
        for (int i = r; i < r+w; i++) {
            for (int j = c; j< c+w; j++) {
                if (arr[i][j] != arr[r][c]) return false;
            }
        }
        return true;
    }
    
    private static void count(int r, int c) {
        int val = arr[r][c];
        if (val == 0) a++;
        else b++;
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