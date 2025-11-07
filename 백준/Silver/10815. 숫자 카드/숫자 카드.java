import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m, target, l, r, mid;
    private static boolean flag;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        Arrays.sort(arr);
        
        m = readInt();
        for (int i = 0; i < m; i++) {
            target = readInt();
            
            l = 0;
            r = n-1;
            flag = false;
            while (l <= r) {
                mid = (l+r)>>1;
                if (arr[mid] == target) {flag = true; break;}
                else if (arr[mid] < target) l = mid+1;
                else r = mid-1;
            }
            
            sb.append(flag ? 1 : 0).append(" ");
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