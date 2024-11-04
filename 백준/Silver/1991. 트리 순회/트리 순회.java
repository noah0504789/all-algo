import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringBuilder sb;
    private static StringTokenizer st;
    
    private static Map<Character, Node> map;
    private static Node root, pn, ln, rn;
    private static char p, l, r;
    private static int n;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
                         
            p = st.nextToken().charAt(0);                                  
            l = st.nextToken().charAt(0);
            r = st.nextToken().charAt(0);
            
            pn = map.getOrDefault(p, new Node(p));
            ln = l != '.' ? map.getOrDefault(l, new Node(l)) : null;
            rn = r != '.' ? map.getOrDefault(r, new Node(r)) : null;
            
            pn.left = ln;
            pn.right = rn;
            
            map.putIfAbsent(p, pn);
            map.putIfAbsent(l, ln);
            map.putIfAbsent(r, rn);
            
            if (i == 0) root = pn;
        }
        
        preOrder(root);
        
        sb.append("\n");
        
        inOrder(root);
        
        sb.append("\n");
        
        postOrder(root);
                
        System.out.print(sb);
    }
    
    private static void preOrder(Node cur) {
        if (cur == null) return;
        
        sb.append(cur.val);
        
        preOrder(cur.left);
        preOrder(cur.right);
    }
    
    private static void inOrder(Node cur) {
        if (cur == null) return;
        
        inOrder(cur.left);
        
        sb.append(cur.val);        
        
        inOrder(cur.right);
    }

    private static void postOrder(Node cur) {
        if (cur == null) return;
        
        postOrder(cur.left);
        postOrder(cur.right);
        
        sb.append(cur.val);
    }
    
    static class Node {
        final char val;
        Node left, right;
        
        Node(char val) {
            this.val = val;
        }
    }
}