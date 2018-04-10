package birds;

public class DataKey {

    private String birdName;
    private int birdSize;
	// default constructor
        public DataKey(){}
    
	public DataKey(String name, int size) {
	 this.birdName = name;
         this.birdSize = size;
	}
        
        public String getBirdName(){
        return this.birdName;
        }
        
        public int getBirdSize(){
        return this.birdSize;
        }
        
	// other constructors

	/**
	 * Returns 0 if this DataKey is equal to k, returns -1 if this DataKey is smaller
	 * than k, and it returns 1 otherwise. 
     * @param k
     * @return 
	 */
	public int compareTo(DataKey k) {
           if (this.birdName.equalsIgnoreCase(k.birdName) && this.birdSize == k.birdSize){
           return 0;
           }  
           else if (this.birdSize < k.birdSize ||(this.birdSize == k.birdSize && this.birdName.compareTo(k.birdName)<0)){
           return -1;
           }
           else {
           return 1;
           }
	}
}
