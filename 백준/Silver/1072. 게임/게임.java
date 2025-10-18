import java.io.*;
import java.util.*;

public class Main {
    
    private static int x, y, z;
    
    public static void main(String... args) throws IOException {
        x = readInt();
        y = readInt();
        z = (int)((long)y*100/x);
        
        if (z >= 99) {
            System.out.print(-1);
            return;
        }
        
        System.out.print(lowerBound(z));        
    }
    
    private static int lowerBound(int target) {
        int l = 1, r = 1_000_000_000;
        while (l < r) {
            int m = l+((r-l)>>>1);
            int z2 = (int)(((long)(y+m)*100)/(x+m));
            if (z2 > target) r = m;
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