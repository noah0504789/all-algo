import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, r, c, width;
    private static char[][] arr;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        r = c = (n-1)*2;
        width = 2*r+1;
        arr = new char[width][width];        
        
        for (int i = 0; i < width; i++) Arrays.fill(arr[i], ' ');        
        
        draw(0, width-1);
        
        for (int i = 0; i < width; i++) sb.append(arr[i]).append("\n");

        System.out.print(sb);
    }
    
    static void draw(int s, int e) {
        if (s > e) return;
        
        for (int j = s; j <= e; j++) {arr[s][j] = '*'; arr[e][j] = '*';}
        for (int j = s; j <= e; j++) {arr[j][s] = '*'; arr[j][e] = '*';}
        
        draw(s+2, e-2);
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