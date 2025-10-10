import java.io.*;
import java.util.*;

public class Main {
    
    private static final int LION = 1;
    private static int n, k, ans;
    private static int start, l, cnt;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        ans = n+1;
                
        arr = new int[n];        
        for (int i = 0; i < n; i++) arr[i] = readInt();
                        
        cnt = 0;
        l = 0;
        
        for (int r = 0; r < n; r++) {
            if (arr[r] == LION) cnt++;
            
            while (cnt == k) {
                ans = Math.min(ans, r-l+1);
                                
                if (arr[l] == LION) cnt--;
                
                l++;                
            }
        }

        System.out.print(ans == n+1 ? "-1" : ans+"");
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

