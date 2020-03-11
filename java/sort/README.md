###Quick Sort
A sorting algorithm which is a divide and conquer based algorithm. It is in place algorithm.

As a simple Quick Sort see following method in SortUtil:

```java
	public static <T extends Comparable<T>> void quickSort(List<T> in, int from, int to) {
			if (from >= 0 && to >= 0 && from < to) {
				int pivot = partition(in, from, to);
				quickSort(in, from, pivot - 1);
				quickSort(in, pivot + 1, to);
			}
		}
```

Here partition element is not picked randomly as shown below.

```java
	private static <T extends Comparable<T>> int partition(List<T> in, int from, int to) {
		int pivotPosition = (from + to)/2;
		T pivotElement = in.get(pivotPosition);
		while(from < to) {
			while(in.get(from).compareTo(pivotElement) == -1)
				from++;
			while(in.get(to).compareTo(pivotElement) == 1)
				to--;
			swap(in, from, to);
		}
		return from;
	}
```

Since the partition is not picked randomly hence the performance of algorithm depends upon the order in which
input is given. **Hence best case is O(n\*log2(n)) and worst case is O(n^2)**. See below:

See image quick_sort1.jpg

See image quick_sort2.jpg

###Randomized Quick Sort

In randomized quick sort the partition element chosen is not always same position and it is totally random due 
to which expectation comes as O(nlog<sub>2</sub>n) in any kind of input.

See image quick_sort_randomized1.jpg

See image quick_sort_randomized11.jpg

See image quick_sort_randomized2.jpg

See image quick_sort_randomized3.jpg

See image quick_sort_randomized4.jpg
