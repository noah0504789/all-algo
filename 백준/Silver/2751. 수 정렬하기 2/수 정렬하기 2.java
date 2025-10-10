import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, SIZE = 1_000_000, MAX = SIZE * 2;
    private static boolean[] arr;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        arr = new boolean[MAX+1];
        
        for (int i = 0; i < n; i++) arr[readInt()+SIZE] = true;        
        
        for (int i = 0; i <= MAX; i++) {
            if (arr[i]) sb.append(i-SIZE).append("\n");
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