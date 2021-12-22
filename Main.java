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


    Ocean sea = new Ocean(3, 3, 3);
    RunLengthEncoding rle = new RunLengthEncoding(3,3, 3, rT, rL);

   System.out.println("TEST: " + rle.wrap(6)[0] + " " + rle.wrap(5)[1]);;


  }

}