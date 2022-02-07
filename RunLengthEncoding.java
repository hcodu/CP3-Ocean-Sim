/* RunLengthEncoding.java */

import DoublyLinkedList.DoublyLinkedList;
import DoublyLinkedList.DListNode;

import java.util.ArrayList;


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
  private final int width;
  private final int height;
  private final int starveTime;
  private int currentHunger;
  private int counter = 0;
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
    runs = new DoublyLinkedList();
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
        runs.insertBack(runLengths[c], Ocean.EMPTY, 0);
      }
      else if(runTypes[c] == Ocean.FISH) {
        runs.insertBack(runLengths[c], Ocean.FISH, 0);
      }
      else if(runTypes[c] == Ocean.SHARK) {
        runs.insertBack(runLengths[c], Ocean.SHARK, 0);
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
    counter++;
    System.out.print("run #" + (counter - 1) + " " + curr.getType());

      System.out.print(" of quantity " + curr.getQuantity() + "\n");


      int[] arr = new int[2];

      arr[0] = curr.getType();
      arr[1] = curr.getQuantity();
      curr = curr.getNext();

      if(arr[1] == 0) {
        return null;
      }

      return arr;

  }

  /**
   *  toOcean() converts a run-length encoding of an ocean into an Ocean
   *  object.  You will need to implement the three-parameter addShark method
   *  in the Ocean class for this method's use.
   *  @return the Ocean represented by a run-length encoding.
   */

  public Ocean toOcean() {
    Ocean sea = new Ocean(width, height, starveTime); //Creates new ocean from 3 parameter constructor
    int rleIndexCounter = 0; //Indexes the count of cells in the RLE

    DListNode currNode = runs.front();


    for(int c = 0; c < runs.length(); c++) { //Iterates through every run in the DLL of runs
       //Takes next cell from current node of DLL

      int q = currNode.getQuantity(); //Stores the quantity of the run


      for(int i = 0; i < q; i++) {
        int[] cords = wrap(rleIndexCounter); //Converts the RLE cord to an arr cords
        if(currNode.getType() == Ocean.SHARK) {
          int hunger = currNode.getHunger();
          sea.addShark(cords[1], cords[0], hunger); //Adds shark using the arr cords
          //System.out.println("RLE: " + rleIndexCounter + " XY: " + cords[1] + "," + cords[0] + " Added a: " + sea.cellContents(cords[1], cords[0]));
        }
        else if(currNode.getType() == Ocean.FISH) {
          sea.addFish(cords[1], cords[0]); //Adds fish using the arr cords
          //System.out.println("RLE: " + rleIndexCounter + " XY: " + cords[1] + "," + cords[0] + " Added a: " + sea.cellContents(cords[1], cords[0]));
        }

        rleIndexCounter++; //Increments the total run counter
      }

      currNode = currNode.getNext(); //Increments the current node of the DLL
    }

    return sea;
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
    sea.printArr();
    Ocean.Cell[][] arr = sea.getCurrentArr();
    int c = 0;

    for(int y = 0; y < arr.length; y++) {
      for(int x = 0; x < arr[0].length; x++) {
        //System.out.println("Count: " + c + " " + arr[y][x]);
        //c++;
      }
    }

    height = sea.height();
    width = sea.width();
    starveTime = sea.starveTime();
    runs = new DoublyLinkedList();

    Ocean.Cell[][] oceanArr = sea.getCurrentArr();
    Ocean.Cell[] rleArr = new Ocean.Cell[oceanArr.length * oceanArr[0].length]; //Makes new 1d array with the length of the amount of cells from the ocean
    int rleCounter = 0;

    //Converts 2d array of cells to 1d array
    for(int y = 0; y < oceanArr.length; y++) {
      for(int x = 0; x < oceanArr[0].length; x++) {
        rleArr[rleCounter] = oceanArr[y][x];
        rleCounter++;
      }
    }

    //Instantiates 2 array lists to dynamically create size for rT and rL
    ArrayList<Integer> rTList = new ArrayList<Integer>();
    ArrayList<Integer> rLList = new ArrayList<Integer>();

    int runCounter = 0;
    int sameCounter = 0;
    int referenceType = rleArr[0].getType();

    //Converts dll to rT and rL to run through 5 param constructor logic
    for(int i = 0; i < rleArr.length; i++) {

      if(rleArr[i].getType() == referenceType) { //If the current type is equal to the reference, increment sameCounter
        sameCounter++;
      }
      else { //If not the same as reference type...
        rTList.add(rleArr[i - 1].getType()); //Add the previous type to rTList
        rLList.add(sameCounter); //Add the previous quantity to rLList

        //System.out.println("Added a " + rleArr[i - 1].getType() + " of quantity " + sameCounter);
        sameCounter = 1; //Reset same counter to 1
        referenceType = rleArr[i].getType(); //Change the reference type to the current type
      }

      //System.out.println("Index: " + i + "Reference: " + referenceType + " \t Current: "  + rleArr[i].getType() + "\t Same (+1): " + sameCounter);
      if(i == rleArr.length - 1) { //If on last element
        rTList.add(rleArr[i].getType()); // Adds the last type which is the reference type
        rLList.add(sameCounter); //Adds the quantity of the last element
      }
    }

    //Try doing this in a for loop??
    //Converts the array lists to arrays
    int[] rT = rTList.stream().mapToInt(i -> i).toArray();
    int[] rL = rLList.stream().mapToInt(i -> i).toArray();


    //Something is wrong with the built in tester



    new RunLengthEncoding(width, height, starveTime, rT, rL);

//    for(int i = 0; i < rT.length; i++) {
//      System.out.println("run #" + i + " " + rT[i] + ", " + rL[i]);
//    }


    first = runs.front();
    first1 = runs.front();
    curr = runs.front();


    printRunLengthEncoding(runs);

    int[] runArr = getRun(81);
    if(runArr != null) {
      System.out.println(runArr[0] + " " + runArr[1]);
    }

    //check(runs);
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
    Ocean newOcean = this.toOcean();
    newOcean.addShark(x, y, currentHunger);

    check(runs);
  }

  /** wrap() converts RLE index to array cordinates.
   * @param i is the RLE Index (element count).
   * @return arr is the 2 element array with the cordinates {Y, X}.
   */
  public int[] wrap(int i) { //0 is y cord, 1 is x cord
    int arr[] = new int[2];
    int c = 0;

    while(i > width) { //Y CORD: Decrements rows until on the first row, counter is the y cord
      c++;
      i = i - width;
    }
    arr[0] = c;

    if(i % width == 0) {
      arr[0] = c + 1; //Y CORD (redundancy)
    }

    arr[1] = i % width; //X CORD

    if(i == 0) {
      arr[0] = 0;
      arr[1] = 0;
    }

    return arr;
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same contents, or if the sum of all run
   *  lengths does not equal the number of cells in the ocean.
   */
  private void check(DoublyLinkedList dll) {
    //System.out.println(this.height + " " + this.width);
    DListNode currNode = dll.front();
    DListNode nextNode = currNode.getNext();


    int counter = 0;
    boolean correct = true;

    //Checks to ensure the proper amount of runs and that no two cells are correct
    for(int c = 0; c < dll.length(); c++) {
      currNode = currNode.getNext(); //Increments the node in runs


      counter = counter + currNode.getQuantity();

      if(nextNode != null) {
        if (currNode.getType() == currNode.getType()) { //Ensures that no two runs are the same
          correct = false;
        }
      }
    }

    if(counter != (width * height)) { //Ensures that the RLE has proper amount of runs
      System.out.println("There are " + runs.length() + " runs. There should be " + (this.width * this.height));
      correct = false;
    }

    if(runs == null) {
      correct = false;
    }

    System.out.println("RLE is correct: " + correct);

  }

  public void printRunLengthEncoding(DoublyLinkedList dll) {
    //System.out.println(dll);
    DListNode currNode = dll.front();
    String result = "|";


    //Converts the Run Length Encoding to a String
    for(int c = 0; c < dll.length(); c++) {

      if(currNode.getType() == Ocean.FISH) {
        result = result + "F" + currNode.getQuantity() + "|";
      }
      if(currNode.getType() == Ocean.SHARK) {
        result = result + "S" + currNode.getHunger() + "," + currNode.getQuantity() + "|";
      }
      if(currNode.getType() == Ocean.EMPTY) {
        result = result + "." + currNode.getQuantity() + "|";
      }
      currNode = currNode.getNext();

    }
    System.out.println(result);

  }


  /** getRun() returns the 1d array of a "run" from nextRun() of a specific
   * rleIndex of the RLE. The first element of the array is the type and the second
   * is the quantity. Runs are indexed from 0
   * */

  public int[] getRun(int r) {
    int[] arr = new int[2];
    DListNode run = runs.front();
    for(int i = 0; i < r; i++) {
      arr = nextRun();
    }
    arr = nextRun();

    restartRuns();

    return arr;
  }

}