import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, w, h;
    private static char[][] arr;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        n = readInt();
        
        h = (1<<n)-1;
        w = h*2-1;
        arr = new char[h][w];
        
        for (int i = 0; i < h; i++) Arrays.fill(arr[i], ' ');
        
        int center = w>>1;
        if ((n & 1) == 1) drawUp(0, center, h, n);
        else drawDown(0, center, h, n);
        
        for (int i = 0; i < h; i++) {
            int last = -1;
            for (int j = w-1; j >= 0; j--) {
                if (arr[i][j] == '*') {
                    last = j; 
                    break;
                }
            }
            if (last != -1) sb.append(arr[i], 0, last+1);
            sb.append("\n");
        }

        System.out.print(sb);
    }
    
    private static void drawUp(int r, int c, int h, int n) {
        if (h == 1) {
            arr[r][c] = '*';
            return;
        }
        
        for (int i = 0; i < h; i++) arr[r+i][c-i] = arr[r+i][c+i] = '*';
            
        int baseRow = r+h-1;
        for (int x = c - (h-1); x <= c + (h-1); x++) arr[baseRow][x] = '*';
        
        if (n > 1) drawDown(r+(h-1)/2, c, (h-1)/2, n-1);
    }
    
    private static void drawDown(int r, int c, int h, int n) {
        if (h == 1) {
            arr[r][c] = '*';
            return;
        }
        
        for (int x = c - (h-1); x <= c + (h-1); x++) arr[r][x] = '*';
        
        for (int i = 0; i < h; i++) arr[r+i][c-(h-1-i)] = arr[r+i][c+(h-1-i)] = '*';
                    
        if (n > 1) drawUp(r+1, c, (h-1)/2, n-1);
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