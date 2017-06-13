package cs241_ass1;

import java.util.Stack;

/**
 * CS 241 Project 1
 * A Binary Search Tree thats can manage integers in a banary tree
 * @author liang dong
 *
 */
public class BinarySearchTree {
	private BinaryTreeNode<Integer> root;// Root of the tree
	private int numberOfNodes;
	
	public BinarySearchTree() {
	}
	
	/**
	 * Creates a new tree with a root that stores a given number.
	 * @param newValue A given number
	 */
	public BinarySearchTree(Integer newValue) {
		root = new BinaryTreeNode<>(newValue);
		numberOfNodes = 1;
	}
	
	
	/**
	 * @return the root
	 */
	public BinaryTreeNode<Integer> getRoot() {
		return root;
	}

	/**
	 * Adds a new number into the tree. 
	 * compare the new number with nodes,
	 * if the new number is less that a node, 
	 * compare with the node's left child, 
	 * otherwise compare with the right child. 
	 * keep comparing until reach a empty space.
	 * Duplicated number is not allowed.
	 * @param newValue The new number to be added.
	 * @return True if adds successfully, false otherwise.
	 */
	public boolean addWithRec(Integer newValue) {
		if(root == null) {
			BinaryTreeNode<Integer> temp = new BinaryTreeNode<>(newValue);
			root = temp;
			numberOfNodes++;
			return true;
		}
		return addWithRec(root, newValue);
	}
	
	/**
	 * Helping function for adding.
	 * @param root The root of the tree
	 * @param newValue The new number to be added.
	 * @return True if adds successfully, false otherwise.
	 */
	private boolean addWithRec(BinaryTreeNode<Integer> root, Integer newValue) {
		if(root.getData().equals(newValue)) {
			return false;
		}
		else if(root.getData().compareTo(newValue) > 0) {
			if(root.getLeftChild() != null) {
				return addWithRec(root.getLeftChild(), newValue);
			}
			else {
				BinaryTreeNode<Integer> temp = new BinaryTreeNode<>(newValue);
				root.setLeftChild(temp);
				numberOfNodes++;
				return true;
			}
		}
		else {
			if(root.getRightChild() != null) {
				return addWithRec(root.getRightChild(), newValue);
			}
			else {
				BinaryTreeNode<Integer> temp = new BinaryTreeNode<>(newValue);
				root.setRightChild(temp);
				numberOfNodes++;
				return true;
			}
		}
	}

	/**
	 * @return the numberOfNodes
	 */
	public int getNumberOfNodes() {
		return numberOfNodes;
	}
	
	/**
	 * Prints the values in pre-order.
	 */
	public void preOrderTraverse() {
		if(root == null) {
			return;
		}
		root.preOrderTraverse();
	}
	
	/**
	 * Prints the values in in-order.
	 */
	public void inOrderTraverse() {
		if(root == null) {
			return;
		}
		root.inOrderTraverse();
	}
	
	/**
	 * Prints the values in post-order.
	 */
	public void postOrderTraverse() {
		if(root == null) {
			return;
		}
		root.postOrderTraverse();
	}
	
	/**
	 * Romves a number in the tree.
	 * @param value The number to be removed.
	 * @return True if removes succesfully.
	 */
	public boolean removeWithRec(Integer value) {
		if(numberOfNodes == 0) {
			return false;
		}
		if(root.getData().equals(value)) {
			numberOfNodes--;
			if(root.getLeftChild() != null) {
				root.setData(root.getLeftChild().getRightmostData());
				root.setLeftChild(root.getLeftChild().removeRightmost());
				return true;
			}
			else if(root.getRightChild() != null){
				root.setData(root.getRightChild().getLeftmostData());
				root.setRightChild(root.getRightChild().removeLeftmost());
				return true;
			}
			root = null;
			return true;
		}
		else {
			return removeWithRec(root, value);
		}
	}
	
	/**
	 * Helping function for removing.
	 * @param root The root of the tree.
	 * @param value The number to be removed.
	 * @return True if removes successfully.
	 */
	private boolean removeWithRec(BinaryTreeNode<Integer> root, Integer value) {	
		if(root.getData().compareTo(value) > 0) {
			if(root.getLeftChild() != null) {		
				if(root.getLeftChild().getData().compareTo(value) == 0) {
					numberOfNodes--;
					if(root.getLeftChild().isLeaf()) {
						root.setLeftChild(null);
						return true;
					}
					if (root.getLeftChild().getLeftChild() != null && root.getLeftChild().getRightChild() != null) {
						root.getLeftChild().setData(root.getLeftChild().getRightChild().getLeftmostData());
						root.getLeftChild().setRightChild(root.getLeftChild().getRightChild().removeLeftmost());
						return true;
					}
					else {
						if(root.getLeftChild().getLeftChild() != null) {
							root.setLeftChild(root.getLeftChild().getLeftChild());
							root.getLeftChild().setLeftChild(null);
							return true;
						}
						else {
							root.setLeftChild(root.getLeftChild().getRightChild());
							root.getLeftChild().setRightChild(null);
							return true;
						}
						
					}
				}
				else {
					return removeWithRec(root.getLeftChild(), value);
				}
			}
			else {
				return false;
			}
			
		}
		else {
			if(root.getRightChild() != null) {
				if(root.getRightChild().getData().compareTo(value) == 0) {
					numberOfNodes--;
					if(root.getRightChild().isLeaf()) {
						root.setRightChild(null);
						return true;
					}
					if (root.getRightChild().getLeftChild() != null && root.getRightChild().getRightChild() != null) {
						root.getRightChild().setData(root.getRightChild().getRightChild().getLeftmostData());
						root.getRightChild().setRightChild(root.getRightChild().getRightChild().removeLeftmost());
						return true;
					}
					else {
						if(root.getRightChild().getLeftChild() != null) {
							root.setRightChild(root.getRightChild().getLeftChild());
							root.getRightChild().setLeftChild(null);
							return true;
						}
						else {
							root.setRightChild(root.getRightChild().getRightChild());
							root.getRightChild().setRightChild(null);
							return true;
						}
						
					}
				}
				else {
					return removeWithRec(root.getRightChild(), value);
				}
			}
			else {
				return false;
			}
		}
	}
	
	/**
	 * Find the node that appears right 
	 * before the given value in an in-order traversal.
	 * @param value 
	 * @return The Predecessor of the given value, null if no such Predecessor.
	 */
	public Integer getPredecessor(Integer value) {
		Integer holder[] = new Integer[2];
		getPredecessor(root, value, holder);
		Integer predecessor = holder[0];
		return predecessor;
	}
	
	/**
	 * Looks for the Predecessor of a given value with recursion.
	 * @param root The root of the tree
	 * @param value
	 * @param holder An array that holder[1] keeps track of the elements, holder[0] holds the Predecessor.
	 */
	private void getPredecessor(BinaryTreeNode<Integer> root, Integer value, Integer[] holder) {
		if(holder[0] != null) {
			return;
		}
		if(root != null) {
			getPredecessor(root.getLeftChild(), value, holder);	
			if(root.getData().equals(value)) {
				holder[0] = holder[1];
			}
			holder[1] = root.getData();
			getPredecessor(root.getRightChild(), value, holder);
		}
	}
	
	/**
	 * Find the node that appears right 
	 * after the given value in an in-order traversal.
	 * @param value 
	 * @return The Successor of the given value, null if no such Successor.
	 */
	public Integer getSuccessor(Integer value) {	
		if(root == null) {
			return null;
		}
		Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
		BinaryTreeNode<Integer> temp = null;
		BinaryTreeNode<Integer> targetNode = null;
		boolean addNew = true;
		stack.push(root);
		while(!stack.isEmpty()) {
			while(stack.peek().getLeftChild() != null && addNew) {
				stack.push(stack.peek().getLeftChild());
			}
			addNew = false;
			temp = targetNode;
			targetNode = stack.pop();
			if(temp != null && temp.getData().equals(value)) {
				if(targetNode != null) {
					return targetNode.getData();
				}
			}
			if(targetNode.getRightChild() != null) {
				stack.push(targetNode.getRightChild());
				addNew = true;
			}
		}
		return null;
	}
	
}
