package chapter3.section2;

import chapter3.ST;

import java.util.Scanner;

public class BST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
    private Node root;

    @Override
    //存入键值
    public void put(Key key, Value val) {
        if (null == val) throw new NullPointerException();
        this.root = put(key, val, root);
    }

    private Node put(Key key, Value val, Node root) {
        if (null == root) {
            root = new Node(key, val);
        } else {
            int cmp = key.compareTo(root.key);
            if (cmp > 0) {
                root.right = put(key, val, root.right);
                root.size++;
            }
            if (cmp < 0) {
                root.left =put(key, val, root.left);
                root.size++;
            }
            if (cmp == 0) {
                root.val = val;
            }
        }
        return root;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    @Override
    public void delete(Key key) {
        delete(key,root);
    }
    private Node delete(Key key,Node root){
        if(null == root) return null;
        int cmp = key.compareTo(root.key);
        if(cmp>0) root.right = delete(key,root.right);
        if(cmp<0) root.left = delete(key,root.left);
        //执行删除操作
        if(null == root.left) return root.right;
        if(null == root.right) return root.left;

        Node successor = min(root.right);
        successor.left = root.left;
        successor.right = deleteMin(root.right);
        successor.size = 1 + size(successor.left) + size(successor.right);
        return successor;
    }

    @Override
    public int size() {
        if(null == root) return 0;
        return root.size;
    }
    private int size(Node root){
        if(null == root) return 0;
        return root.size;
    }

    private Node deleteMin(Node root){
        if(null == root.left) return root.right;
        return deleteMin(root.left);
    }


    @Override
    public Key min() {
        return min(root) == null ? null:min(root).key;
    }

    private Node min(Node root) {
        if (null == root) return null;
        if (null == root.left) return root;
        return min(root.left);
    }



    @Override
    public Key max() {
        return max(root)==null?null:max(root).key;
    }

    private Node max(Node root) {
        if (null == root) return null;
        if (null == root.right) return root;
        return max(root.right);
    }

    @Override
        //小于等于key的最大键
    public Key floor(Key key) {
        if(null == key) return null;
        return floor(key,root);
    }
    //小于等于key的最大键
    private Key floor(Key key,Node root){
        if(null == root) return null;
        int cmp = key.compareTo(root.key);
        if(cmp < 0) {
            return floor(key,root.left);
        }
        if(cmp > 0){
            return bigger(root.key,floor(key,root.right));
        }
        return root.key;
    }

    private Key bigger(Key rootKey,Key key2){
        if(null == key2) return rootKey;
        int cmp = rootKey.compareTo(key2);
        return cmp >= 0? rootKey:key2;
    }
    @Override
    //大于等于key的最小键
    public Key ceiling(Key key) {
        if(null == key) return null;
        return ceiling(key,root);
    }
    //大于等于key的最小键
    private Key ceiling(Key key,Node root){
        if(null == root) return null;
        int cmp = key.compareTo(root.key);
        if(cmp<0){
            return smaller(root.key,ceiling(key,root.left));
        }
        if(cmp>0){
            return ceiling(key,root.right);
        }
        return root.key;
    }
    private Key smaller(Key rootKey,Key key2){
        if(null == key2) return rootKey;
        int cmp = rootKey.compareTo(key2);
        return cmp <= 0? rootKey:key2;
    }
    @Override
    //小于key的键的数量
    public int rank(Key key) {
        if(null == key) return 0;
        return rank(key,root);
    }
    private int rank(Key key,Node root){
        if(null == root) return 0;
        int cmp = key.compareTo(root.key);
        if(cmp < 0) {
            return rank(key,root.left);
        }
        if(cmp > 0) {
            return rank(key,root.right)+size(root.left)+1;
        }
        return size(root.left);

    }

    @Override
    //排名为k的键
    public Key select(int k) {
        if (k < 0 || k >= size()) return null;
        //排名为k，即找第k+1个元素（从0开始排名）
        return select(k+1,root);
    }
    private Key select(int k,Node root){
        int count = k - size(root.left);
        if(count < 1){
            return select(k,root.left);
        }
        if(count > 1){
            return select(count-1,root.right);
        }
        return root.key;
    }




    private Value get(Node root, Key key) {
        if (null == root) return null;
        int cmp = key.compareTo(root.key);
        if (cmp > 0) return get(root.right, key);
        if (cmp < 0) return get(root.left, key);
        return root.val;
    }


    private class Node {
        public Key key;
        public Value val;
        public Node left, right;
        //Node做根节点的子树有多少个
        public int size;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.size = 1;
            this.left = null;
            this.right = null;
        }


    }

    public static void main(String[] args) {
        BST<String,Integer> binarySearchTree = new BST<>();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String key = sc.nextLine();
            int val = sc.nextInt();
            binarySearchTree.put(key,val);
            System.out.println("next");
            sc.nextLine();
        }
    }

}
