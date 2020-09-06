import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	public static void main(String[] args) {
		if (args.length == 1) {
			RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
			while (!StdIn.isEmpty()) {
				String s = StdIn.readString();
				randomizedQueue.enqueue(s);
			}

			Integer size = Integer.parseInt(args[0]);
			int count = 0;
			while (count < size) {
				StdOut.println(randomizedQueue.dequeue());
				count++;
			}
		}

	}
}
