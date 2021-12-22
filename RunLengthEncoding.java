/* RunLengthEncoding.java */

import DoublyLinkedList.DoublyLinkedList;
import DoublyLinkedList.DListNode;


/**
 *  The RunLengthEncoding class defines an object that run-length encodes an
 *  Ocean object.  Descriptions of the methods you must implement appear below.
 *  They include constructors of the form
 *
 *      public RunLengthEncoding(int i, int j, int starveTime);
 *      public RunLengthEncoding(int i, int j, int starveTime,
 *                               int[] runTypes, int[] runLengths) {
 *      public RunLengthEncoding(Ocean ocean) {
 *
 *  that create a run-length encoding of an Ocean having width i and height j,
 *  in which sharks starve after starveTime timesteps.
 *
 *  The first constructor creates a run-length encoding of an Ocean in which
 *  every cell is empty.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts an Ocean object into a run-length encoding of that object.
 *
 *  See the README file accompanying this project for additional details.
 */

public class RunLengthEncoding {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
  public static DoublyLinkedList runs;
  private DListNode first, first1, curr;
  private int width, height, starveTime, counter = 0, currentHunger;
  private Ocean ocean;

  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with three parameters) is a constructor that creates
   *  a run-length encoding of an empty ocean having width i and height j,
   *  in which sharks starve after starveTime timesteps.
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param "starveTime" is the number of timesteps sharks survive without food.
   */

  public RunLengthEncoding(int i, int j, int sT) {
    DoublyLinkedList runs = new DoublyLinkedList();
    starveTime = sT;
    width = i;
    height = j;
    // Your solution here.
  }

  /**
   *  RunLengthEncoding() (with five parameters) is a constructor that creates
   *  a run-length encoding of an ocean having width i and height j, in which
   *  sharks starve after starveTime timesteps.  The runs of the run-length
   *  encoding are taken from two input arrays.  Run i has length runLengths[i]
   *  and species runTypes[i].
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param sT is the number of timesteps sharks survive without food.
   *  @param runTypes is an array that represents the species represented by
   *         each run.  Each element of runTypes is Ocean.EMPTY, Ocean.FISH,
   *         or Ocean.SHARK.  Any run of sharks is treated as a run of newborn
   *         sharks (which are equivalent to sharks that have just eaten).
   *  @param runLengths is an array that represents the length of each run.
   *         The sum of all elements of the runLengths array should be i * j.
   */

  public RunLengthEncoding(int i, int j, int sT, int[] runTypes, int[] runLengths) {
    runs = new DoublyLinkedList();
    starveTime = sT;
    width = i;
    height = j;

    for(int c = 0; c < runTypes.length; c++) {
      if(runTypes[c] == Ocean.EMPTY) {
        runs.insertBack(new Ocean.Cell(runLengths[c]));
      }
      else if(runTypes[c] == Ocean.FISH) {
        runs.insertBack(new Ocean.Fish(runLengths[c]));
      }
      else if(runTypes[c] == Ocean.SHARK) {
        runs.insertBack(new Ocean.Shark(runLengths[c]));
      }
    }

    first = runs.front();
    first1 = runs.front();
    curr = runs.front();

    printRunLengthEncoding(runs);
    //check(runs);





    // Your solution here.
  }

  /**
   *  restartRuns() and nextRun() are two methods that work together to return
   *  all the runs in the run-length encoding, one by one.  Each time
   *  nextRun() is invoked, it returns a different run (represented as an
   *  array of two ints), until every run has been returned.  The first time
   *  nextRun() is invoked, it returns the first run in the encoding, which
   *  contains cell (0, 0).  After every run has been returned, nextRun()
   *  returns null, which lets the calling program know that there are no more
   *  runs in the encoding.
   *
   *  The restartRuns() method resets the enumeration, so that nextRun() will
   *  once again enumerate all the runs as if nextRun() were being invoked for
   *  the first time.
   *
   *  (Note:  Don't worry about what might happen if nextRun() is interleaved
   *  with addFish() or addShark(); it won't happen.)
   */

  /**
   *  restartRuns() resets the enumeration as described above, so that
   *  nextRun() will enumerate all the runs from the beginning.
   */

  public void restartRuns() {
    // Your solution here.

    curr = first1;
    counter = 0;
  }

  /**
   *  nextRun() returns the next run in the enumeration, as described above.
   *  If the runs have been exhausted, it returns null.  The return value is
   *  an array of two ints (constructed here), representing the type and the
   *  size of the run, in that order.
   *  @return the next run in the enumeration, represented by an array of
   *          two ints.  The int at index zero indicates the run type
   *          (Ocean.EMPTY, Ocean.SHARK, or Ocean.FISH).  The int at index one
   *          indicates the run length (which must be at least 1).
   */

  public int[] nextRun() {
    // Replace the following line with your solution.

    counter++;
   // System.out.println("run #" + counter + " " + curr.getValue());
    Ocean.Cell currCell = (Ocean.Cell) curr.getValue();
    curr = curr.getNext();


    if(currCell != null && curr != null) {
      int[] arr = new int[2];

      arr[0] = currCell.getType();
      arr[1] = currCell.quantity;

      return arr;
    }
    else {
      return null;
    }

  }

  /**
   *  toOcean() converts a run-length encoding of an ocean into an Ocean
   *  object.  You will need to implement the three-parameter addShark method
   *  in the Ocean class for this method's use.
   *  @return the Ocean represented by a run-length encoding.
   */

  public Ocean toOcean() {
    ocean = new Ocean(width, height, starveTime);
    int rleIndexCounter = 0;

    Ocean.Cell[][] arr = new Ocean.Cell[height][width];

    for(int h = 0; h < height; h++) { //Sets all cells to empty cells
      for(int w = 0; w < width; w++) {
        arr[h][w] = new Ocean.Cell();
      }
    }

    ocean.setCurrentArr(arr);

    DListNode currNode = runs.front(); //FIX THIS: runs (dll) is null
    Ocean.Cell currCell = (Ocean.Cell) currNode.getValue();

    for(int c = 0; c < runs.length(); c++) { //Iterates through every run in the DLL of runs
      currCell = (Ocean.Cell) currNode.getValue();
      currNode = currNode.getNext();

      int q = currCell.quantity;

      if(currCell.getType() == Ocean.SHARK) {
        currentHunger = currCell.getHunger();
      }
      else {
        //currentHunger = (Integer) null;
      }

      for(int i = 0; i < q; q++) {

      }



    }



    return ocean;
  }

  /**
   *  The following method is required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of an input Ocean.  You will need to implement
   *  the sharkFeeding method in the Ocean class for this constructor's use.
   *  @param sea is the ocean to encode.
   */

  public RunLengthEncoding(Ocean sea) {
    // Your solution here, but you should probably leave the following line
    //   at the end.


    check(runs);
  }

  /**
   *  The following methods are required for Part IV.
   */

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.  The final run-length
   *  encoding should be compressed as much as possible; there should not be
   *  two consecutive runs of sharks with the same degree of hunger.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */

  public void addFish(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    ocean.addFish(x, y);
    check(runs);
  }

  public int[] wrap(int i) {
    int arr[] = new int[2];
    arr[0] = (int) Math.floor(i - 1 / height); //Y CORD
    arr[1] = (i - 1) % width; //X CORD
    return arr;
  }


  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  The final run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs of sharks with the same degree
   *  of hunger.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    ocean.addShark(x, y, currentHunger);
    check(runs);
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same contents, or if the sum of all run
   *  lengths does not equal the number of cells in the ocean.
   */

  private void check(DoublyLinkedList dll) {
    DListNode currNode = dll.front();
    DListNode nextNode = currNode.getNext();

    Ocean.Cell currCell = (Ocean.Cell) currNode.getValue();
    Ocean.Cell nextCell = (Ocean.Cell) currNode.getNext().getValue();
    int counter = 0;
    boolean correct = true;

    //Checks to ensure the proper amount of runs and that no two cells are correct
    for(int c = 0; c < dll.length(); c++) {
      currCell = (Ocean.Cell) currNode.getValue();
      currNode = currNode.getNext(); //Increments the node in runs

      nextCell = (Ocean.Cell) nextNode.getValue();
      nextNode = nextNode.getNext(); //Increments the node in runs

      counter = counter + currCell.quantity;

      if(nextCell != null) {
        if (currCell.getType() == nextCell.getType()) { //Ensures that no two runs are the same
          correct = false;
        }
      }
    }

    if(counter != (width * height)) { //Ensures that the RLE has proper ammount of runs
      System.out.println("There are " + runs.length() + " runs. There should be " + (this.width * this.height));
      correct = false;
    }

    if(runs == null) {
      correct = false;
    }

    System.out.println("RLE is correct: " + correct);

  }

  public DoublyLinkedList getRuns() {
    return runs;
  }

  public void printRunLengthEncoding(DoublyLinkedList dll) {
    //System.out.println(dll);
    DListNode currNode = dll.front();
    Ocean.Cell currCell = (Ocean.Cell) currNode.getValue();
    String result = "|";


    //Converts the Run Length Encoding to a String
    for(int c = 0; c < dll.length(); c++) {
      currCell = (Ocean.Cell) currNode.getValue();
      currNode = currNode.getNext();

      if(currCell.getType() == Ocean.FISH) {
        result = result + "F" + currCell.quantity + "|";
      }
      if(currCell.getType() == Ocean.SHARK) {
        result = result + "S" + currCell.getHunger() + "," + currCell.quantity + "|";
      }
      if(currCell.getType() == Ocean.EMPTY) {
        result = result + "." + currCell.quantity + "|";
      }
    }
    System.out.println(result);

  }

}
