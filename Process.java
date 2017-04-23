public class Process{
  //Track the global max page number.
  private static int maxPage = 0;
  private static final int PAGE_SIZE = 4;

  private int pID, memSize, pageStart;

  Process(int pID, int memSize){
    this.pID = pID;
    this.memSize = memSize;
    pageStart = maxPage + (memSize / PAGE_SIZE);
    maxPage += memSize/PAGE_SIZE;
    if(memSize % PAGE_SIZE != 0){
      maxPage++;
    }
  }
  
  public int get_pID(){
    return pID;
  }
   
  public int getMemSize(){
    return pID;
  }
  
  public int getPageStart(){
    return pageStart;
  }
 
  public int getPageNumber(int relMemLocation){
    if(relMemLocation <= memSize){
      return pageStart + (relMemLocation / PAGE_SIZE);
    }else{
      return -1;
    }
  } 
  
  public String toString(){
   return pID + " " + memSize + " " + pageStart + "\n";
  }

}
