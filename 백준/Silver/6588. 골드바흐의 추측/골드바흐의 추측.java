import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;
    private static boolean[] noPrime;    
    private static int n;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
                
        noPrime = new boolean[1_000_000+1];
        noPrime[0] = noPrime[1] = true;

        for (int i = 2; i <= Math.sqrt(1_000_000); i++) {
            if (noPrime[i]) continue;

            for (int j = i*i; j <= 1_000_000; j += i) noPrime[j] = true;
        }
        
        while ((n = readInt()) > 0) {            
            int l = 1, r = n - l;
            boolean isExist = false;

            for (int i = 2; i <= n / 2; i++) {
                l++;
                r--;
                
                if (!noPrime[l] && !noPrime[r]) {
                    isExist = true;
                    break;
                }
            }
            
            if (isExist) sb.append(n).append(" = ").append(l).append(" + ").append(r).append("\n");
            else sb.append("Goldbach's conjecture is wrong.").append("\n");
        }
        
        System.out.print(sb);
    }
    
    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        
        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }
        
        return r;
    }
}