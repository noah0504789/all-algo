import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n;
    private static int[] arr, arr2;
    private static Set<Integer> set;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        n = readInt();
        arr = new int[n];
        
        set = new HashSet<>();
        for (int i = 0; i < n; i++) set.add(arr[i] = readInt());
        
        arr2 = new int[set.size()];
        int i = 0;
        for (int num : set) arr2[i++] = num;
                
        Arrays.sort(arr2);
        
        for (int target : arr) sb.append(lowerBound(target)).append(" ");
        
        System.out.print(sb);
    }
    
    private static int lowerBound(int target) {
        int l = 0, r = arr2.length;
        while (l < r) {
            int m = (l+r)>>>1;
            if (arr2[m] >= target) r = m;
            else l = m+1;
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