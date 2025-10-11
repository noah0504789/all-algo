import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, l, r, ans;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        arr = new int[n];        
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        Arrays.sort(arr);
        
        ans = 0;
        l = 0;
        r = n-1;
        
        while (l < r) {
            int sum = arr[l]+arr[r];
            if (m == sum) {
                ans++;
                l++;
                r--;
            } else if (m > sum) {
                l++;
            } else {
                r--;
            }
        }

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