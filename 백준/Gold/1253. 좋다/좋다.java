import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, ans;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        Arrays.sort(arr);
        
        ans = 0;
        
        for (int i = 0; i < n; i++) {
            int l = 0, r = n-1;
            
            while (l < r) {
                if (i == l) { l++; continue; }
                if (i == r) { r--; continue; }
                int sum = arr[l] + arr[r];
                
                if (sum == arr[i]) {
                    ans++;
                    break;
                } else if (sum < arr[i]) {
                    l++;
                } else if (sum > arr[i]) {
                    r--;
                }
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