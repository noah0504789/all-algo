import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[] moves = {1, 3, 4};
    private static boolean[] win;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        win = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            for (int k : moves) {
                if (i-k > 0 && !win[i-k]) {
                    win[i] = true;
                    break;
                }
            }
        }

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