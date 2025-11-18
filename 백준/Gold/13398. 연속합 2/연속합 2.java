import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, ans;
    private static int[] arr, left, right;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        arr = new int[n];        
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        left = new int[n];
        left[0] = arr[0];
        for (int i = 1; i < n; i++) left[i] = Math.max(arr[i], left[i-1] + arr[i]);
        
        right = new int[n];
        right[n-1] = arr[n-1];
        for (int i = n-2; i >= 0; i--) right[i] = Math.max(arr[i], right[i+1] + arr[i]);
        
        ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) ans = Math.max(ans, left[i]);
        for (int i = 1; i < n-1; i++) ans = Math.max(ans, left[i-1]+right[i+1]);

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