import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br;
    private static String[] input, arr;
    private static String prefix;
    private static int n, m, cnt, l, r, mid;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        
        arr = new String[n];
        for (int i = 0; i < n; i++) arr[i] = br.readLine();
        
        Arrays.sort(arr);
        
        cnt = 0;
        while (m-- > 0) {
            prefix = br.readLine();
            int idx = lowerBound(prefix);
            if (idx < n && arr[idx].startsWith(prefix)) cnt++;
        }

        System.out.print(cnt);
    }
    
    private static int lowerBound(String target) {
        int l = 0, r = n;
        while (l < r) {
            int m = (l+r)>>>1;
            if (arr[m].compareTo(target) >= 0) r = m;
            else l = m+1;
        }
        return l;
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