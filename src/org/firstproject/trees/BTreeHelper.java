package org.firstproject.trees;

public class BTreeHelper {
    TNode root;

    public BTreeHelper(TNode root) {
        this.root = root;
    }

    public int findMinValue() {
        TNode node = root;
        while(node.getLeft() != null)
            node = node.getLeft();
        return node.getPayload();
    }

    public int findHeight(TNode root) {
        if(root.getLeft() == null && root.getRight() == null)
            return 0;
        int lHeight = (root.getLeft() == null) ? Integer.MIN_VALUE : findHeight(root.getLeft());
        int rHeight = (root.getRight() == null) ? Integer.MIN_VALUE : findHeight(root.getRight());
        return (lHeight > rHeight) ? lHeight + 1 : rHeight +  1;
    }
}
