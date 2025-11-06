import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static BufferedReader br;
    private static int n;
    private static char[][] arr;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        for (int i = 0; i < n; i++) arr[i] = br.readLine().toCharArray();
                
        solve(n, 0, 0);
        
        System.out.print(sb);
    }
    
    private static void solve(int w, int r, int c) {
        if (isUniformed(w, r, c)) {
            sb.append(arr[r][c]);
            return;
        }
                
        sb.append("(");
        
        w >>= 1;
        
        solve(w, r, c);
        solve(w, r, c+w);
        solve(w, r+w, c);
        solve(w, r+w, c+w);
        
        sb.append(")");        
    }
    
    private static boolean isUniformed(int w, int r, int c) {
        char first = arr[r][c];
        
        for (int i = r; i < r+w; i++) {
            for (int j = c; j < c+w; j++) {
                if (first != arr[i][j]) return false;
            }    
        }
        
        return true;
    }
}