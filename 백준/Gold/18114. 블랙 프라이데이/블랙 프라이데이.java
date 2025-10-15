import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, c;
    private static int[] arr;
    private static boolean flag;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        c = readInt();
        arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = readInt();
            if (arr[i] == c) flag = true;
        }
        
        if (flag) {
            System.out.print("1");
            return;
        }
        
        Arrays.sort(arr);
        
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (arr[i] + arr[j] == c) {
                    System.out.print("1");
                    return;
                }
            }
        }
        
        for (int i = 0; i <= n-3; i++) {
            int l = i+1, r = n-1;
            while (l < r) {
                int sum = arr[i] + arr[l] + arr[r];
                if (sum == c) {
                    System.out.print("1");
                    return;
                } else if (sum < c) {
                    l++;
                } else if (sum > c) {
                    r--;
                }
            }
        }

        System.out.print("0");
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