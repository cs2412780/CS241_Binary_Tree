package cs241_ass1;


/**
 * CS 241 Project 1
 * A Binary Tree Node thats can manage a value and has two children.
 * @author liang dong
 *
 */
public class BinaryTreeNode<T> {
	private T data;
	private BinaryTreeNode<T> leftChild;
	private BinaryTreeNode<T> rightChild;
	
	public BinaryTreeNode() {
	}
	
	public BinaryTreeNode(T data) {
		this(data,null,null);
	}
	
	public BinaryTreeNode(T data, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild) {
		this.setData(data);
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the lifeChild
	 */
	public BinaryTreeNode<T> getLeftChild() {
		return leftChild;
	}

	/**
	 * @param lifeChild the lifeChild to set
	 */
	public void setLeftChild(BinaryTreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @return the rightChild
	 */
	public BinaryTreeNode<T> getRightChild() {
		return rightChild;
	}

	/**
	 * @param rightChild the rightChild to set
	 */
	public void setRightChild(BinaryTreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	
	/**
	 * 
	 * @return True if the calling node has no children.
	 */
	public boolean isLeaf() {
		return rightChild == null && leftChild == null;
	}
	
	public void preOrderTraverse() {
		preOrderTraverse(this);
		System.out.println();
	}
	private void preOrderTraverse(BinaryTreeNode<T> root) {
		if(root != null) {
			System.out.print(root.getData() + " ");
			preOrderTraverse(root.leftChild);
			preOrderTraverse(root.rightChild);
		}
	}
	public void inOrderTraverse() {
		inOrderTraverse(this);
		System.out.println();
	}
	private void inOrderTraverse(BinaryTreeNode<T> root) {
		if(root != null) {
			inOrderTraverse(root.leftChild);
			System.out.print(root.getData() + " ");
			inOrderTraverse(root.rightChild);
		}
	}
	public void postOrderTraverse() {
		postOrderTraverse(this);		
		System.out.println();
	}
	private void postOrderTraverse(BinaryTreeNode<T> root) {
		if(root != null) {
			postOrderTraverse(root.leftChild);
			postOrderTraverse(root.rightChild);
			System.out.print(root.getData() + " ");
		}
	}
	
	/**
	 * Makes a full copy of the calling node
	 * @return The cpoy node
	 */
	public BinaryTreeNode<T> copy() {
		BinaryTreeNode<T> newRoot = new BinaryTreeNode<>(this.getData());
		if(leftChild != null) 
			newRoot.setLeftChild(leftChild.copy());
		if(rightChild != null)
			newRoot.setRightChild(rightChild.copy());
		return newRoot;
	}
	
	public int getNumberOfNodes() {
		int count = 1;
		if(leftChild != null)
			count += leftChild.getNumberOfNodes();
		if(rightChild != null)
			count += rightChild.getNumberOfNodes();
		return count;
	}
	
	
	public int getHeight() {
		int heightOfLeftChild = 1;
		int heightOfRightChild = 1;
		if(this.getLeftChild() != null) {
			heightOfLeftChild = 1 + leftChild.getHeight();
		}
		if(this.getRightChild() != null) {
			heightOfRightChild = 1 + rightChild.getHeight();
		}
		return heightOfLeftChild > heightOfRightChild ? heightOfLeftChild : heightOfRightChild;
	}
	
	public T getRightmostData() {
		if(rightChild == null) {
			return this.getData();
		}
		else {
			return rightChild.getRightmostData();
		}
	}
	
	/**
	 * Looks for the Leftmost node of the calling node.
	 * @return The value of the Leftmost node.
	 */
	public T getLeftmostData() {
		if(leftChild == null) {
			return this.getData();
		}
		else {
			return leftChild.getLeftmostData();
		}
	}
	
	/**
	 * Removes for the Leftmost node of the calling node.
	 * @return The updated calling node.
	 */
	public BinaryTreeNode<T> removeLeftmost() {
		if(leftChild == null) {
			return rightChild;
		}
		else {
			leftChild = leftChild.removeLeftmost();
			return this;
		}
	}
	
	/**
	 * Removes for the Rightmost node of the calling node.
	 * @return The updated calling node.
	 */
	public BinaryTreeNode<T> removeRightmost() {
		if(rightChild == null) {
			return leftChild;
		}
		else {
			rightChild = rightChild.removeRightmost();
			return this;
		}
	}

}
