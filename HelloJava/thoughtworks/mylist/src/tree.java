import java.util.Scanner;

class TreeNode{
    int val;
    TreeNode left=null;
    TreeNode right=null;
    public TreeNode(int val) {
        this.val=val;
    }
}
public class tree {


    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);

        TreeNode root1=creatTree(in);
        System.out.println("前序");
        preorder(root1);
        System.out.println();
        System.out.println("中序");
        inorder(root1);
        System.out.println();
        System.out.println("后序");
        postorder(root1);
        System.out.println();


    }
    private static void preorder(TreeNode root) {
        if (root!=null) {
            System.out.print(root.val+"  ");
            preorder(root.left);
            preorder(root.right);

        }
    }
    private static void inorder(TreeNode root) {
        if (root!=null) {
            inorder(root.left);
            System.out.print(root.val+"  ");
            inorder(root.right);

        }
    }
    private static void postorder(TreeNode root) {
        if (root!=null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.val+"  ");
        }
    }
    private static TreeNode creatTree(Scanner in) {
        int val=in.nextInt();
        if (val==-1) {     //如果输入-1，则表示无该节点,则让上一个节点的该子节点为null
            return null;
        }
        TreeNode newNode=new TreeNode(val);

        newNode.left=creatTree(in);
        newNode.right=creatTree(in);

        return newNode;

    }

}