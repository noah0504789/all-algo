import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static char[] input;

    private static int tc, size;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            input = br.readLine().toCharArray();
            size = input.length;

            bw.write(check(0, size-1) ? "YES\n" : "NO\n");
        }

        bw.flush();
    }

    private static boolean check(int l, int r) {
        if (l == r) return true;

        int mid = (l+r)/2;

        for (int i = l; i < mid; i++) {
            if (input[i] == input[r-i]) return false;
        }

        return check(l, mid-1) && check(mid+1, r);
    }
}
