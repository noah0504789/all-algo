import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static long cnt;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        Arrays.sort(arr);
        
        cnt = 0;
        
        for (int i = 0; i < n-2; i++) {
            if (arr[i] > 0) break;
            
            int l = i+1, r = n-1;
            while (l < r) {
                int sum = arr[i] + arr[l] + arr[r];
                
                if (sum == 0) {
                    if (arr[l] == arr[r]) {
                        int m = r-l+1;
                        cnt += m * (m-1) / 2;
                        break;
                    } else {
                        int lv = arr[l], rv = arr[r];
                        int lc = 1, rc = 1;
                        while (l+1 < r && arr[l+1] == lv) {l++; lc++;}
                        while (r-1 > l && arr[r-1] == rv) {r--; rc++;}
                        cnt += lc * rc;
                        l++; 
                        r--;
                    } 
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        System.out.print(cnt);
    }
    
    private static void check(int i, int j) {
                                             
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