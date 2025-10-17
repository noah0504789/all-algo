import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br;
    private static char[] arr;
    private static int n, m, cnt, idx, l, r, ans;
    private static int[] leftK, rightK;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        arr = br.readLine().toCharArray();
        n = arr.length;
        m = 0;
                
        for (char c : arr) if (c == 'R') m++;
        if (m == 0) {
            System.out.print(0);
            return;
        }
        
        leftK = new int[m];
        rightK = new int[m];
        
        cnt = 0;
        idx = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'K') cnt++;
            else leftK[idx++] = cnt;
        }
        
        cnt = 0;
        idx = m-1;
        for (int i = n-1; i >= 0; i--) {
            if (arr[i] == 'K') cnt++;
            else rightK[idx--] = cnt;
        }
        
        l = 0;
        r = m-1;
        ans = 0;
        while (l <= r) {
            int rCnt = r-l+1;
            int kCnt = Math.min(leftK[l], rightK[r]);
            ans = Math.max(ans, rCnt + 2 * kCnt);
            
            if (leftK[l] > rightK[r]) r--;
            else l++;
        }
        
        System.out.print(ans);
    }
}