import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static List<Ant> list;
    private static Ant cur, next;
    private static char[] a, b;
    private static int t;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        br.readLine();

        a = br.readLine().toCharArray();
        b = br.readLine().toCharArray();

        t = Integer.parseInt(br.readLine());

        list = new ArrayList<>();

        for (int i = a.length-1; i >= 0; i--) list.add(new Ant(a[i], 1));
        for (int i = 0; i < b.length; i++) list.add(new Ant(b[i], 2));

        while (t-- > 0) {
            for (int i = 0; i < list.size()-1; i++) {
                cur = list.get(i);
                next = list.get(i+1);

                if (cur.type == 2) continue;
                if (cur.type == next.type) continue;

                list.set(i, next);
                list.set(i+1, cur);
                i++;
            }
        }

        for (Ant ant : list) bw.write(ant.ch);

        bw.flush();
    }

    static class Ant {
        char ch;
        int type;

        public Ant(char ch, int type) {
            this.ch = ch;
            this.type = type;
        }
    }
}