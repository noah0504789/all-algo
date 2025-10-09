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
        
        for (int i = n-1; i > 0; i--) {
            boolean swapped = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] <= arr[j+1]) continue;
                if (k == 0) break;
                
                swap(j, j+1);
                k--;
                swapped = true;
            }
            
            if (!swapped) break;
        }
        
        if (k > 0) {
            System.out.print("-1");            
            return;
        }
        
        for (int i = 0; i < n; i++) sb.append(arr[i] + " ");

        System.out.print(sb);
    }
    
    private static void swap(int src, int dest) {
        int tmp = arr[src];
        arr[src] = arr[dest];
        arr[dest] = tmp;
    }

    private static int readInt() throws IOException {
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