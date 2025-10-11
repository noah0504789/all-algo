import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, l, r, h;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        k = h = 1;
        l = r = 0;
        
        while (r+1 < n) {
            if (arr[r+1] < h+1) {
                l++;
                r = l;
                h = 1;
            } else if (arr[r+1] >= h+1) {
                r++;
                h++;
                k = Math.max(k, h);
            }
        }

        System.out.print(k);
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