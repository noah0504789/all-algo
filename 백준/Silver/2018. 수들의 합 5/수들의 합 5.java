import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, l, r, sum, cnt;
    
    public static void main(String... args) throws IOException {
        n = readInt();
                        
        l = r = 1;
        sum = 1;
        cnt = 0;
        
        while (l <= n) {
            if (sum == n) cnt++;
            
            if (sum >= n) {
                sum -= l;
                l++;
            } else {
                r++;
                if (r > n) break;
                sum += r;
            }       
        }

        System.out.print(cnt);
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