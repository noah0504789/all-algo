import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringBuilder sb;

    private static String s, t;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        sb = new StringBuilder();

        s = br.readLine();
        t = br.readLine();

        dfs(t);

        System.out.print(0);
    }

    private static void dfs(String cur) {
        if (cur.length() == s.length()) {
            if (cur.equals(s)) {
                System.out.print(1);
                System.exit(0);
            }

            return;
        }

        if (cur.charAt(cur.length()-1) == 'A') {
            sb.setLength(0);
            sb.append(cur).setLength(cur.length()-1);
            dfs(sb.toString());
        }

        if (cur.charAt(0) == 'B') {
            sb.setLength(0);
            sb.append(cur).reverse().setLength(cur.length()-1);
            dfs(sb.toString());
        }
    }
}