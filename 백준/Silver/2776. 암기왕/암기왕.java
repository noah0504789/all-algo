import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, n, m;
    private static int[] arr;
    private static boolean flag;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        while (tc-- > 0) {
            n = readInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = readInt();
            
            Arrays.sort(arr);
            
            m = readInt();            
            for (int i = 0; i < m; i++) {
                int target = readInt();
                int l = 0, r = arr.length-1;   
                flag = false;
                
                while (l <= r) {
                    int m = l+((r-l)>>>1);
                    if (arr[m] == target) {flag = true; break;}
                    else if (arr[m] > target) r = m-1;
                    else l = m+1;
                }
                sb.append(flag ? "1" : "0").append("\n");
            }            
        }

        System.out.print(sb);
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