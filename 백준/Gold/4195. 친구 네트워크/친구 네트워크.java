import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static Map<String, Integer> map;
    private static DisjointSet disjointSet;
    private static String src, dest;
    private static int tc, f, serialNum;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new HashMap<>();

        tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            f = Integer.parseInt(br.readLine());

            map.clear();

            disjointSet = new DisjointSet(2*f+1);

            serialNum = 2;

            for (int i = 0; i < f; i++) {
                st = new StringTokenizer(br.readLine());

                src = st.nextToken();
                dest = st.nextToken();

                if (!map.containsKey(src)) map.put(src, serialNum++);
                if (!map.containsKey(dest)) map.put(dest, serialNum++);

                bw.write(disjointSet.union(map.get(src), map.get(dest))+"\n");
            }
        }

        bw.flush();
    }

    private static class DisjointSet {
        int[] root, rank, size;

        public DisjointSet(int size) {
            this.root = new int[size+1];
            this.rank = new int[size+1];
            this.size = new int[size+1];

            makeSet();
        }

        private void makeSet() {
            for (int i = 0; i < root.length; i++) {
                root[i] = i;
                rank[i] = 1;
                size[i] = 1;
            }
        }

        private int find(int x) {
            if (x == root[x]) return x;

            return root[x] = find(root[x]);
        }

        public int union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return size[rootX];

            if (rank[rootX] > rank[rootY]) {
                root[rootY] = root[rootX];
                size[rootX] += size[rootY];
                return size[rootX];
            }

            root[rootX] = root[rootY];
            if (rank[rootX] == rank[rootY]) rank[rootY]++;

            size[rootY] += size[rootX];
            return size[rootY];
        }
    }
}
