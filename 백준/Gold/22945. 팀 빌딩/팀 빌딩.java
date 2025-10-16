import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, l, r, max, width, h;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();

        l = 0;
        r = n-1;
        max = 0;
        
        while (l < r) {
            width = r-l-1;
            if (width == 0) break;
            h = Math.min(arr[l], arr[r]);
            
            max = Math.max(max, width*h);
            
            if (arr[l] < arr[r]) l++;
            else r--;
        }
        
        System.out.print(max);
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