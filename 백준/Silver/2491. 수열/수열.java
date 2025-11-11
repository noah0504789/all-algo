import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, inc, dec, ans;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        inc = 1;
        dec = 1;
        ans = 1;
        for (int i = 1; i < n; i++) {
            inc = arr[i-1] <= arr[i] ? inc+1 : 1;
            dec = arr[i-1] >= arr[i] ? dec+1 : 1;
            ans = Math.max(ans, Math.max(inc, dec));
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