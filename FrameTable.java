import java.util.ArrayList;

public class FrameTable{
  private int bufferSpace = 512;
  private int numFrames;
  private Frame[] frames;
  private String algorithm, paging;
  private ArrayList<Process> programs;
  private int swapCount = 0;
  
  //Age is used for FIFO.  
  private long maxAge = 0;

  //Access time is used for LRU
  private long maxAccessTime = 0;

  //Set current index for SCLRU
  private int currIdx = 0;

  FrameTable(int bufferSpace, int pageSize, String algorithm, 
      String paging, ArrayList<Process> programs){
    this.bufferSpace = bufferSpace;
    numFrames = bufferSpace / pageSize;
    frames = new Frame[numFrames];
    this.algorithm = algorithm;
    this.paging = paging;
    this.programs = programs;
  }

  public void loadMem(int program, int relMemAddress){
    boolean found = false;

    //Try to directly load page
    Process process = programs.get(program);

    if(process.get_pID() != program){
      for(Process proc : programs){
        if(proc.get_pID() == program){
          process = proc;
          break;
        }
      }
    }

    if(process.get_pID() != program){
      System.out.println("----CRITICAL ERROR: CANNOT FIND PROGRAM IN loadMem----");
      return;
    }

    int pageNum = process.getPageNumber(relMemAddress);

    //Check if memory is already loaded
    for(Frame frame: frames){
      if(frame != null){
        if(frame.getPage() == pageNum){
          found = true;
          
          //Track access for lru
          frame.setLastAccess(maxAccessTime);
          maxAccessTime++;
          
          //Track access for sclru
          frame.setRefBit(true);

          return;
        }
      }
    }

    //If not, then load it.
    if(!found){
      switch (algorithm){
        case "lru":
          lru(process, relMemAddress);
          break;
        case "sclru":
          sclru(process, relMemAddress);
          break;
        case "fifo":
          fifo(process, relMemAddress);
          break;
        default:
          System.out.println("Broken algorithm call.");
          break;
      } 
      swapCount++;
    }
  }

  private void fifo(Process process, int relMemAddress){
    long frameAge = Long.MAX_VALUE;
    int frameIndex = -1;
    for(int i = 0; i < numFrames; i++){
      if(frames[i] == null){
        frameIndex = i;
        break;
      }else if(frames[i].getAge() < frameAge){
        frameAge = frames[i].getAge();
        frameIndex = i;
      }
    }
    
    swap(frameIndex, process, relMemAddress);
    
  }

  private void lru(Process process, int relMemAddress){
    long frameAccess = Long.MAX_VALUE;
    int frameIndex = -1;
    for(int i = 0; i < numFrames; i++){
      if(frames[i] == null){
        frameIndex = i;
        break;
      }else if(frames[i].getLastAccess() < frameAccess){
        frameAccess = frames[i].getLastAccess();
        frameIndex = i;
      }
    }

    swap(frameIndex, process, relMemAddress);
  }

  private void sclru(Process process, int relMemAddress){
    boolean found = false;
    int replIdx = -1;

    while(!found){
      if(frames[currIdx] == null){
        replIdx = currIdx;
        found = true;
      }else if(!frames[currIdx].getRefBit()){
        replIdx = currIdx;
        found = true; 
      }else if(frames[currIdx].getRefBit()){
        frames[currIdx].setRefBit(false);
      } 
     
      currIdx++;
      if(currIdx >= numFrames){
        currIdx = 0;
      }
    }

    swap(replIdx, process, relMemAddress);
  }
  
  //Performs a swap given a frame index and a page number.
  //Allows us to not rewrite swap for each algorithm.
  //Also will handle prepaging vs demand paging.
  private void swap(int frameIndex, Process process, int relMemAddress){
    int pageNum = process.getPageNumber(relMemAddress);
    frames[frameIndex] = new Frame(pageNum);
    
    //Set age for fifo
    frames[frameIndex].setAge(maxAge);
    maxAge++;

    //Set access time for LRU
    frames[frameIndex].setLastAccess(maxAccessTime);
    maxAccessTime++;

    //Set reference bit for SCLRU
    frames[frameIndex].setRefBit(false);
  }

  public int getSwapCount(){
    return swapCount;
  }
}
