import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, len;
    private static int[] arr, tails;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        tails = new int[n];
        for (int i = 0; i < n; i++) {
            int x = arr[i];
            int pos = lowerBound(len, x);
            tails[pos] = x;
            if (pos == len) len++;
        } 
        
        System.out.print(len);
    }
    
    private static int lowerBound(int r, int x) {
        int l = 0;
        while (l < r) {
            int m = (l+r)>>>1;
            if (tails[m]>=x) r = m;
            else l = m+1;
        }
        return l;
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