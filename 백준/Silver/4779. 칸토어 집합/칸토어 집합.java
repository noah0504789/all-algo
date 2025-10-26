import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();        
        
        while (true) {
            n = readInt();
            if (n == -1) break;
            
            sb.append(paint(n, pow(3, n))).append("\n");
        }

        System.out.print(sb);
    }
    
    private static String paint(int n, int length) {
        if (n == 0) return "-".repeat(length);
                
        String line = paint(n-1, length/3);
        String blank = blank(length/3);
        
        StringBuilder sb2 = new StringBuilder();
        sb2.append(line).append(blank).append(line);
        
        return sb2.toString();
    }
    
    private static String blank(int length) {
        return " ".repeat(length);
    }
    
    private static int pow(int a, int b) {
        int res = 1;
        while (b > 0) {
            if ((b&1)==1) res *= a;
            a *= a;
            b >>= 1;
        }
        return res;
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       
        
        while (c != -1 && c <= ' ') c = System.in.read();
        if (c == -1) return -1;
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