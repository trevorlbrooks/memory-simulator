public class Frame{
  private int page;
  private long age;
  private long lastAccess;
  private boolean refBit;

  Frame(int page){
    this.page = page;
  }
  
  public int getPage(){
    return page;
  }

  public long getAge(){
    return age;  
  }

  public void setAge(long age){
    this.age = age;
  }

  public long getLastAccess(){
    return lastAccess;  
  }

  public void setLastAccess(long lastAccess){
    this.lastAccess = lastAccess;
  }
  
  public boolean getRefBit(){
    return refBit;
  }

  public void setRefBit(boolean refBit){
    this.refBit = refBit;
  }

}
