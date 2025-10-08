import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n;
    private static int[] arr, pos, move;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        arr = new int[n = readInt()];
        pos = new int[n+1];
        move = new int[n+1];
                
        for (int i = 0; i < n; i++) pos[arr[i] = readInt()] = i;
        
        for (int i = 0; i < n; i++) {
            int j = pos[i+1];
            if (i==j) continue;
            int v = arr[i];
            int dist = j-i;
            
            move[i+1] += dist;
            move[v] += dist;
            
            arr[i] = i+1; pos[i+1] = i;
            arr[j] = v; pos[v] = j;
        }
        
        for (int i = 1; i <= n; i++) sb.append(move[i]+"").append(" ");

        System.out.print(sb);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;
                
        while (c == ' ') c = System.in.read();
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