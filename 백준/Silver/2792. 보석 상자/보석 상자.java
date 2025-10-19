import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, min, l, r, mid;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        arr = new int[m];
        
        for (int i = 0; i < m; i++) arr[i] = readInt();
        
        Arrays.sort(arr);
        
        min = 0;
        l = 1;
        r = arr[m-1];
        
        while (l <= r) {
            mid = (l+r)>>>1;
            if (can(mid)) {
                min = mid;
                r = mid-1;
            } else {
                l = mid+1;
            }
        }        

        System.out.print(min);
    }
    
    private static boolean can(int target) {
        long cnt = 0;
        for (int a : arr) {
            cnt += (a-1)/target + 1;
            
            if (cnt > n) return false;
        }
        
        return cnt <= n;
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