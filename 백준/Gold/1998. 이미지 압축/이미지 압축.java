import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br;
    private static int n, m, w, cnt, a, b;
    private static String[] input;
    private static int[][] arr;
    private static Map<String, Integer> map;
    private static Node root;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        
        w = Math.max(n,m);
        if (!checkPow2(w)) w = Integer.highestOneBit(w)<<1;
        
        arr = new int[w][w];
        for (int r = 0; r < n; r++) {
            char[] cArr = br.readLine().toCharArray();
            for (int c = 0; c < m; c++) arr[r][c] = cArr[c] - '0';        
        }
        
        map = new HashMap<>();
        
        root = new Node(0, 0, w);
        
        System.out.print(compress(root, w, 0, 0) + " " + preOrderWithCache(root));
    }
    
    private static int preOrderWithCache(Node node) {
        if (!node.hasChildren()) return 1;
        String hash = node.hashStr();
        map.put(hash, map.getOrDefault(hash, 0)+1);
        if(map.get(hash)>1) return 0;
        
        int cnt = 1;
        for (Node child : node.children) cnt += preOrderWithCache(child);
        
        return cnt;
    }
    
    private static int compress(Node node, int w, int r, int c) {
        if (isUniformed(w, r, c)) {
            node.compress(arr[r][c]);
            return 1;
        }
        
        w >>= 1;
        
        int cnt = 1;
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int nr = r + i*w, nc = c + j*w;
                Node child = new Node(nr, nc, w);
                cnt += compress(child, w, nr, nc);
                node.addChild(child);
            }
        }
        
        return cnt;
    }
    
    private static boolean isUniformed(int w, int r, int c) {
        for (int i = r; i < r+w; i++) {
            for (int j = c; j < c+w; j++) {
                if (arr[r][c] != arr[i][j]) return false;
            }
        }
        
        return true;
    }
    
    private static boolean checkPow2(int w) {
        for (int i = 0; i <= 8; i++) {
            if (w == 1<<i) return true;            
        }
        
        return false;
    }
    
    static class Node {
        int r, c, w;
        int compressed;
        String hashValue;
        List<Node> children;
        
        Node(int r, int c, int w) {
            this.r=r;
            this.c=c;
            this.w=w;
            this.compressed=-1;
        }
        
        public void compress(int compressed) {
            this.compressed=compressed;
        }
        
        public boolean hasChildren() {
            return children!=null;
        }
        
        public void addChild(Node child) {
            if (children == null) children = new ArrayList<>();
            children.add(child);
        }
        
        public String hashStr() {
            if (hashValue != null) return hashValue;
            
            if (children == null) return this.compressed+"";
            
            StringBuilder sb = new StringBuilder();
            
            sb.append("(");
            
            for (Node child: children) sb.append(child.hashStr());
            
            sb.append(")");
            
            return hashValue = sb.toString();
        }
    }
}