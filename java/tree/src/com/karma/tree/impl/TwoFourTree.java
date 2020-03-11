package com.karma.tree.impl;

import java.util.ArrayList;
import java.util.List;
import com.karma.tree.exception.DuplicateKeyException;
import com.karma.tree.exception.NoPredecessorException;
import com.karma.tree.exception.NoSuccessorException;
import com.karma.tree.exception.NoSuchKeyException;

public class TwoFourTree<T extends Comparable<T>> {

	private TwoFourTree<T> parent = null;
	private TwoFourTree<T> firstChild = null;
	private TwoFourTree<T> secondChild = null;
	private TwoFourTree<T> thirdChild = null;
	private TwoFourTree<T> fourthChild = null;
	private List<T> keys = new ArrayList<>();
	
	public TwoFourTree<T> getParent() {
		return parent;
	}
	public void setParent(TwoFourTree<T> parent) {
		this.parent = parent;
	}
	public TwoFourTree<T> getFirstChild() {
		return firstChild;
	}
	public void setFirstChild(TwoFourTree<T> firstChild) {
		this.firstChild = firstChild;
	}
	public TwoFourTree<T> getSecondChild() {
		return secondChild;
	}
	public void setSecondChild(TwoFourTree<T> secondChild) {
		this.secondChild = secondChild;
	}
	public TwoFourTree<T> getThirdChild() {
		return thirdChild;
	}
	public void setThirdChild(TwoFourTree<T> thirdChild) {
		this.thirdChild = thirdChild;
	}
	public TwoFourTree<T> getForthChild() {
		return fourthChild;
	}
	public void setFourthChild(TwoFourTree<T> fourthChild) {
		this.fourthChild = fourthChild;
	}
	public List<T> getKeys() {
		return keys;
	}
	public void setKeys(List<T> keys) {
		this.keys = keys;
	}
	
	public void insert(T key) throws DuplicateKeyException{
		if(isLeaf()) {
			insertKey(key);
		}else {
			switch (this.keys.size()) {
			case 1:
				if (keys.get(0).compareTo(key) == 0)
					throw new DuplicateKeyException("Key [" + key + "] already exists");

				if (keys.get(0).compareTo(key) < 0) {
					if(secondChild == null) {
						secondChild = new TwoFourTree<T>();
					}
					secondChild.setParent(this);
					secondChild.insert(key);
				} else if (keys.get(0).compareTo(key) > 0) {
					firstChild.setParent(this);
					firstChild.insert(key);
				}
				break;
			case 2:
				if (keys.get(0).compareTo(key) == 0 || keys.get(1).compareTo(key) == 0)
					throw new DuplicateKeyException("Key [" + key + "] already exists");

				if (keys.get(1).compareTo(key) < 0) {
					if(thirdChild == null) {
						thirdChild = new TwoFourTree<T>();
					}
					thirdChild.setParent(this);
					thirdChild.insert(key);;
				} else if (keys.get(0).compareTo(key) < 0 && keys.get(1).compareTo(key) > 0) {
					if(secondChild == null) {
						secondChild = new TwoFourTree<T>();
					}
					secondChild.setParent(this);
					secondChild.insert(key);
				} else if (keys.get(0).compareTo(key) > 0) {
					firstChild.setParent(this);
					firstChild.insert(key);
				}
				break;
			case 3:
				if (keys.get(0).compareTo(key) == 0 || keys.get(1).compareTo(key) == 0  || keys.get(2).compareTo(key) == 0)
					throw new DuplicateKeyException("Key [" + key + "] already exists");

				if (keys.get(2).compareTo(key) < 0) {
					if(fourthChild == null) {
						fourthChild = new TwoFourTree<T>();
					}
					fourthChild.setParent(this);
					fourthChild.insert(key);
				} else if (keys.get(1).compareTo(key) < 0 && keys.get(2).compareTo(key) > 0) {
					if(thirdChild == null) {
						thirdChild = new TwoFourTree<T>();
					}
					thirdChild.setParent(this);
					thirdChild.insert(key);
				} else if (keys.get(0).compareTo(key) < 0 && keys.get(1).compareTo(key) > 0) {
					if(secondChild == null) {
						secondChild = new TwoFourTree<T>();
					}
					secondChild.setParent(this);
					secondChild.insert(key);
				} else if (keys.get(0).compareTo(key) > 0) {
					firstChild.setParent(this);
					firstChild.insert(key);
				}
				break;
			default:
				break;
			}
		}
	}
	
	public void delete(T key) throws NoSuchKeyException {
		if(isLeaf()) {
			//keys size > 1
			// Keys size = 1
		    //	if(sibling.keys.size > 1)
			//		borrowFromSibling()
			// 	else if(sibling.keys.size == 1)
			// 		if(parent.keys.sise > 1)
			//			mergeWithSibling()
			//      else
			// 			merge
			if(!keys.contains(key))
				throw new NoSuchKeyException("");
			if(keys.size() > 1) {
				keys.remove(key);
			}else if(keys.size() == 1) {
				TwoFourTree<T> parent = getParent();
				TwoFourTree<T> sibling = null;
				if(parent.getFirstChild() == this) {
					sibling = parent.getSecondChild();
					
				}else if(parent.getSecondChild() == this) {
					sibling = parent.getFirstChild();
				}else if(parent.getThirdChild() == this) {
					sibling = parent.getSecondChild();
				}else if(parent.getForthChild() == this) {
					sibling = parent.getThirdChild();
				}
				borrowFromSibling(parent, sibling);
			}
		}else {
//			 getPredecessor(key)
//			 swap(predecessor,key)
//			 delete(key)
			
		}
	
	}
	
	private void borrowFromSibling(TwoFourTree<T> parent, TwoFourTree<T> sibling) {
	}
	
	public boolean isLeaf() {
		return firstChild == null;
	}
	
	public void insertKey(T key) throws DuplicateKeyException{
		switch(getKeys().size()) {
		case 0:
			keys.add(key);
			break;
		case 1:
			if (keys.get(0).compareTo(key) == 0)
				throw new DuplicateKeyException("Key [" + key + "] already exists");

			if (keys.get(0).compareTo(key) < 0) {
				keys.add(key);
			} else if (keys.get(0).compareTo(key) > 0) {
				keys.add(0, key);
			}
			break;
		case 2:
			if (keys.get(0).compareTo(key) == 0 || keys.get(1).compareTo(key) == 0)
				throw new DuplicateKeyException("Key [" + key + "] already exists");

			if (keys.get(1).compareTo(key) < 0) {
				keys.add(key);
			} else if (keys.get(0).compareTo(key) < 0 && keys.get(1).compareTo(key) > 0) {
				keys.add(1, key);
			} else if (keys.get(0).compareTo(key) > 0) {
				keys.add(0, key);
			}
			break;
		case 3:
			TwoFourTree<T> splitNode1 = new TwoFourTree<>();
			TwoFourTree<T> splitNode2 = new TwoFourTree<>();
			List<T> split1 = new ArrayList<>(3);
			List<T> split2 = new ArrayList<>(3);
			
			if (keys.get(0).compareTo(key) == 0 || keys.get(1).compareTo(key) == 0 || keys.get(2).compareTo(key) == 0)
				throw new DuplicateKeyException("Key [" + key + "] already exists");

			T promotionKey = null;
			if (keys.get(2).compareTo(key) < 0) {
				split1.add(keys.get(0));
				promotionKey = keys.get(1);
				split2.add(keys.get(2));
				split2.add(key);
			} else if (keys.get(1).compareTo(key) < 0 && keys.get(2).compareTo(key) > 0) {
				split1.add(keys.get(0));
				promotionKey = keys.get(1);
				split2.add(key);
				split2.add(keys.get(2));
			} else if (keys.get(0).compareTo(key) < 0 && keys.get(1).compareTo(key) > 0) {
				split1.add(keys.get(0));
				promotionKey = key;
				split2.add(keys.get(1));
				split2.add(keys.get(2));
			}else if(keys.get(0).compareTo(key) > 0) {
				split1.add(key);
				promotionKey = keys.get(0);
				split2.add(keys.get(1));
				split2.add(keys.get(2));
			}
			
			splitNode1.setKeys(split1);
			splitNode2.setKeys(split2);
			splitAndPromote(promotionKey, splitNode1, splitNode2);
			break;
		default:
			break;
	 }
	}
	
	public void splitAndPromote(T promotionKey, TwoFourTree<T> splitNode1, TwoFourTree<T> splitNode2) throws DuplicateKeyException{
		
		TwoFourTree<T> parent = getParent();
		if(parent == null) {
			keys.removeAll(keys);
			keys.add(promotionKey);
			splitNode1.setFirstChild(getFirstChild());
			splitNode1.setSecondChild(getSecondChild());
			splitNode2.setFirstChild(getThirdChild());
			this.setFirstChild(splitNode1);
			this.setSecondChild(splitNode2);
			this.setThirdChild(null);
			this.setFourthChild(null);
			splitNode1.setParent(this);
			splitNode2.setParent(this);
		}else {
			TwoFourTree<T> first = parent.getFirstChild();
			TwoFourTree<T> second = parent.getSecondChild();
			TwoFourTree<T> third = parent.getThirdChild();
			TwoFourTree<T> forth = parent.getForthChild();
			if(parent.getKeys().size() == 2) {
				if(first == this) {
					parent.setThirdChild(second);
					parent.setSecondChild(splitNode2);
					parent.setFirstChild(splitNode1);
				}else if(second == this) {
					parent.setFourthChild(third);
					parent.setThirdChild(splitNode2);
					parent.setSecondChild(splitNode1);
				}else if(third == this) {
					parent.setFourthChild(splitNode2);
					parent.setThirdChild(splitNode1);
				}
				splitNode1.setParent(parent);
				splitNode2.setParent(parent);
				parent.insertKey(promotionKey);
			}else if(parent.getKeys().size() == 1) {
				if(first == this) {
					parent.setThirdChild(second);
					parent.setSecondChild(splitNode2);
					parent.setFirstChild(splitNode1);
				}else if(second == this) {
					parent.setThirdChild(splitNode2);
					parent.setSecondChild(splitNode1);
				}
				splitNode1.setParent(parent);
				splitNode2.setParent(parent);
				parent.insertKey(promotionKey);
			}else if(parent.getKeys().size() == 3) {
				TwoFourTree<T> parentSplitNode1 = new TwoFourTree<>();
				TwoFourTree<T> parentSplitNode2 = new TwoFourTree<>();
				List<T> split1 = new ArrayList<>(3);
				List<T> split2 = new ArrayList<>(3);
				T parentPromotionKey = null;
				if (parent.getKeys().get(2).compareTo(promotionKey) < 0) {
					split1.add(parent.getKeys().get(0));
					parentPromotionKey = parent.getKeys().get(1);
					split2.add(parent.getKeys().get(2));
					split2.add(promotionKey);
				} else if (parent.getKeys().get(1).compareTo(promotionKey) < 0 && parent.getKeys().get(2).compareTo(promotionKey) > 0) {
					split1.add(parent.getKeys().get(0));
					parentPromotionKey = parent.getKeys().get(1);
					split2.add(promotionKey);
					split2.add(parent.getKeys().get(2));
				} else if (parent.getKeys().get(0).compareTo(promotionKey) < 0 && parent.getKeys().get(1).compareTo(promotionKey) > 0) {
					split1.add(parent.getKeys().get(0));
					parentPromotionKey = promotionKey;
					split2.add(parent.getKeys().get(1));
					split2.add(parent.getKeys().get(2));
				}else if(parent.getKeys().get(0).compareTo(promotionKey) > 0) {
					split1.add(promotionKey);
					parentPromotionKey = parent.getKeys().get(0);
					split2.add(parent.getKeys().get(1));
					split2.add(parent.getKeys().get(2));
				}
				
				parentSplitNode1.setKeys(split1);
				parentSplitNode2.setKeys(split2);
				if(parent.getFirstChild() == this) {
					parentSplitNode1.setFirstChild(splitNode1);
					parentSplitNode1.setSecondChild(splitNode2);
					splitNode1.setParent(parentSplitNode1);
					splitNode2.setParent(parentSplitNode1);
					parentSplitNode2.setFirstChild(second);
					parentSplitNode2.setSecondChild(third);
					parentSplitNode2.setThirdChild(forth);
					second.setParent(parentSplitNode2);
					third.setParent(parentSplitNode2);
					forth.setParent(parentSplitNode2);
				}else if(parent.getSecondChild() == this) {
					parentSplitNode1.setFirstChild(first);
					parentSplitNode1.setSecondChild(splitNode1);
					first.setParent(parentSplitNode1);
					splitNode1.setParent(parentSplitNode1);
					parentSplitNode2.setFirstChild(splitNode2);
					parentSplitNode2.setSecondChild(third);
					parentSplitNode2.setThirdChild(forth);
					splitNode2.setParent(parentSplitNode2);
					third.setParent(parentSplitNode2);
					forth.setParent(parentSplitNode2);
				}else if(parent.getThirdChild() == this) {
					parentSplitNode1.setFirstChild(first);
					parentSplitNode1.setSecondChild(second);
					first.setParent(parentSplitNode1);
					second.setParent(parentSplitNode1);
					parentSplitNode2.setFirstChild(splitNode1);
					parentSplitNode2.setSecondChild(splitNode2);
					parentSplitNode2.setThirdChild(forth);
					splitNode1.setParent(parentSplitNode2);
					splitNode2.setParent(parentSplitNode2);
					forth.setParent(parentSplitNode2);
				}else if(parent.getForthChild() == this) {
					parentSplitNode1.setFirstChild(first);
					parentSplitNode1.setSecondChild(second);
					first.setParent(parentSplitNode1);
					second.setParent(parentSplitNode1);
					parentSplitNode2.setFirstChild(third);
					parentSplitNode2.setSecondChild(splitNode1);
					parentSplitNode2.setThirdChild(splitNode2);
					third.setParent(parentSplitNode2);
					splitNode1.setParent(parentSplitNode2);
					splitNode2.setParent(parentSplitNode2);
				}
				parent.splitAndPromote(parentPromotionKey, parentSplitNode1, parentSplitNode2);
			}
		}
	}
	
	public TwoFourTree<T> search(T key) throws NoSuchKeyException{
		TwoFourTree<T> node = null;
		switch (this.getKeys().size()) {
		case 1:
			if(this.getKeys().get(0).compareTo(key) == 0) {
				node = this;
			}else if(this.getKeys().get(0).compareTo(key) > 0) {
				if(getFirstChild() == null) {
					throw new NoSuchKeyException("Key = ["+key+"] does not exist.");
				}else {
					return getFirstChild().search(key);					
				}
			}else {
				if(getSecondChild() == null) {
					throw new NoSuchKeyException("Key = ["+key+"] does not exist.");
				}else {
					return getSecondChild().search(key);					
				}
			}
			break;
		case 2:
			if(this.getKeys().get(0).compareTo(key) == 0 || this.getKeys().get(1).compareTo(key) == 0) {
				node = this;
			}else if(getKeys().get(0).compareTo(key) > 0) {
				if(getFirstChild() == null) {
					throw new NoSuchKeyException("Key = ["+key+"] does not exist.");
				}else {
					return getFirstChild().search(key);
				}
			}else if(getKeys().get(0).compareTo(key) < 0 && getKeys().get(1).compareTo(key) > 0) {
				if(getSecondChild() == null) {
					throw new NoSuchKeyException("Key = ["+key+"] does not exist.");
				}else {
					return getSecondChild().search(key);
				}
			}else if(getKeys().get(1).compareTo(key) < 0) {
				if(getThirdChild() == null) {
					throw new NoSuchKeyException("Key = ["+key+"] does not exist.");
				}else {
					return getThirdChild().search(key);
				}
			}
			break;
		case 3:
			if(this.getKeys().get(0).compareTo(key) == 0 || this.getKeys().get(1).compareTo(key) == 0 || this.getKeys().get(2).compareTo(key) == 0) {
				node = this;
			}else if(getKeys().get(0).compareTo(key) > 0) {
				if(getFirstChild() == null) {
					throw new NoSuchKeyException("Key = ["+key+"] does not exist.");
				}else {
					return getFirstChild().search(key);
				}
			}else if(getKeys().get(0).compareTo(key) < 0 && getKeys().get(1).compareTo(key) > 0) {
				if(getSecondChild() == null) {
					throw new NoSuchKeyException("Key = ["+key+"] does not exist.");
				}else {
					return getSecondChild().search(key);
				}
			}else if(getKeys().get(1).compareTo(key) < 0 && getKeys().get(2).compareTo(key) > 0) {
				if(getThirdChild() == null) {
					throw new NoSuchKeyException("Key = ["+key+"] does not exist.");
				}else {
					return getThirdChild().search(key);
				}
			}else if(getKeys().get(2).compareTo(key) < 0) {
				if(getThirdChild() == null) {
					throw new NoSuchKeyException("Key = ["+key+"] does not exist.");
				}else {
					return getForthChild().search(key);
				}
			}
			break;
		default:
			break;
		}
		return node;
	}
	
	public T min(){
		if(isLeaf())
			return keys.get(0);

		TwoFourTree<T> first = getFirstChild();
		while(first.getFirstChild() != null) 
			first = first.getFirstChild();
		return first.getKeys().get(0);
	}
	
	public T max(){
		if(isLeaf())
			return keys.get(keys.size()-1);
		TwoFourTree<T> next = getForthChild() != null ? getForthChild() : ((getThirdChild() != null) ? getThirdChild(): getSecondChild());
		while(!next.isLeaf())
			next = next.getForthChild() != null ? next.getForthChild() : ((next.getThirdChild() != null) ? next.getThirdChild(): next.getSecondChild());
		return next.getKeys().get(next.getKeys().size()-1);
	}
	
	public T predecessor(T key) throws NoSuchKeyException, NoPredecessorException{
		TwoFourTree<T> node = search(key);
		if(node == null) return null;
		if(node.getParent() == null && node.getKeys().indexOf(key) == 0 && node.isLeaf()) {
			throw new NoPredecessorException("No pre-decessor for given key = "+key+". Looks like this is minimum key");
		}
		if(node.isLeaf()) {
			if(node.getKeys().indexOf(key) == 0 ) {
				if(node.getParent().getFirstChild() == node) {
					throw new NoPredecessorException("No pre-decessor for given key = "+key+". Looks like this is minimum key");
				}else if(node.getParent().getSecondChild() == node) {
					return node.getParent().getKeys().get(0);					
				}else if(node.getParent().getThirdChild() == node) {
					return node.getParent().getKeys().get(1);
				}else if(node.getParent().getForthChild() == node) {
					return node.getParent().getKeys().get(2);
				}
			}else {
				return node.getKeys().get(node.getKeys().indexOf(key)-1);				
			}
		}else{
			switch (node.getKeys().indexOf(key)) {
			case 0:
				node = node.getFirstChild();
				break;
			case 1:
				node = node.getSecondChild();
				break;
			case 2:
				node = node.getThirdChild();
				break;
			default:
				break;
			}
		}
		return node.max();
	}
	
	public T successor(T key) throws NoSuchKeyException, NoSuccessorException{
		TwoFourTree<T> node = search(key);
		if(node == null) return null;
		
		if(node.getParent() == null && node.isLeaf() && node.getKeys().indexOf(key) == node.getKeys().size()-1)
			throw new NoSuccessorException("No successor for given key = "+key+". Looks like this is maximum key");	
		
		if(node.isLeaf()) {
			if(node.getKeys().indexOf(key) == node.getKeys().size()-1) {
				if(node.getParent().getForthChild() == node) {
					throw new NoSuccessorException("No successor for given key = "+key+". Looks like this is maximum key");									
				}else if(node.getParent().getThirdChild() == node) {
					return node.getParent().getKeys().get(2);
				}else if(node.getParent().getSecondChild() == node) {
					return node.getParent().getKeys().get(1);
				}else if(node.getParent().getFirstChild() == node) {
					return node.getParent().getKeys().get(0);
				}
			}else{
				return node.getKeys().get(node.getKeys().indexOf(key)+1);
			}
		}else {
			switch (node.getKeys().indexOf(key)) {
			case 0:
				node = node.getSecondChild();
				break;
			case 1:
				node = node.getThirdChild();
				break;
			case 2:
				node = node.getForthChild();
				break;
			default:
				break;
			}
		}
		return node.min();
	}
}
