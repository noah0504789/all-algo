import java.io.*;
import java.util.*;

public class Main {
    
    private static final String w = "SK", l = "CY";
    private static int n;    
    private static Boolean[] win;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        win = new Boolean[n+1];
        //win[1] = win[3] = true;
        
        System.out.print(dfs(1, true) ? w : l);
    }
    
    private static boolean dfs(int i, boolean res) {
        if (i == n) return res;
        if (i > n) return false;
        if (win[i] != null) return win[i];
        
        return win[i] = dfs(i+1, !res) || dfs(i+3, !res);
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