import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[] a, b, s;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        a = new int[n];
        b = new int[n];
        
        for (int i = 0; i < n; i++) a[i] = readInt();
        for (int i = 0; i < n; i++) b[i] = readInt();
        
        if (Arrays.equals(a, b)) {
            System.out.print("1");
            return;
        }
        
        int step = -1;
        for (int j = n-1; j >= 0; j--) {
            if (a[j] == b[j]) continue;
            
            step = j;
            break;
        }
        
        s = new int[step];
        for (int i = 0; i < step; i++) s[i] = a[i];        
        Arrays.sort(s);        
        int x = a[step];
        
        int lb = 0;
        while (lb < step && s[lb] < x) lb++;
                       
        if (equalsIntermediatePrefix(s, lb, b) || equalsFinalPrefix(s, x, lb, b, step))  {
            System.out.print("1");
            return;
        }
       
        System.out.print("0");
    }
    
    private static boolean equalsIntermediatePrefix(int[] S, int lb, int[] B) {
        int n = S.length;
        int d = -1;
        for (int j=0;j<n;j++) { if (B[j]!=S[j]) { d=j; break; } }

        if (d==-1) return B[n] == S[n-1];
        
        int k = d-1;
        if (k < lb || k < 0) return false;        
        for (int j=k; j<n; j++) {
            if (B[j+1] != S[j]) return false;
        }
            
        return true;
    }
    
    private static boolean equalsFinalPrefix(int[] S, int x, int lb, int[] B, int i) {
        int p=0, q=0, n=S.length;
        while (p<lb) if (B[q++]!=S[p++]) return false;
        if (B[q++]!=x) return false;
        while (p<n)    if (B[q++]!=S[p++]) return false;
        return q==i+1; // prefix 길이 일치 확인
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
