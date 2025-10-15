import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, a, b, c;
    private static long minAbs;
    private static int[] arr; 
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        Arrays.sort(arr);
        minAbs = Long.MAX_VALUE;
        
        for (int i = 0; i <= n-3; i++) {
            int l = i+1, r = n-1;
            while (l < r) {
                long sum = (long) arr[i] + arr[l] + arr[r];                
                long abs = Math.abs(sum);
                if (minAbs > abs) {
                    minAbs = abs;
                    a = arr[i];
                    b = arr[l];
                    c = arr[r];
                    if (sum == 0) {
                        System.out.print(a + " " + b + " " + c);
                        return;
                    }
                } 
                
                if (sum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }

        System.out.print(a + " " + b + " " + c);
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