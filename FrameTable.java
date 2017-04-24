import java.util.ArrayList;

public class FrameTable{
  private int bufferSpace = 512;
  private int numFrames;
  private Frame[] frames;
  private String algorithm, paging;
  private ArrayList<Process> programs;
  private int swapCount = 0;
  
  //Age is used for FIFO. 
  //Age represents access time for LRU
  private long maxAge = 0;

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
          return;
        }
      }
    }

    //If not, then load it.
    if(!found){
      switch (algorithm){
        case "lru":
          break;
        case "sclru":
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
  
  //Performs a swap given a frame index and a page number.
  //Allows us to not rewrite swap for each algorithm.
  //Also will handle prepaging vs demand paging.
  private void swap(int frameIndex, Process process, int relMemAddress){
    int pageNum = process.getPageNumber(relMemAddress);
    frames[frameIndex] = new Frame(pageNum);
    frames[frameIndex].setAge(maxAge);
    maxAge++;
  }

  public int getSwapCount(){
    return swapCount;
  }
}
