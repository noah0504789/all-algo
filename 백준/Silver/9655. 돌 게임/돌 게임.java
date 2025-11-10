import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        System.out.print(solve(0, 0));
    }
    
    private static String solve(int i, int flip) {
        if (i == n-1) return flip == 0 ? "SK" : "CY";
        
        if (n-i > 3) return solve(i+3, flip^1);
        
        return solve(i+1, flip^1);
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