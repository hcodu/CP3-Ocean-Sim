public class Main {
  
  public static void main(String[] args) {
    System.out.println("Testing RLE");

    Ocean.Cell[][] arr = {{new Ocean.Cell(), new Ocean.Cell(), new Ocean.Fish()},
                          {new Ocean.Shark(), new Ocean.Shark(), new Ocean.Cell()},
                          {new Ocean.Cell(), new Ocean.Cell(), new Ocean.Fish()}
                         };
    int[] arr1 = {Ocean.EMPTY, Ocean.FISH, Ocean.SHARK, Ocean.EMPTY, Ocean.FISH};
    int[] arr2 = {2, 1, 2, 3 , 1};


    Ocean sea = new Ocean(3, 3, 3);
    RunLengthEncoding runs = new RunLengthEncoding(3,3, 3, arr1, arr2);

    System.out.println(runs.nextRun()[0]);
    System.out.println(runs.nextRun()[0]);
    System.out.println(runs.nextRun()[0]);
    System.out.println(runs.nextRun()[0]);
    System.out.println(runs.nextRun()[0]);
    System.out.println(runs.nextRun()[1]); //Should be null
    System.out.println();

    runs.restartRuns();
    System.out.println(runs.nextRun()[0]);
    System.out.println(runs.nextRun()[0]);
    System.out.println(runs.nextRun()[0]);
    System.out.println(runs.nextRun()[0]);
    System.out.println(runs.nextRun()[0]);
    System.out.println(runs.nextRun()[1]); //Should be null
    System.out.println();

  }

}