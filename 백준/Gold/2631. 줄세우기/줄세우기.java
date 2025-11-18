import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, v, len, pos;
    private static int[] arr, tail;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        tail = new int[n];
        for (int i = 0; i < n; i++) {
            v = arr[i];
            pos = lowerBound(len, v);
            tail[pos] = v;
            if (pos == len) len++;
        }

        System.out.print(n-len);
    }
    
    private static int lowerBound(int r, int t) {
        int l = 0;
        while (l < r) {
            int m = (l+r)>>>1;
            if (tail[m]>=t)r=m;
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