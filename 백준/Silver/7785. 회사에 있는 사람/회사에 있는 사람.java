import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {        
    private static BufferedReader br;    
    private static StringTokenizer st;
    private static StringBuilder sb;
    
    private static MyHashTable hashtable;
    private static String name, activity;
    private static int n;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        
        hashtable = new MyHashTable(n);
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            
            name = st.nextToken();
            activity = st.nextToken();
            
            if (activity.equals("enter")) hashtable.put(name, true);
            else hashtable.remove(name);
        }
        
        List<String> list = hashtable.keySet().stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        for (String key : list) sb.append(key).append("\n");
        
        System.out.print(sb);
    }
    
    static class MyHashTable {
        class Node {
            final String key;
            boolean value;
            Node next;

            Node(String key, boolean value) {
                this.key = key;
                this.value = value;
            }
        }

        final int size;
        Node[] arr;        

        MyHashTable(int size) {
            this.arr = new Node[size];
            this.size = size;
        }

        public void put(String key, boolean value) {
            int idx = hash(key) % size;
            Node cur = arr[idx], newNode = new Node(key, true);
            
            if (cur == null) {
                arr[idx] = newNode;
                return;
            } 
            
            Node prev = null;
            while (cur != null) {
                if (cur.key.equals(key)) {
                    cur.value = value;
                    return;
                }
                
                prev = cur;
                cur = cur.next;
            }
                
            prev.next = newNode;
        }

        public boolean get(String key) {
            int idx = hash(key) % size;
            Node cur = arr[idx];
            
            if (cur == null) return false;
            
            while (cur != null) {
                if (cur.key.equals(key)) return true;
                
                cur = cur.next;
            }
                
            return false;
        }

        public void remove(String key) {
            int idx = hash(key) % size;
            Node cur = arr[idx], prev = null;
            
            if (cur == null) return;
            
            while (cur != null) {
                if (cur.key.equals(key)) {
                    if (prev == null) arr[idx] = cur.next;
                    else prev.next = cur.next;
                    
                    return;
                }
                
                prev = cur;
                cur = cur.next;                    
            }
        }
        
        public List<String> keySet() {
            List<String> list = new ArrayList<>();
            
            for (Node node : arr) {
                while (node != null) {
                    list.add(node.key);
                    node = node.next;
                }
            }
            
            return list;
        }

        private int hash(String key) {
            return Objects.hashCode(key);
        }
    }
}