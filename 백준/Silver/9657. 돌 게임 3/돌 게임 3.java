import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static boolean[] win;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        win = new boolean[Math.max(n, 4)+1];
        win[1] = win[3] = win[4] = true;
        
        for (int i = 5; i <= n; i++) win[i] = !win[i-1] || !win[i-3] || !win[i-4];

        System.out.print(win[n] ? "SK" : "CY");
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