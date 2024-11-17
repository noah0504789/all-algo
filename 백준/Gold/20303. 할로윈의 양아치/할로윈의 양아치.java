import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;
    private static Queue<Integer> queue;
    private static Node[] friends; // Adjacency list for friend connections
    private static BitSet friendV, allV; // BitSets for visited nodes
    private static int[] candies, dp; // candies array and dp array
    private static int n, m, k, src, dest, cur, ans; // Various variables

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // Reading input
        n = readInt(); // Number of children
        m = readInt(); // Number of friendships
        k = readInt(); // Minimum number of crying children to alert adults

        candies = new int[n+1]; // Number of candies for each child
        for (int i = 1; i <= n; i++) {
            candies[i] = readInt();
        }

        friends = new Node[n+1]; // Initializing adjacency list
        for (int i = 1; i <= m; i++) {
            src = readInt(); // First child in friendship
            dest = readInt(); // Second child in friendship
            
            // Adding edges (undirected)
            friends[src] = new Node(dest, friends[src]);
            friends[dest] = new Node(src, friends[dest]);
        }

        queue = new ArrayDeque<>(); // Queue for BFS
        friendV = new BitSet(); // BitSet for visited nodes during BFS
        allV = new BitSet(); // BitSet for marking all visited nodes
        dp = new int[k]; // DP array for the knapsack approach

        // Finding connected components
        for (int i = 1; i <= n; i++) {
            if (!allV.get(i)) {
                int totalCandies = 0;
                int numChildren = 0;
                
                // BFS to traverse connected component
                queue.add(i);
                friendV.clear();
                friendV.set(i);
                allV.set(i);
                
                while (!queue.isEmpty()) {
                    cur = queue.poll();
                    numChildren++;
                    totalCandies += candies[cur];
                    
                    for (Node node = friends[cur]; node != null; node = node.next) {
                        if (!friendV.get(node.val)) {
                            queue.add(node.val);
                            friendV.set(node.val);
                            allV.set(node.val);
                        }
                    }
                }

                // Knapsack DP step
                for (int j = k - 1; j >= numChildren; j--) {
                    dp[j] = Math.max(dp[j], dp[j - numChildren] + totalCandies);
                }
            }
        }

        // The maximum candies we can collect without alerting adults
        ans = 0;
        for (int i = 0; i < k; i++) {
            ans = Math.max(ans, dp[i]);
        }

        bw.write(ans + "\n");
        bw.flush();
    }

    // Method to read integers efficiently
    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r = r * 10 + (c - '0');
            c = System.in.read();
        }
        return r;
    }

    // Node class for adjacency list representation
    private static class Node {
        int val;
        Node next;

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
