# Stack

Stack is a data structure in which data can be only kept from top and accessed from top. Like a stack of plates. 

So an element will be always added to Stack by **push(element)** and an element will be accessed by it using **pop()** operation. Hence we can only access last element pushed to a stack. That is why stack is called LIFO(Last In First Out).

# Stack ADT

```Java
public interface Stack<T> {
	public void push(T element) throws StackFullException;
	public T pop() throws StackEmptyException;
	public Boolean isEmpty();
	public Boolean isFull();
	public T top() throws StackEmptyException;
}
```
# Fixed Size Stack

All operations of FixedSizeStack implementation can be done in constant time. There is a pointer top which is always pointing to element currently on top of stack, hence push(element), pop() and top() all will take constant time O(1).

# Linked List Based Stack
All operations of LinkedListBasedStack implementation can be done in constant time. But it will take more space over a period of time.

There is LinkedList in which head always represent top of the stack.

# Array Based Stack
Average time for all operations of ArrayBasedStack implementation constant time but in worst case it can be O(N). 

Elements are kept in Array which keep on growing or getting reduced in size by twice or by half respectively. And elements in that can vary between 3N/4 to N. 


   
