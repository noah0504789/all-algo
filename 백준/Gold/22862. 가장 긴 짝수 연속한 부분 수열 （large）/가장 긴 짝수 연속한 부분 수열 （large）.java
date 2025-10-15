import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, l, odds, evens, max;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) arr[i] = readInt();
                       
        l = 0;
        odds = evens = 0;
        max = 0;
        for (int r = 0; r < n; r++) {
            if (arr[r] % 2 == 0) evens++;
            else odds++;
            
            while (odds > k) {
                if (arr[l] % 2 == 0) evens--;
                else odds--;
                l++;
            }
            
            max = Math.max(max, evens);
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