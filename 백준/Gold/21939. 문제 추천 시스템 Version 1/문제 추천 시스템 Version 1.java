import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringTokenizer st;
    private static StringBuilder sb;

    private static TreeSet<Problem> minTree;
    private static Map<Integer, Integer> map;
    private static Problem problem;
    private static String command;
    private static int n, p, l, m, ord;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        minTree = new TreeSet<>();
        map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            p = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            map.put(p, l);

            problem = new Problem(p, l);

            minTree.add(problem);
        }

        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            command = st.nextToken();
            if (command.equals("add")) {
                p = Integer.parseInt(st.nextToken());
                l = Integer.parseInt(st.nextToken());

                map.put(p, l);

                problem = new Problem(p, l);

                minTree.add(problem);
            } else if (command.equals("solved")) {
                p = Integer.parseInt(st.nextToken());

                problem = new Problem(p, map.get(p));

                map.remove(p);

                minTree.remove(problem);
            } else {
                ord = Integer.parseInt(st.nextToken());

                sb.append(ord == 1 ? minTree.last().p : minTree.first().p).append("\n");
            }
        }

        System.out.print(sb);
    }

    static class Problem implements Comparable<Problem> {
        final int p, l;

        Problem(int p, int l) {
            this.p = p;
            this.l = l;
        }

        public int compareTo(Problem o) {
            if (this.l == o.l) return this.p - o.p;

            return this.l - o.l;
        }

        public boolean equalsTo(Object o) {
            Problem other = (Problem) o;

            return other.p == this.p;
        }
    }
}