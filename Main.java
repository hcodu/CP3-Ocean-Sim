public class Main {
  
  public static void main(String[] args) {
    System.out.println("Testing RLE");

    Ocean.Cell[][] arr = {{new Ocean.Fish(), new Ocean.Fish(), new Ocean.Fish()},
                          {new Ocean.Shark(), new Ocean.Shark(), new Ocean.Shark()},
                          {new Ocean.Shark(), new Ocean.Shark(), new Ocean.Cell()},
                          {new Ocean.Cell(), new Ocean.Cell(), new Ocean.Cell()}
                         };
    int[] rT = {Ocean.FISH, Ocean.SHARK, Ocean.EMPTY};
    int[] rL = {3, 5, 4};

    /*                                   X 0   1   2
                                      Y   _____________
        Width: 4   Height: 3          0  | 0 | 1 | 2 |       X Cord: index % width (works)
        | 0 | 1 | 2 | 3 |... --->     1  | 3 | 4 | 5 |       Y Cord: Round down (index / height) (only of index greater width - 1)
                                      2  | 6 | 7 | 8 |
                                      3  | 9 | 10| 11|
                                         -------------

     */






    Ocean sea = new Ocean(3, 4, 3);
    sea.setCurrentArr(arr);


    RunLengthEncoding rle1 = new RunLengthEncoding(3, 4, 3, rT, rL);

    RunLengthEncoding rle = new RunLengthEncoding(sea);


  }

}