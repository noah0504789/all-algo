import java.io.*;
import java.util.*;

public class Main {
    
    private static final String NO_VISITORS = "SAD";
    private static boolean flag;
    private static int n, x, maxSum, ans, l, r, cnt;
    private static int[] arr; 
    
    public static void main(String... args) throws IOException {
        n = readInt();
        x = readInt();
        
        maxSum = 0;
        l = 0;
        r = x-1;
        
        arr = new int[n];        
        for (int i = 0; i < n; i++) {
            arr[i] = readInt();
            if (arr[i] > 0) flag = true;
            if (i < x) maxSum += arr[i];
        }
        
        if (!flag) {
            System.out.print(NO_VISITORS);
            return;
        }
        
        ans = maxSum;
        cnt = 1;
        
        while (r+1 < n) {
            maxSum += arr[++r];
            maxSum -= arr[l++];
            if (ans < maxSum) {
                ans = maxSum;
                cnt = 1;
            } else if (ans == maxSum) {
                cnt++;
            }
        }

        System.out.println(ans);
        System.out.println(cnt);
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