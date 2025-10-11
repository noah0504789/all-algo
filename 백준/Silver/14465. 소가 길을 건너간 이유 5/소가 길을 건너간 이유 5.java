import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, b, need, l, r, ans;
    private static boolean[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        b = readInt();
        
        need = 0;

        arr = new boolean[n+1];
        for (int i = 0; i < b; i++) {
            int idx = readInt();
            arr[idx] = true;
            if (idx <= k) need++;
        }
        
        if (need == 0) {
            System.out.print("0");
            return;
        }
                       
        l = 2;
        r = k+1;
        ans = need;
        
        while (r <= n) {
            if (arr[l-1]) need--;
            if (arr[r]) need++;
            
            ans = Math.min(ans, need);
            
            l++;
            r++;
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