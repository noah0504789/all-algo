import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        Arrays.sort(arr);
        
        m = readInt();
        for (int i = 0; i < m; i++) {
            int target = readInt();
            sb.append(upperBound(target) - lowerBound(target)).append(" ");
        }

        System.out.print(sb);
    }
    
    private static int upperBound(int target) {
        int l = 0, r = n;
        while (l < r) {
            int mid = (l+r)>>>1;
            if (arr[mid] <= target) l = mid+1;
            else r = mid;
        }
        return l;
    }
    
    private static int lowerBound(int target) {
        int l = 0, r = n;
        while (l < r) {
            int mid = (l+r)>>>1;
            if (arr[mid] < target) l = mid+1;
            else r = mid;
        }
        return l;
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