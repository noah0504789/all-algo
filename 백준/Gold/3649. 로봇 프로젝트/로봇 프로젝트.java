import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int x, n, l, r;
    private static boolean found;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {        
        sb = new StringBuilder();
        
        while (true) {
            x = readInt();
            if (x == Integer.MIN_VALUE) break;
            
            x *= 10_000_000;
            n = readInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = readInt();
            Arrays.sort(arr);
        
            l = 0;
            r = n-1;
            found = false;
        
            while (l < r) {
                int sum = arr[l] + arr[r];
                if (sum == x) {
                    sb.append("yes ").append(arr[l]).append(" ").append(arr[r]).append("\n");
                    found = true;
                    break;
                } else if (sum > x) {
                    r--;                
                } else {
                    l++;
                }
            }
        
            if (!found) sb.append("danger").append("\n");
        }
        
        System.out.print(sb);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') {
            if (c == -1) return Integer.MIN_VALUE;
            c = System.in.read();
        }
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