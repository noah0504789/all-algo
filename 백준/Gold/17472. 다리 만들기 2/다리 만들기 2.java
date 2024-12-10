import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1,0}};
    private static DisjointSet disjointSet;
    private static PriorityQueue<Edge> pq;
    private static List<List<int[]>> borderList;
    private static List<int[]> borders;
    private static Queue<int[]> queue;
    private static BitSet visited;

    private static int[][] board, islands;
    private static int n, m, label;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) board[i][j] = readInt();
        }

        islands = new int[n][m];
        queue = new ArrayDeque<>();
        visited = new BitSet();

        label = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) continue;
                if (visited.get(i*m+j)) continue;

                bfs(i, j);

                label++;
            }
        }

        borderList = new ArrayList<>();
        for (int i = 0; i <= label-1; i++) borderList.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (islands[i][j] == 0) continue;

                borders = borderList.get(islands[i][j]);

                for (int[] dir : DIRS) {
                    int nr = i + dir[0], nc = j + dir[1];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    if (board[nr][nc] != 0) continue;

                    borders.add(new int[]{i, j});
                    break;
                }
            }
        }

        // pq에 edge 넣기
        pq = new PriorityQueue<>();

        for (int i = 1; i <= label-1; i++) {
            for (int[] point : borderList.get(i)) {
                int cr = point[0], cc = point[1], ci = islands[cr][cc];
                // 섬 존재 체크
                for (int[] dir : DIRS) {
                    int nr = cr, nc = cc;

                    while (true) {
                        nr += dir[0];
                        nc += dir[1];

                        if (nr < 0 || nr >= n || nc < 0 || nc >= m) break;
                        if (board[nr][nc] == 0) continue;
                        if (ci == islands[nr][nc]) break;

                        int dist = getDist(cr, cc, nr, nc)-1;
                        if (dist < 2) break;

                        Edge newEdge = new Edge(cr *m+ cc, nr*m+nc, dist);
                        if (!pq.contains(newEdge)) pq.offer(newEdge);

                        break;
                    }
                }
            }
        }

        disjointSet = new DisjointSet(borderList.size()-1);

        int eCnt = 0, sum = 0;
        while (!pq.isEmpty()) {
            Edge curE = pq.poll();
            int x = islands[curE.src/m][curE.src%m], y = islands[curE.dest/m][curE.dest%m];

            if (!disjointSet.union(x, y)) continue;

            sum += curE.dist;

            if (disjointSet.isConnected()) break;
//            if (++eCnt == borderList.size()-1) break;
        }

        bw.write(disjointSet.isConnected() ? sum+"" : "-1");
        bw.flush();
    }

    private static void bfs(int r, int c) {
        queue.offer(new int[]{r, c});
        visited.set(r*m+c);

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int cr = point[0], cc = point[1];
            islands[cr][cc] = label;

            for (int[] dir : DIRS) {
                int nr = cr + dir[0], nc = cc + dir[1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (board[nr][nc] == 0) continue;
                if (visited.get(nr*m+nc)) continue;

                visited.set(nr*m+nc);
                queue.offer(new int[]{nr, nc});
            }
        }
    }

    private static int getDist(int r2, int c2, int r1, int c1) {
        return Math.abs(r2-r1) + Math.abs(c2-c1);
    }

    static class DisjointSet {
        int[] rank, root;
        int connectedCnt;

        public DisjointSet(int size) {
            this.rank = new int[size+1];
            this.root = new int[size+1];
            this.connectedCnt = size;
            
            makeSet();
        }

        private void makeSet() {
            for (int i = 1; i <= rank.length-1; i++) {
                rank[i] = 1;
                root[i] = i;
            }
        }

        public int find(int x) {
            if (x == root[x]) return x;

            return root[x] = find(root[x]);
        }

        public boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);

            if (rootX == rootY) return false;

            if (rank[rootX] < rank[rootY]) root[rootX] = root[rootY];
            else root[rootY] = root[rootX];

            if (rank[rootX] == rank[rootY]) rank[rootX]++;

            connectedCnt--;
            return true;
        }

        public boolean isConnected() {
//            int root = find(1);
//
//            for (int i = 2; i < rank.length-1; i++) {
//                if (root != find(i)) return false;
//            }
//
//            return true;
            
            return connectedCnt == 1;
        }
    }

    static class Edge implements Comparable<Edge> {
        final int src, dest, dist;

        public Edge(int src, int dest, int dist) {
            this.src = src;
            this.dest = dest;
            this.dist = dist;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }

        @Override
        public boolean equals(Object o) {
            Edge other = (Edge) o;

            return (this.src == other.src && this.dest == other.dest) || (this.src == other.dest && this.dest == other.src);
        }
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