package birds;
public class BirdRecord{

    private DataKey key;
    private String abt;
    private String snd;
    private String img;

    public BirdRecord(DataKey k, String abt, String snd, String img) {
       this.key = k;
       this.abt = abt;
       this.snd = snd;
       this.img = img;
    }

     // Other constructors
    public DataKey getDataKey(){
        return key;
    }
    public String getAbout(){
        return abt;
    }
    
    public String getSound(){
        return snd;
    }
    
    public String getImage(){
        return img;
    }
}



