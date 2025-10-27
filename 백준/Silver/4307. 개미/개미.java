import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, l, n, earliest, latest, p, toLeft, toRight;
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        while (tc-- > 0) {
            l = readInt();
            n = readInt();
            
            earliest = 0;
            latest = 0;
            for (int i = 0; i < n; i++) {
                p = readInt();
                toLeft = p;
                toRight = l-p;
                earliest = Math.max(earliest, Math.min(toLeft, toRight));
                latest = Math.max(latest, Math.max(toLeft, toRight));
            }
            
            sb.append(earliest + " " + latest).append("\n");
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