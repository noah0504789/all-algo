import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;    
    private static StringBuilder sb;
    
    private static Node root, cur;
    private static String input;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();       
                
        root = new Node(Integer.parseInt(br.readLine()));
                    
        while ((input = br.readLine()) != null) {
            cur = new Node(Integer.parseInt(input));
            
            root.addNode(cur);
        }
        
        postOrder(root);
        
        System.out.print(sb);
    }
    
    private static void postOrder(Node node) {
        if (node == null) return;
        
        postOrder(node.left);
        postOrder(node.right);
        
        sb.append(node.num).append("\n");
    }
    
    static class Node {
        final int num;
        Node left, right;
        
        Node(int num) {
            this.num = num;
        }
        
        public void addNode(Node newNode) {
            if (newNode.num < this.num) {
                if (this.left == null) this.left = newNode;
                else this.left.addNode(newNode);
            } else {
                if (this.right == null) this.right = newNode;
                else this.right.addNode(newNode);
            }
        }
    }    
}