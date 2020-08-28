import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private final double[] xs;
	private final int T;
	
	/**
	 * Constructor to initialize percolation object for 
	 * n trials and get the threshold when grid starts to
	 * percolate.
	 *  
	 * @param n
	 * @param trials
	 * @throws IllegalAccessException 
	 */
    public PercolationStats(int n, int trials){
    	
    	if(n < 1)
    		throw new IllegalArgumentException("Grid size cannot be "+n);
    	
    	T = trials;
    	xs = new double[T];
    	for(int i=0; i<T; i++) {
        	Percolation percolation = new Percolation(n);
        	while(!percolation.percolates()) {
    			int r = StdRandom.uniform(1, n+1);
    			int c = StdRandom.uniform(1, n+1);
        		if(!percolation.isOpen(r, c))
        			percolation.open(r, c);			    				
    		}
        	double ratio = (1.0*percolation.numberOfOpenSites())/(n*n);
        	xs[i] = ratio;
    	}
    }

    /**
     * @return {@code mean} of ratios when grid starts to percolate. 
     */
    public double mean() {
    	return StdStats.mean(xs);
    }

    public double stddev() {
    	return StdStats.stddev(xs);
    }

    public double confidenceLo() {
    	return mean() - 1.96*stddev()/Math.sqrt(T);
    }

    public double confidenceHi() {
    	return mean() + 1.96*stddev()/Math.sqrt(T);
    }

	public static void main(String[] args) {
		if(args.length == 2) {
			int n = Integer.parseInt(args[0]);
			int t = Integer.parseInt(args[1]);
			PercolationStats percolationStats = new PercolationStats(n, t);
			System.out.println("mean                    = "+percolationStats.mean());
			System.out.println("stddev                  = "+percolationStats.stddev());
			System.out.println("95% confidence interval = ["
								+percolationStats.confidenceLo()+","+percolationStats.confidenceHi()
								+"]");
		}
	}
}
