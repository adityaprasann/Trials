package org.firstproject.trees;

public class TreeClientCode {
    public static void main(String[] args) {
        TreeClientCode tcc = new TreeClientCode();
        BTree tree = new BTree();
        int[] arr = {100, 50, 200, 25, 75, 125, 350, 30, 60};
        TNode root = tcc.createTree(tree, arr);
        BTreeHelper helper = new BTreeHelper(root);
        tree.inorder(root);
        System.out.println();
        int min = helper.findMinValue();
        System.out.println("Min Val " + min);
        int height = helper.findHeight(root);
        System.out.println("Height " + height);
    }

    private TNode createTree(BTree tree, int[] arr) {
        TNode root = null;
        for (int i:arr) {
            root = tree.insert(i);
        }
        return root;
    }
}
