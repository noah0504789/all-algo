import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int[] arr;
    private static int n, m;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        Arrays.sort(arr);
        
        m = readInt();
        for (int i = 0; i < m; i++) sb.append(binarySearch(readInt())).append(" ");

        System.out.print(sb);
    }
    
    private static int binarySearch(int target) {
        int l = 0, r = n-1;
        while (l <= r) {
            int m = (l+r)/2;
            if (arr[m] == target) return 1;
            else if (arr[m] > target) r = m-1;
            else l = m + 1;
        }
        
        return 0;
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