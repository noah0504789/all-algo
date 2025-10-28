import java.io.*;
import java.util.*;

public class Main {
    
    private static int l, w, h, n;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        l = readInt();
        w = readInt();
        h = readInt();
        
        n = readInt();
        arr = new int[20];
        for (int i = 0; i < n; i++) arr[readInt()] = readInt();
        
        long used = 0, filled = 0;
        for (int i = 19; i >= 0; i--) {
            long lx = l >> i;
            long wx = w >> i;
            long hx = h >> i;
            long capacity = lx * wx * hx;
            if (lx == 0 || wx == 0 || hx == 0) {
                filled *= 8;
                continue;
            }
            
            long canPlace = capacity - filled;
            if (canPlace < 0) canPlace = 0;
            
            long use = Math.min(canPlace, arr[i]);
            used+=use;
            filled+=use;
            
            if (i > 0) filled *= 8;
        }
        
        long volume = 1L * l * w * h;
        if (filled == volume) System.out.println(used);
        else System.out.println(-1);
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