package com.karma.st;

public class HashTable<Key, Value>{
	
    private class Node{
        Key key;
        Value value;
        Node next;
        public Node(Key key, Value value){
        	this.key = key;
            this.value = value; 
        }
    }
    
    
    
    public final class Entry{
    	
    	Key key;
    	Value value;
    	
    	public Key getKey() {
    		return this.key;
    	}
    	
    	public Value getValue() {
    		return this.value;
    	}
    	
    }

    private Node[] buckets;
    private static final int M = 97;
    
    public HashTable(){
        buckets =  (HashTable<Key, Value>.Node[]) new HashTable.Node[M];    
    }

    private int hash(Key key){
        return key.hashCode() % M;
    }

    public Value get(Key key){
        int idx = hash(key);
        Node h = buckets[idx];
        while(h != null){
            if(h.key.equals(key)) break;
            h = h.next; 
        }
        if(h == null) return null;
        return h.value; 
    }

    public void put(Key key, Value value){
        int idx = hash(key);
        Node h = buckets[idx];
        while(h != null){
            if(h.key.equals(key)) break;
            h = h.next;  
        }
        if(h != null) 
        	h.value = value;
        else{
            Node n = new Node(key, value);
	n.next = buckets[idx];
            buckets[idx] = n;
        }
    }

    public void delete(Key key){
        int idx = hash(key);
        Node curr = buckets[idx];
        Node prev = curr;
        while(curr != null){
            if(curr.key.equals(key)) break;
            prev = curr;
            curr = curr.next;
        }
        if(curr != null){
            if(prev != curr){
            	prev.next = curr.next;
            }else{
            	buckets[idx] = curr.next; 
            }
            
        }
    }
    
//    private class HashTableIterator<Key> implements Iterator<Key>{
//
//    	
//		@Override
//		public boolean hasNext() {
//			for(int )
//			return buckets;
//		}
//
//		@Override
//		public Key next() {
//			return null;
//		}
//    	
//    }
    
//    @Override
//	public Iterator<Key> iterator() {
//    	
//		return new HashTableIterator<>();
//	}
    
//    public Set<Key> keySet(){
//    	
//    	return null;
//    }
    
    public static void main(String[] args) {
    	HashTable<Integer, String> ht = new HashTable<>();
    	ht.put(1, "Google");
    	ht.put(2, "Microsoft");
    	ht.put(3, "Ibm");
    	ht.put(4, "SAP Labs");
    	ht.put(5, "Cisco");
    	ht.put(6, "NetApp");
    	ht.put(7, "Intuit");
    	ht.put(8, "Oracle");
    	ht.put(9, "Facebook");
    	ht.put(10, "Flipkart");
    	ht.put(11, "Herman");
    	ht.put(12, "Amazon");
    	ht.put(13, "Apple");
    	ht.put(14, "Walmart");
    	ht.put(15, "Oyo");
    	ht.put(16, "MakeMyTrip");
    	ht.put(17, "Yatra");
    	ht.put(18, "OLA");
    	ht.put(19, "Uber");
    	ht.put(20, "ZoomCars");
    	
    	for(int i = 1; i <= 20; i++)
    		System.out.println(ht.get(i));
    	
    	ht.delete(14);
    	System.out.println(ht.get(14));
    }

	
    
}

