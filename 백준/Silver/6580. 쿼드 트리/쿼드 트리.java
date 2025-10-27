import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static BufferedReader br;
    private static int w, h;
    private static char[][] arr; //
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        br = new BufferedReader(new InputStreamReader(System.in));
        w = Integer.parseInt(br.readLine().split(" ")[2]);
        h = Integer.parseInt(br.readLine().split(" ")[2]);
        arr = new char[w][h];
        br.readLine();
        
        for (int i = 0; i < h; i++) {            
            String[] input = br.readLine().split(",");   
            int c = 0;
            for (String hex : input) {
                if (hex.isEmpty()) continue;
                
                int val = Integer.decode(hex) & 0xFF;
                for (int bit = 0; bit < 8 && c < w; bit++) {
                    arr[i][c++] =  (((val >> bit) & 1) == 1) ? 'B' : 'W';
                }
            }
        }            
        
        br.readLine();
        
        sb.append(w).append("\n");
        solve(0, 0, w);

        System.out.print(sb);
    }
    
    private static void solve(int r, int c, int size) {
        if (size == 1 || isUniform(r, c, size)) {
            sb.append(arr[r][c]);
            return;
        }
        
        sb.append('Q');
        
        int half = size>>1;
        solve(r, c, half);
        solve(r, c+half, half);
        solve(r+half, c, half);
        solve(r+half, c+half, half);
    }
    
    private static boolean isUniform(int r, int c, int size) {
        char first = arr[r][c];
        for (int i = r; i < r+size; i++) {
            for (int j = c; j < c+size; j++) {
                if (first != arr[i][j]) return false;
            }
        }
        
        return true;
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