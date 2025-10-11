import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, l, r, min, minL, minR;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        l = 0;
        r = n-1;
        min = Integer.MAX_VALUE;
        
        while (l < r) {
            int sum = arr[l] + arr[r];
            int diff = Math.abs(sum);
            if (diff < min) {
                min = diff;
                minL = l;
                minR = r;
                if (diff == 0) break;
            } 
            
            if (sum > 0) r--;
            else l++;
        }

        System.out.print(arr[minL] + " " + arr[minR]);
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