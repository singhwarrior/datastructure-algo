import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation is simulation of a grid of size N*N. Each cell is called 
 * a site. A site can be opened or closed. This can be used to do simulation
 * of such systems where current, water, liquid can flow via open sites and 
 * not from closed sites. But in this solution we identify whether anything
 * can flow from top row to bottom row. From one site to another site can 
 * allow to flow if the upper, left, right is open for it.
 * 
 * @author singh_warrior
 *
 */
public class Percolation {
	
	private Boolean[] sites;
	private final WeightedQuickUnionUF uf;
	private final int count;
	private int openSites;
	
	/**
	 * creates n-by-n grid, with all sites initially blocked. It is represented 
	 * by sites array of size n*n. To check whether two sites are connected with
	 * each other is done by WeightedQuickUnionUF.
	 * 
	 * Here WeightedQuickUnionUF has been created with size of n*n+2. The two 
	 * extra virtual cells are created. One above top row and one below bottom row.
	 * Both virtual nodes are considered to open. The reason to use these two extra
	 * virtual sites is to make the performance faster. If these two virtual sites
	 * are connected to each other that mean grid percolates. If these two virtual 
	 * sites are not there in that case, it needs to be checked each bottom site
	 * is connected to any of the site of top row. In worst case this can take 
	 * T(N) ~ N*N*log N. But on the other hand keeping virtual node can finish it 
	 * within log N. 
	 * 
	 * @param n
	 */
    public Percolation(int n) {
    	if(n < 1)
    		throw new IllegalArgumentException("Grid size cannot be "+n);
    	count = n;
    	openSites = 0;
    	sites = new Boolean[n*n];
    	for(int i = 0; i < n*n; i++) sites[i] = false;
    	uf = new WeightedQuickUnionUF(n*n+2);
    }
    

    /**
     * 
     * Opens a site of a grid. Also checks neighbors to identify
     * whether they are open or not. And if they are open then connect
     * them.
     *  
     * @param row
     * @param col
     */
    public void open(int row, int col){
    	
    	int site_idx = getIndex(row, col);
    	if(!sites[site_idx]) {
        	sites[site_idx] = true;
        	openSites++;
        	
        	if(row == 1)
        		uf.union(site_idx, count*count);
        	else if(isOpen(row-1, col)) 
        		uf.union(site_idx, getIndex(row-1, col));
        	
        	if(row == count)
        		uf.union(site_idx, count*count+1);
        	else if(isOpen(row+1, col)) 
        		uf.union(site_idx, getIndex(row+1, col));

        	if(col != 1 && isOpen(row, col-1))
        		uf.union(site_idx, getIndex(row, col-1));
        	
        	if(col != count && isOpen(row, col+1))
        		uf.union(site_idx, getIndex(row, col+1));    		
    	}
    }

    /**
     * Checks whether a site is open or not
     * 
     * @param row
     * @param col
     * @return {@code true} if site {@code row},{@code col} is open; {@code false} otherwise.
     */
    public boolean isOpen(int row, int col){
    	return sites[getIndex(row, col)];
    }

    /**
     * Checks whether a site is connected to top row cell.
     * 
     * @param row
     * @param col
     * @return {@code true} if the given site represented by {@code row}, {@code col}
     *         is connected to sites of top row; {@code false} otherwise.
     */
    public boolean isFull(int row, int col){
    	int site = getIndex(row, col);
    	return uf.find(site) == uf.find(count*count);
//    	for(int top = 0; top < count; top++) { 
//    		if(site != top && uf.find(site) == uf.find(top))
//    			return true;
//    		if(site == top && isOpen(row, col))
//    			return true;
//    	}
//    	return false;
    }

    /**
     * Gets all open sites in the grid.
     * 
     * @return {@code openSites} in the grid. 
     */
    public int numberOfOpenSites() {
    	return openSites;
    }

    /**
     * Checks whether grid percolates or not
     * 
     * @return {@code true} if grid percolates otherwise {@code false}
     */
    public boolean percolates() {
    	return uf.find(count*count) == uf.find(count*count+1);
//    	for(int i = 1; i <= count; i++) 
//    		if(isFull(count, i))
//    			return true;
//    	return false;
    }
    
    /**
     * Checks whether given index which can be a row or column fall within legal
     * range.
     * 
     * @param index
     * @throws IllegalArgumentException
     */
    private void validate(int index){
    	if(index < 1 || index > count)
    		throw new IllegalArgumentException("Given row or column should be within range 1 and "+(count));
    }
    
    private int getIndex(int row, int col) {
    	validate(row);
    	validate(col);
    	return count*(row-1)+(col-1);
    }
    
	public static void main(String[] args) {
		System.out.println("Hello World");
		int n = StdIn.readInt();
		System.out.println(n);
		
		int t = StdIn.readInt();
		System.out.println(t);
		
		Percolation percolation = new Percolation(n);
//		percolation.open(1, 1);
		System.out.println(percolation.isOpen(2, 1));
		System.out.println(percolation.isFull(1, 1));
//		percolation.open(1, 1);
		System.out.println(percolation.isOpen(1, 1));
		System.out.println(percolation.isOpen(1, 2));
		System.out.println(percolation.isOpen(1, 3));
		System.out.println(percolation.isOpen(1, 4));
		
		
		System.out.println(percolation.numberOfOpenSites());
		
//		while(!percolation.percolates()) {
//			int r = (int)(Math.random()*n);
//			int c = (int)(Math.random()*n);
//			//System.out.println("ROW="+r+",COL="+c);
//			if(!percolation.isOpen(r, c))
//				percolation.open(r, c);			
//		}
//		System.out.println(percolation.numberOfOpenSites());
//		float ratio = (1.0f*percolation.numberOfOpenSites())/(n*n);
//		System.out.println(ratio);
	}
}
