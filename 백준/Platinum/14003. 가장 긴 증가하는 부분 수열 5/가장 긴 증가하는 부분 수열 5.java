import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static int n, len, lisEnd, p;
    private static int[] arr, lisIdx, path, res;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        lisIdx = new int[n];
        path = new int[n];
        Arrays.fill(path, -1);
        
        for (int i = 0; i < n; i++) {
            int x = arr[i];
            int pos = lowerBound(len, x);
            lisIdx[pos] = i;
            if (pos > 0) path[i] = lisIdx[pos-1];
            
            if (pos == len) {
                len++;
                lisEnd = i;
            }
        }
        
        p = len-1;
        res = new int[len];
        while (lisEnd != -1) {
            res[p--] = arr[lisEnd];
            lisEnd = path[lisEnd];
        }
        
        sb.append(len+"\n");
        for (int a : res) sb.append(a+" ");        

        System.out.print(sb);
    }
    
    private static int lowerBound(int r, int x) {
        int l = 0;
        while (l < r) {
            int m = (l+r)>>>1;
            if (arr[lisIdx[m]] >= x) r = m;
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