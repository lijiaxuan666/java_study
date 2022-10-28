import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
public class day12 {
    Map<Integer,Node> map;
    public void order(Node root){
        if(root==null)
            return;
        map.put(root.val,root);
        order(root.left);
        order(root.right);
    }
    public Node treeToDoublyList(Node root) {
        map=new TreeMap<>();
        order(root);
        Node head=null;
        Node last=head;

        for(Map.Entry<Integer,Node> entry:map.entrySet()){
            if(head==null){
                head= entry.getValue();
                last=head;
            }else{
                last.right=entry.getValue();
                entry.getValue().left=last;
                last=entry.getValue();
            }
        }
        head.left=last;
        last.right=head;
        return head;
    }

    public static void main(String[] args) {
        Node root=new Node(4);
        Node root1=new Node(2);
        Node root2=new Node(5);
        Node root3=new Node(1);
        Node root4=new Node(3);
        root.left=root1;
        root.right=root2;
        root1.left=root3;
        root1.right=root4;
        root2.left=null;
        root2.right=null;
        root3.left=null;
        root3.right=null;
        root4.left=null;
        root4.right=null;
        day12 dy12=new day12();
        Node head=dy12.treeToDoublyList(root);
        while(head!=null){
            System.out.println(head.val);
            head=head.right;
        }
    }
}
