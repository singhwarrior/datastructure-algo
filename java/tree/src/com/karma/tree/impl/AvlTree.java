package com.karma.tree.impl;

import com.karma.tree.exception.DuplicateKeyException;
import com.karma.tree.exception.NoSuchKeyException;

public class AvlTree<T extends Comparable<T>> extends BinarySearchTree<T> {

	private Integer height;

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	@Override
	public void insert(T key) throws DuplicateKeyException {
		AvlTree<T> keyNode = new AvlTree<>();
		keyNode.setValue(key);
		keyNode.setHeight(1);
		if (this.content() == null) {
			this.setValue(key);
			this.setHeight(1);
		} else if (this.content().compareTo(key) > 0) {
			if (this.leftChild() == null && this.rightSibling() != null) {
				this.setLeftChild(keyNode);
			} else if (this.isLeaf()) {
				this.setLeftChild(keyNode);
				this.setHeight(2);
				AvlTree<T> imBalanceNode = findImbalance(this);
				rotationForInsert(imBalanceNode, key);
			} else if (this.leftChild() != null) {
				((AvlTree<T>) this.leftChild()).insert(key);
			}
		} else if (this.content().compareTo(key) < 0){
			if (this.rightSibling() == null && this.leftChild() != null) {
				this.setRightChild(keyNode);
			} else if (this.isLeaf()) {
				this.setRightChild(keyNode);
				this.setHeight(2);
				AvlTree<T> imBalanceNode = findImbalance(this);
				rotationForInsert(imBalanceNode, key);
			} else if (this.rightSibling() != null) {
				((AvlTree<T>) this.rightSibling()).insert(key);
			}
		} else if (this.content().compareTo(key) == 0){
			throw new DuplicateKeyException("Key = "+key+" already exists.");
		}
	}



	@Override
	public void delete(T key) throws NoSuchKeyException {
		AvlTree<T> keyNode = (AvlTree<T>) search(key);
		if (keyNode.isLeaf()) {
			AvlTree<T> parent = (AvlTree<T>) keyNode.parent();
			boolean hasBothChildren = (parent.leftChild() != null && parent.rightSibling() != null) ? true : false;
			keyNode.setParent(null);
			if(parent != null) {
				if (parent.leftChild() == keyNode) {
					parent.setLeftChild(null);
					if (!hasBothChildren) {
						parent.setHeight(1);
						recursiveRotation(parent,key);
					}else 
						recursiveRotation((AvlTree<T>)parent.rightSibling(),key);
				}
				if (parent.rightSibling() == keyNode) {
					parent.setRightChild(null);
					if (!hasBothChildren) {
						parent.setHeight(1);
						recursiveRotation(parent,key);
					}else 
						recursiveRotation((AvlTree<T>)parent.leftChild(),key);
				}
			}
		} else if (keyNode.leftChild() != null && keyNode.rightSibling() == null) {
			AvlTree<T> left = (AvlTree<T>) keyNode.leftChild();
			left.setParent(null);
			keyNode.setValue(left.content());
			keyNode.setHeight(1);
			keyNode.setLeftChild(null);
			left = null;
			recursiveRotation(keyNode, key);
		} else if (keyNode.leftChild() == null && keyNode.rightSibling() != null) {
			AvlTree<T> right = (AvlTree<T>) keyNode.rightSibling();
			right.setParent(null);
			keyNode.setValue(right.content());
			keyNode.setHeight(1);
			keyNode.setRightChild(null);
			right = null;
			recursiveRotation(keyNode, key);
		} else {
			AvlTree<T> successor = (AvlTree<T>) keyNode.successor(key);
			AvlTree<T> parent = (AvlTree<T>) successor.parent();
			keyNode.setValue(successor.content());
			successor.delete(successor.content());
			recursiveRotation(parent, key);
		}
	}
	
	private void rotationForInsert(AvlTree<T> imBalanceNode, T key) {
		if (imBalanceNode == null)
			return;
		if (imBalanceNode.content().compareTo(key) > 0) {
			AvlTree<T> leftChild = (AvlTree<T>)imBalanceNode.leftChild();
			if(leftChild != null && leftChild.content().compareTo(key) > 0) {
				rotateRight(imBalanceNode);
			}else if(leftChild != null && leftChild.content().compareTo(key) < 0) {
				rotateLeft((AvlTree<T>) leftChild);
				rotateRight((AvlTree<T>) imBalanceNode);
			}
		}else if(imBalanceNode.content().compareTo(key) < 0){
			AvlTree<T> rightChild = (AvlTree<T>)imBalanceNode.rightSibling();
			if(rightChild != null && rightChild.content().compareTo(key) < 0) {
				rotateLeft(imBalanceNode);
			}else if(rightChild != null && rightChild.content().compareTo(key) > 0) {
				rotateRight((AvlTree<T>) rightChild);
				rotateLeft((AvlTree<T>) imBalanceNode);
			}
		}
	}

	private void recursiveRotation(AvlTree<T> avlNode, T key) {
		AvlTree<T> imbalanceNode = findImbalance(avlNode);
		if(imbalanceNode != null) {
			rotationForDelete(imbalanceNode, key);
			if(!imbalanceNode.isRoot()) {
				recursiveRotation((AvlTree<T>)imbalanceNode.parent(), key);							
			}
		}
	}
	
	private void rotationForDelete(AvlTree<T> imBalanceNode, T key) {
		if (imBalanceNode == null)
			return;
		
		AvlTree<T> leftChild = (AvlTree<T>)imBalanceNode.leftChild();
		AvlTree<T> rightChild = (AvlTree<T>)imBalanceNode.rightSibling();
		int leftHeight = (leftChild != null) ? leftChild.getHeight() : 0;
		int rightHeight = (rightChild != null) ? rightChild.getHeight() : 0;
		
		if (imBalanceNode.content().compareTo(key) < 0) {
			if(leftHeight > rightHeight) {
				leftChild = (leftChild != null) ? (AvlTree<T>)leftChild.leftChild() : null;
				rightChild = (leftChild != null) ? (AvlTree<T>)leftChild.rightSibling() : null;
				leftHeight = (leftChild != null) ? leftChild.getHeight() : 0;
				rightHeight = (rightChild != null) ? rightChild.getHeight() : 0;
				if(leftHeight >= rightHeight) {
					rotateRight(imBalanceNode);
				}else {
					rotateLeft((AvlTree<T>) imBalanceNode.leftChild());
					rotateRight((AvlTree<T>) imBalanceNode);
				}
			}
		}else {
			if(leftHeight < rightHeight) {
				leftChild = (rightChild != null) ? (AvlTree<T>)rightChild.leftChild() : null;
				rightChild = (rightChild != null) ? (AvlTree<T>)rightChild.rightSibling() : null;
				leftHeight = (leftChild != null) ? leftChild.getHeight() : 0;
				rightHeight = (rightChild != null) ? rightChild.getHeight() : 0;
				if(leftHeight <= rightHeight) {
					rotateLeft(imBalanceNode);
				}else {
					rotateRight((AvlTree<T>) imBalanceNode.rightSibling());
					rotateLeft((AvlTree<T>) imBalanceNode);
				}
			}
		}
	}

	private void rotateRight(AvlTree<T> imBalanceNode) {
		if (imBalanceNode.isRoot()) {
			AvlTree<T> left = (AvlTree<T>) imBalanceNode.leftChild();
			AvlTree<T> rootCopy = new AvlTree<>();
			rootCopy.setValue(imBalanceNode.content());
			rootCopy.setHeight(imBalanceNode.getHeight() - 1);
			rootCopy.setRightChild(imBalanceNode.rightSibling());
			rootCopy.setLeftChild(left.rightSibling());
			imBalanceNode.setValue(left.content());
			imBalanceNode.setLeftChild(left.leftChild());
			imBalanceNode.setRightChild(rootCopy);
			left.setParent(null);
		} else {
			AvlTree<T> parent = (AvlTree<T>) imBalanceNode.parent();
			boolean isLeft = (parent != null && parent.leftChild() == imBalanceNode) ? true : false;
			boolean isRight = (parent != null && parent.rightSibling() == imBalanceNode) ? true : false;
			AvlTree<T> left = (AvlTree<T>) imBalanceNode.leftChild();
			if (left != null) {
				imBalanceNode.setLeftChild(left.rightSibling());
				imBalanceNode.setHeight(imBalanceNode.getHeight() - 1);
				left.setRightChild(imBalanceNode);
				AvlTree<T> leftChild = (AvlTree<T>) left.leftChild();
				AvlTree<T> rightChild = (AvlTree<T>) left.rightSibling();
				int lHght = (leftChild != null) ? leftChild.getHeight() : 0;
				int rHght = (rightChild != null) ? rightChild.getHeight() : 0;
				left.setHeight(Math.max(lHght, rHght) + 1);
			}
			if (isLeft) {
				parent.setLeftChild(left);
			}
			if (isRight) {
				parent.setRightChild(left);
			}
		}
	}

	private void rotateLeft(AvlTree<T> imBalanceNode) {
		if (imBalanceNode.isRoot()) {
			AvlTree<T> right = (AvlTree<T>) imBalanceNode.rightSibling();
			AvlTree<T> rootCopy = new AvlTree<>();
			rootCopy.setValue(imBalanceNode.content());
			rootCopy.setHeight(imBalanceNode.getHeight() - 1);
			rootCopy.setLeftChild(imBalanceNode.leftChild());
			rootCopy.setRightChild(right.leftChild());
			imBalanceNode.setValue(right.content());
			imBalanceNode.setRightChild(right.rightSibling());
			imBalanceNode.setLeftChild(rootCopy);
			right.setParent(null);
		} else {
			AvlTree<T> parent = (AvlTree<T>) imBalanceNode.parent();
			boolean isLeft = (parent != null && parent.leftChild() == imBalanceNode) ? true : false;
			boolean isRight = (parent != null && parent.rightSibling() == imBalanceNode) ? true : false;
			AvlTree<T> right = (AvlTree<T>) imBalanceNode.rightSibling();
			if(right != null) {
				imBalanceNode.setRightChild(right.leftChild());
				imBalanceNode.setHeight(imBalanceNode.getHeight() - 1);
				right.setLeftChild(imBalanceNode);	
				AvlTree<T> leftChild = (AvlTree<T>) right.leftChild();
				AvlTree<T> rightChild = (AvlTree<T>) right.rightSibling();
				int lHght = (leftChild != null) ? leftChild.getHeight() : 0;
				int rHght = (rightChild != null) ? rightChild.getHeight() : 0;
				right.setHeight(Math.max(lHght, rHght) + 1);
			}
			if (isLeft) {
				parent.setLeftChild(right);
			}
			if (isRight) {
				parent.setRightChild(right);
			}
		}
	}

	private AvlTree<T> findImbalance(AvlTree<T> avlTree) {
		AvlTree<T> parent = (AvlTree<T>) avlTree.parent();
		while (parent != null) {
			AvlTree<T> left = (AvlTree<T>) parent.leftChild();
			AvlTree<T> right = (AvlTree<T>) parent.rightSibling();
			int lHght = (left != null) ? left.getHeight() : 0;
			int rHght = (right != null) ? right.getHeight() : 0;
			if (Math.abs(lHght - rHght) > 1)
				break;
			parent.setHeight(Math.max(lHght, rHght) + 1);
			parent = (AvlTree<T>) parent.parent();
		}
		return parent;
	}

}
