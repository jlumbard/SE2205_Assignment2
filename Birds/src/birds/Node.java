/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birds;

/**
 *
 * @author Kevin
 */
public class Node {

    private BirdRecord data;
    private Node leftChild;
    private Node rightChild;
    private Node parent;
    
    public Node(){
     this(null);
    }
    public Node (BirdRecord data){
        this(data, null,null, null);
    }
    
    public Node(BirdRecord bRec, Node left, Node right, Node parent){
    this.data = bRec;
    this.leftChild = left;
    this.rightChild = right;
    this.parent = parent;
    }
    
    public BirdRecord GetBirdRecord(){
    return data;
    }
    
    public Node getLeft(){
        return leftChild;
    }
    
    public Node getRight(){
        return rightChild;
    }
    
    public Node getParent(){
        return parent;
    }
    
    public void setData(BirdRecord newData){
        this.data = newData;
    }
    
    public void setLeft (Node left){
        this.leftChild = left;
    }
    
    public void setRight (Node right){
        this.rightChild = right;
    }
    
    public void setParent (Node parent){
        this.parent = parent;
    }
    
    public boolean hasLeftChild(){
        return leftChild != null;
    }
    
    public boolean isLeaf(){
        return (leftChild == null) && (rightChild ==null);
    }
    
}
