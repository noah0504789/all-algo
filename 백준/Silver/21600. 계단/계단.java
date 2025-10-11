import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, ans, cur;
    private static boolean flag;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        ans = cur = 0;
        
        for (int i = 0; i < n; i++) {
            cur = Math.min(cur+1, readInt());
            ans = Math.max(ans, cur);
        }

        System.out.print(ans);
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