import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, n, k, l, r, cnt, min;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        
        while (tc-- > 0) {
            n = readInt();
            k = readInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = readInt();
            
            Arrays.sort(arr);
            
            l = 0;
            r = n-1;
            cnt = 0;
            min = Integer.MAX_VALUE;
            
            while (l < r) {
                int sum = arr[l] + arr[r];
                int close = Math.abs(sum-k);
                if (min > close) {
                    min = close;
                    cnt = 1;
                } else if (min == close) {
                    cnt++;
                } 
                
                if (sum > k) r--;
                else if (sum == k) {r--; l++;}
                else l++;
            }
            
            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
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