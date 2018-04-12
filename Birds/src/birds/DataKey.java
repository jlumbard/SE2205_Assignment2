package birds;

public class DataKey {

    private String name;
    private int size;
	// default constructor
        public DataKey(){}
    
	public DataKey(String name, int size) {
         this.size = size;
	 this.name = name;

	}
        public String getBirdName(){
        return name;
        }
        public int getBirdSize(){
        return size;
        }
	// other constructors
        //necessary?
	/**
	 * Returns 0 if this DataKey is equal to k, returns -1 if this DataKey is smaller
	 * than k, and it returns 1 otherwise. 
     * @param k
     * @return 
	 */
	public int compareTo(DataKey k) {
            if (this.size < k.size ||(this.size == k.size && this.name.compareTo(k.name)<0)){
           return -1;
           }
           else if  (this.size == k.size&&(this.name.equalsIgnoreCase(k.name))){
           return 0;
           } 
           else {
           return 1;
           }
	}
}
