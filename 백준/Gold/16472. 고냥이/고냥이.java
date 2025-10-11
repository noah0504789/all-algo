import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br;
    private static int n, l, distinct, max;
    private static int[] cnt;
    private static char[] arr;    
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();
        
        l = 0;
        max = 0;
        cnt = new int[26];
        distinct = 0;
        
        int length = arr.length;
        
        for (int r = 0; r < length; r++) {
            int cr = arr[r] - 'a';
            if (cnt[cr] == 0) distinct++;
            cnt[cr]++;
            
            while (distinct > n) {
                int cl = arr[l] - 'a';
                cnt[cl]--;
                if (cnt[cl] == 0) distinct--;
                l++;
            }
            
            max = Math.max(max, r-l+1);
        }

        System.out.print(max);
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