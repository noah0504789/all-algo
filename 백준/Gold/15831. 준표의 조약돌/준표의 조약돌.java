import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br;
    private static int n, b, w, l, r, bc, wc, max;
    private static String[] input;
    private static char[] arr;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        b = Integer.parseInt(input[1]);
        w = Integer.parseInt(input[2]);
        
        arr = br.readLine().toCharArray();
        
        bc = wc = 0;
        l = r = 0;
        max = 0;
        
        while (r < n) {
            if (arr[r] == 'B') bc++;
            else wc++;
            r++;
            
            while (bc > b) {
                if (arr[l] == 'B') bc--;
                else wc--;
                l++;
            }            
            
            if (wc >= w) max = Math.max(max, r-l);            
        }

        System.out.print(max);
    }
}