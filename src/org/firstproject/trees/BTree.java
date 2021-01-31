package org.firstproject.trees;

public class BTree {
    private TNode root;

    public TNode insert(int data){
        TNode newNode = new TNode(data);
        if(root == null) {
            this.root = newNode;
            return this.root;
        }
        TNode tempNode = this.root;
        while(tempNode.left != null && tempNode.right != null){
            if( data <= tempNode.getPayload())
                tempNode = tempNode.left;
            else
                tempNode = tempNode.right;
        }
        if( data <= tempNode.getPayload())
            tempNode.left = newNode;
        else
            tempNode.right = newNode;
        return this.root;
    }

    public void inorder(TNode tree){
        if(tree == null)
            return;
        inorder(tree.left);
        System.out.print(tree.payload + " -> ");
        inorder(tree.right);
    }
}
