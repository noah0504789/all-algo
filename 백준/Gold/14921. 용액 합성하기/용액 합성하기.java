import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, bestAbs, bestSum, l, r;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        if (n == 2) {
            System.out.print(arr[0] + arr[1]);
            return;
        }
        
        l = 0;
        r = n-1;
        bestAbs = Integer.MAX_VALUE;
        bestSum = 0;
        while (l < r) {
            int sum = arr[l]+arr[r];
            int abs = Math.abs(sum);
            if (abs < bestAbs) {
                bestAbs = abs;
                bestSum = sum;
                if (abs == 0) break;
            }
            
            if (sum > 0) r--;
            else l++;
        }

        System.out.print(bestSum);
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