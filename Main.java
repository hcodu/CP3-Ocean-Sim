public class Main {
  
  public static void main(String[] args) {
    System.out.println("Testing RLE");

//    Ocean.Cell[][] arr = {{new Ocean.Fish(1), new Ocean.Fish(1), new Ocean.Fish(1)},
//                          {new Ocean.Shark(1), new Ocean.Shark(1), new Ocean.Shark(1)},
//                          {new Ocean.Shark(1), new Ocean.Shark(1), new Ocean.Cell(1)},
//                          {new Ocean.Cell(1), new Ocean.Fish(1), new Ocean.Cell(1)}
//                         };

    int[] rT = {Ocean.FISH, Ocean.EMPTY, Ocean.FISH, Ocean.EMPTY, Ocean.FISH, Ocean.EMPTY, Ocean.SHARK, Ocean.EMPTY, Ocean.FISH, Ocean.EMPTY, Ocean.SHARK, Ocean.EMPTY, Ocean.SHARK};
    int[] rL = {6, 26, 6, 396, 2, 26, 2, 26, 1, 1, 3, 1, 1, 26, 3};

    /*                                   X 0   1   2
                                      Y   ____________
        Width: 4   Height: 3          0  | 0 | 1 | 2 |       X Cord: index % width (works)
        | 0 | 1 | 2 | 3 |... --->     1  | 3 | 4 | 5 |       Y Cord: Round down (index / height) (only of index greater width - 1)
                                      2  | 6 | 7 | 8 |
                                      3  | 9 | 10| 11|
                                         -------------

     */






    Ocean sea = new Ocean(3, 4, 3);
    //sea.setCurrentArr(arr);


    RunLengthEncoding rle1 = new RunLengthEncoding(50, 33, 3, rT, rL);

    //RunLengthEncoding rle = new RunLengthEncoding(sea);

    rle1.toOcean().printArr();


  }

}