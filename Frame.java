public class Frame{
  private int page;
  private long age;


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

}
