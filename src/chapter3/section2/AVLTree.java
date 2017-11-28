package chapter3.section2;

import java.util.Scanner;

/**
 * 平衡二叉树
 * 只实现了平衡二叉树基本的骨架
 * 实现了插入和删除操作
 */
public class AVLTree {
    private AVLNode root;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AVLTree tree = new AVLTree();
        while (sc.hasNext()) {
            tree.insert(sc.nextInt());
            tree.print();
        }
    }

    public void print(){
        print(root);
        System.out.println("Height:"+root.getHeight());
    }
    private void print(AVLNode root){
        if(null == root) return;
        print(root.left);
        System.out.printf(root.val+" ");
        print(root.right);
    }


    public void insert(int val) {
        this.root = insert(new AVLNode(val), root);
    }

    private AVLNode insert(AVLNode node, AVLNode root) {
        if (null == root) {
            root = node;
        } else if (node.val < root.val) {
            root.left = insert(node, root.left);
        } else if (node.val > root.val) {
            root.right = insert(node, root.right);
        }
        root.setHeight();
        return balanceTree(root);
    }

    private AVLNode balanceTree(AVLNode root) {
        if(null == root) return root;
        if(root.getHeight() != 0){
            if(getBalanceFactor(root) == 2){
                if(getBalanceFactor(root.left) == 1){
                    //LL
                    root = rightRotation(root);
                }else if(getBalanceFactor(root.left) == -1){
                    //LR
                    root.left = leftRotation(root.left);
                    root = rightRotation(root);
                }
            }else if(getBalanceFactor(root) == -2){
                if(getBalanceFactor(root.right) == -1){
                    //RR
                    root = leftRotation(root);
                }else if(getBalanceFactor(root.right) == 1){
                    //RL
                    root.right = rightRotation(root.right);
                    root = leftRotation(root);
                }
            }
        }
        return root;
    }

    //平衡因子
    private int getBalanceFactor(AVLNode node){
        if(null == node) return 0;
        int leftHeight = null == node.left? -1:node.left.getHeight();
        int rightHeight = null == node.right? -1 : node.right.getHeight();
        return leftHeight - rightHeight;
    }

    //将根节点左旋转
    private AVLNode leftRotation(AVLNode root) {
        AVLNode t= root.right;
        root.right = t.left;
        t.left = root;
        return t;
    }

    //将根节点右旋转
    private AVLNode rightRotation(AVLNode root) {
        AVLNode t = root.left;
        root.left = t.right;
        t.right = root;
        return t;
    }


}

class AVLNode {
    public AVLNode left;
    public AVLNode right;
    int val;
    //高度指示,通过这个参数来判断以这个节点为根的树是否为平衡二叉树
    private int height;

    public int getHeight() {
        return height;
    }


    public AVLNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        setHeight();
    }



    public void setHeight() {
        int leftHeight = null == this.left ? -1 : left.height;
        int rightHeight = null == this.right ? -1 : right.height;
        height = Math.max(leftHeight, rightHeight) + 1;
    }
}
