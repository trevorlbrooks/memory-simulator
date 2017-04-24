import java.io.*;
import java.util.ArrayList;

public class Driver{

  static String[] validAlgs = {"lru", "fifo", "sclru"};

  public static void main(String args[]){
    if(args.length != 5){
      System.out.println("Calling structure: java Driver programlist commandlist [page size: powers of two up to 16] [algorithm: lru, fifo, sclru] [demand or prepage: d or p]");
    }else{
      //Start checking and loading values.
      File programList = new File(args[0]);
      File commandList = new File(args[1]);
      int pageSize = 1;
      String alg = args[3].toLowerCase();
      String paging = args[4];

      boolean validArgs = true;
      if(!programList.exists()){
        System.out.println("Could not load program list.");
        validArgs = false;
      }


      if(!commandList.exists()){
        System.out.println("Could not load command list.");
        validArgs = false;
      }

      try{
        pageSize = Integer.parseInt(args[2]);
        if(pageSize > 16 || (pageSize % 2 != 0 && pageSize != 1)){
          System.out.println("The page size should be 1, 2, 4, 8, or 16.");
          validArgs = false;
        }
      }catch(NumberFormatException ex){
        System.out.println("Please enter a valid number for a page size.");
        validArgs = false;
      }

      boolean foundAlg = false;
      for(String algName: validAlgs){
        if(algName.equals(alg)){
          foundAlg = true;
          break;
        }
      }
      if(!foundAlg){
        System.out.println("Please enter a valid algorithm. (lru, fifo, sclru)");
        validArgs = false;
      }

      if(!paging.equals("d") && !paging.equals("p")){
        System.out.println("Please enter a valid paging option. d or p");
        validArgs = false;
      }

      //Now that we have checked all the parameters, either continue or stop.
      if(!validArgs){
        System.out.println("Parameter validation failed. Please check your calling syntax.");
        return;
      }
      
      //Set the page size for processes.
      Process.setPageSize(pageSize);

      //Start reading and loading the program list.
      try{
        FileReader programReader = new FileReader(programList);
        BufferedReader programBuffer = new BufferedReader(programReader);
        String inputLine;

        ArrayList<Process> programs = new ArrayList<>();
        while((inputLine = programBuffer.readLine()) != null && inputLine.length() > 0){
          try{
            //Seperate the arguments.
            String[] inputs = inputLine.split(" ");
            programs.add(new Process(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])));
          }catch(NumberFormatException ex){
            System.out.println("Error in programlist file.");
            return;
          }
        }
        for(Process process : programs){
          System.out.println(process);
        }

        //Start reading from command list and perform swaps.
      }catch(IOException ex){
        System.out.println("Error handling file: " + ex);
      }
    } 
  }
}
