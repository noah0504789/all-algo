import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static BitSet visited;
    private static String input;
    private static int n, m, ans;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        visited = new BitSet();

        while ((input = br.readLine()) != null) {
            st = new StringTokenizer(input);

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            ans = 0;

            for (int i = n; i <= m; i++) {
                if (!check(i)) continue;

                ans++;
            }

            bw.write(ans+"\n");
        }

        bw.flush();
    }

    private static boolean check(int num) {
        visited.clear();

        while (num != 0) {
            if (visited.get(num % 10)) return false;

            visited.set(num % 10);

            num /= 10;
        }

        return true;
    }
}
