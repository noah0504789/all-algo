import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static List<Integer> left, right;
    private static int[] weights, dp;
    private static int n, c, ans, idx, cnt;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        c = readInt();

        weights = new int[n];
        for (int i = 0; i < n; i++) weights[i] = readInt();

        left = new ArrayList<>();
        right = new ArrayList<>();
        
        comb(left, 0, n/2, 0);
        comb(right, n/2, n, 0);
        right.sort((a, b) -> (a-b));
        
        idx = 0;
        cnt = 0;
        
        for (int i = 0; i < left.size(); i++) {
            idx = upperbound(0, right.size()-1, left.get(i));
            cnt += idx+1;
        }

        bw.write(cnt+"");
        bw.flush();
    }
    
    private static int upperbound(int l, int r, int val) {
        while (l <= r) {
            int mid = (l+r)/2;
            
            if (right.get(mid) <= c - val) l = mid + 1;
            else r = mid - 1;
        }
        
        return r;
    }
    
    private static void comb(List list, int start, int high, int sum) {
        if (sum > c) return;
        if (start == high) {
            list.add(sum);
            return;
        }
        
        comb(list, start+1, high, sum);
        comb(list, start+1, high, sum+weights[start]);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}
