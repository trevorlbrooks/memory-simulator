public class Process{
  //Track the global max page number.
  private static int maxPage = 0;
  private static int PAGE_SIZE;

  private int pID, memSize, pageStart;
  
  Process(int pID, int memSize){
    this.pID = pID;
    this.memSize = memSize;
    pageStart = maxPage;
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

  static public void setPageSize(int pageSize){
    PAGE_SIZE = pageSize;
  }
  
  public String toString(){
   return pID + " " + memSize + " " + pageStart;
  }
  
  public int getNextPageNumber(int relMemAddress){
    if((getPageNumber(relMemAddress) + 1) * PAGE_SIZE < memSize){
      return getPageNumber(relMemAddress) + 1;
    }else{
      return -1;
    }
  }

  public int getNextPageAddress(int relMemAddress){
    if((getPageNumber(relMemAddress) + 1) * PAGE_SIZE < memSize){
      return (getPageNumber(relMemAddress) + 1)* PAGE_SIZE;   
    }else{
      return -1;
    }
  }

}
