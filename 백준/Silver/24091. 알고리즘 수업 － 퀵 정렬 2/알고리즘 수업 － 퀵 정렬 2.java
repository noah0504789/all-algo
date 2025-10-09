import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, k;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        k = readInt();
        arr = new int[n];
        
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        quickSort(0, n-1);
        
        System.out.print("-1");
    }
    
    private static void quickSort(int low, int high) {        
        if (low >= high) return;
        
        int pivot = partition(low, high);
        
        quickSort(low, pivot-1);
        quickSort(pivot+1, high);
    }
    
    private static int partition(int low, int high) {        
        int pivot = arr[high], i = low-1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) swap(++i, j);            
        }
        
        if (i+1 != high) swap(i+1, high);
        
        return i+1;
    }
    
    private static void swap(int src, int dest) {        
        int tmp = arr[src];
        arr[src] = arr[dest];
        arr[dest] = tmp;
        
        if (--k == 0) {
            for (int i = 0; i < n; i++) sb.append(arr[i]+" ");
            System.out.print(sb);
            System.exit(0);
        }
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