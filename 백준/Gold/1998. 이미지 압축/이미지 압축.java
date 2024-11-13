import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static Map<String, Integer> map;
    private static Node root;
    private static String input;
    private static byte[][] board;
    private static byte val;
    private static int n, m, size, nonCompressedCnt, compressedCnt;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        size = nextN(Math.max(n, m));

        board = new byte[size][size];

        for (int i = 0; i < n; i++) {
            input = br.readLine();
            for (int j = 0; j < m; j++) board[i][j] = (byte) (input.charAt(j) - '0');
        }

        nonCompressedCnt = compressedCnt = 0;
        map = new HashMap<>();

        quadTree(root = new Node(0, 0, size), 0, 0, size);
        compressedCnt = preOrderWithCache(root);

        bw.write(nonCompressedCnt + " " + compressedCnt);

        bw.flush();
    }

    private static int nextN(int n) {
        int size = 1;

        while (size < n) size *= 2;

        return size;
    }

    private static void quadTree(Node node, int r, int c, int size) {
        nonCompressedCnt++;

        if (isSame(r, c, size)) {
            node.compress(board[r][c]);
            return;
        }

        size /= 2;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int nr = r + i * size, nc = c + j * size;

                Node child = new Node(nr, nc, size);

                quadTree(child, nr, nc, size);

                node.addChild(child);
            }
        }
    }

    private static int preOrderWithCache(Node node) {
        if (!node.hasChildren()) return 1;

        String hash = node.hashStr();
        map.put(hash, map.getOrDefault(hash, 0) + 1);

        if (map.get(hash) > 1) return 0;

        int cnt = 1;

        for (Node child : node.children) cnt += preOrderWithCache(child);

        return cnt;
    }

    private static boolean isSame(int r, int c, int size) {
        val = board[r][c];

        for (int nr = r; nr < r + size; nr++) {
            for (int nc = c; nc < c + size; nc++) {
                if (board[nr][nc] != val) return false;
            }
        }

        return true;
    }

    static class Node {
        final int r, c, size;
        byte compressedCh;
        List<Node> children;

//        private Integer hashValue;
        private String hashValue;

        Node(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.compressedCh = -1;
        }

        public void compress(byte ch) {
            this.compressedCh = ch;
        }

        public void addChild(Node child) {
            if (children == null) children = new ArrayList<>();

            children.add(child);
        }

        public boolean hasChildren() {
            return children != null;
        }
        
        public String hashStr() {
            if (hashValue != null) return hashValue;

            if (!this.hasChildren()) return String.valueOf(this.compressedCh);

            StringBuilder sb = new StringBuilder();

            sb.append("(");

            for (Node child : children) sb.append(child.hashStr());

            sb.append(")");

            return hashValue = sb.toString();
        }
    }
}