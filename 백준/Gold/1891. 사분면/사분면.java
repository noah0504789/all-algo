import java.io.*;
import java.util.*;

public class Main {
    
    private static int d;
    private static long x, y, sr, sc, dr, dc, size;
    private static BufferedReader br;
    private static String[] input;
    private static char[] arr;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().split(" ");
        
        d = Integer.parseInt(input[0]);
        arr = input[1].toCharArray();
        
        input = br.readLine().split(" ");        
        x = Long.parseLong(input[0]);
        y = Long.parseLong(input[1]);
        
        size = 1L<<d;
        find(0, 0L, 0L, size);
        
        dr = sr + y;
        dc = sc + x;        
        
        if (dr < 0 || dr >= size || dc < 0 || dc >= size) {
            System.out.print(-1);
            return;
        }
        
        System.out.print(solve(dr, dc, size));
    }
    
    private static void find(int step, long r, long c, long w) {
        if (step == d) {
            sr = r;
            sc = c;
            return;
        }
        
        int num = arr[step];
        long half = w>>1;
        if (num == '1') find(step+1, r+half, c+half, half);
        else if (num == '2') find(step+1, r+half, c, half);
        else if (num == '3') find(step+1, r, c, half);
        else if (num == '4') find(step+1, r, c+half, half);
    }     
    
    private static String solve(long r, long c, long w) {
        StringBuilder sb = new StringBuilder();
        long curW = w, cr = r, cc = c;
        for (int i = 0; i < d; i++) {
            long half = curW >> 1;
            boolean up = cr >= half, right = cc >= half;
            if (up && right) {
                sb.append('1');
                cr -= half;
                cc -= half;
            } else if (up) {
                sb.append('2');
                cr -= half;
            } else if (right) {
                sb.append('4');
                cc -= half;
            } else {
                sb.append('3');
            }
            curW = half;
        }
        
        return sb.toString();
    }
}