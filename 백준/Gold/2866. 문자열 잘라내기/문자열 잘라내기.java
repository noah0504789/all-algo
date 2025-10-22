import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;
    private static BufferedReader br;
    private static int r, c, lo, hi, cnt;
    private static Set<String> set;
    private static char[][] arr;
    private static String[] input;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);
        
        arr = new char[r][c];
        for (int i = 0; i < r; i++) arr[i] = br.readLine().toCharArray();
        
        cnt = 0;
        lo = 0;
        hi = r-1;
        set = new HashSet<>();
        sb = new StringBuilder();
        while (lo <= hi) {
            int mid = (lo + hi)>>>1;
            if (ok(mid)) {
                cnt = mid;
                lo = mid+1;
            } else {
                hi = mid-1;
            }
        }
        
        System.out.print(cnt);
    }
    
    private static boolean ok(int t) {
        set.clear();
        for (int col = 0; col < c; col++) {
            sb.setLength(0);
            for (int row = t; row < r; row++) sb.append(arr[row][col]);
            
            if (!set.add(sb.toString())) return false;
        }
        
        return true;
    }
}