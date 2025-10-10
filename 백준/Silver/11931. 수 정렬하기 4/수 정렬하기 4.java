import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        mergeSort(0, n-1);
        
        for (int i = 0; i < n; i++) sb.append(arr[i]+"\n");

        System.out.print(sb);
    }
    
    public static void mergeSort(int l, int r) {
        if (l>=r) return;
        
        int m = (l+r)/2;
        
        mergeSort(l, m);
        mergeSort(m+1, r);
        
        merge(l, m, r);
    }
    
    public static void merge(int l, int m, int r) {
        int[] merged = new int[r-l+1];
        int i = l, j = m+1, k = 0;
        
        while (i <= m && j <= r) merged[k++] = arr[i] <= arr[j] ? arr[j++] : arr[i++];
        while (i <= m) merged[k++] = arr[i++];
        while (j <= r) merged[k++] = arr[j++];
        
        System.arraycopy(merged, 0, arr, l, merged.length);
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
