import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        
        List<Integer> a = new ArrayList<>();
        a.add(1);
        while (a.size() < n) {
            List<Integer> b = new ArrayList<>();
            for (int v : a) {
                int x = v*2-1;
                if (x <= n) b.add(x);
            }
            
            for (int v : a) {
                int x = v*2;
                if (x <= n) b.add(x);
            }
            
            a = b;
        }
        
        for (int v : a) sb.append(v+" ");

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