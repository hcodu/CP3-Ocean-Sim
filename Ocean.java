/* Ocean.java */

/**
 *  The Ocean class defines an object that models an ocean full of sharks and
 *  fish.  Descriptions of the methods you must implement appear below.  They
 *  include a constructor of the form
 *
 *      public Ocean(int i, int j, int starveTime);
 *
 *  that creates an empty ocean having width i and height j, in which sharks
 *  starve after starveTime timesteps.
 *
 *  See the README file accompanying this project for additional details.
 */

public class Ocean {

  /**
   *  Do not rename these constants.  WARNING:  if you change the numbers, you
   *  will need to recompile Test4.java.  Failure to do so will give you a very
   *  hard-to-find bug.
   */

  public final static int EMPTY = 0;
  public final static int SHARK = 1;
  public final static int FISH = 2;

  /**
   *  Define any variables associated with an Ocean object here.  These
   *  variables MUST be private.
   */
  private int width;
  private int height;
  private int starveTime;
  private Cell[][] currentArr;
  private Cell[][] nextArr;

//  /**
//   *  The following methods are required for Part I.
//   */
//
//  /**
//   *  Ocean() is a constructor that creates an empty ocean having width i and
//   *  height j, in which sharks starve after starveTime timesteps.
//   *  @param i is the width of the ocean.
//   *  @param j is the height of the ocean.
//   *  @param starveTime is the number of timesteps sharks survive without food.
//   */

  public Ocean(int i, int j, int sT) {
    // Your solution here.
    width = i;
    height = j;
    starveTime = sT;
    currentArr = new Cell[width][height];
    nextArr = new Cell[width][height];


    for(int h = 0; h < height; h++) {
      for(int w = 0; w < width; w++) {
        currentArr[w][h] = new Cell();
      }
    }
  }

  /**
   *  width() returns the width of an Ocean object.
   *  @return the width of the ocean.
   */

  public int width() {
    // Replace the following line with your solution.
    return width;
  }

  /**
   *  height() returns the height of an Ocean object.
   *  @return the height of the ocean.
   */

  public int height() {
    // Replace the following line with your solution.
    return height;
  }

  /**
   *  starveTime() returns the number of timesteps sharks survive without food.
   *  @return the number of timesteps sharks survive without food.
   */

  public int starveTime() {
    // Replace the following line with your solution.
    return starveTime;
  }

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */

  public void addFish(int x, int y) {
    // Your solution here.
    if(currentArr[x][y].getType() == EMPTY) {
      currentArr[x][y] = new Fish();
    }

  }

  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
    // Your solution here.
    if(currentArr[x][y].getType() == EMPTY) {
      currentArr[x][y] =  new Shark();
    }
  }

  /**
   *  cellContents() returns EMPTY if cell (x, y) is empty, FISH if it contains
   *  a fish, and SHARK if it contains a shark.
   *  @param x is the x-coordinate of the cell whose contents are queried.
   *  @param y is the y-coordinate of the cell whose contents are queried.
   */

  public int cellContents(int x, int y) {
    return currentArr[x][y].getType();
  }

  /**
   *  timeStep() performs a simulation timestep as described in README.
   *  @return an ocean representing the elapse of one timestep.
   */

  public Ocean timeStep() {
    // Replace the following line with your solution.
    nextArr = currentArr;

    // 1) If a cell contains a shark, and any of its neighbors is a fish, then the
    // shark eats during the timestep, and it remains in the cell at the end of the
    // timestep with its hunger completely gone.  (We may have multiple sharks sharing
    // the same fish.  This is fine; miraculously, they all get enough to eat.)

//    for(int h = 0; h < currentArr.length; h++) { //Traverse entire array
//      for(int w = 0; w < currentArr[0].length; w++) {
//        if(currentArr[w][h].getType() == SHARK) {
//
//        }
//      }
//    }

    // 2) If a cell contains a shark, and none of its neighbors is a fish, it gets
    // hungrier during the timestep.  If this timestep is the (starveTime + 1)th
    // consecutive timestep the shark has gone through without eating, then the shark
    // dies (disappears).  Otherwise, it remains in the cell.  An example
    // demonstrating this rule appears below.

    // 3) If a cell contains a fish, and all of its neighbors are either empty or are
    // other fish, then the fish stays where it is.

    // 4) If a cell contains a fish, and one of its neighbors is a shark, then the
    // fish is eaten by a shark, and therefore disappears.

    // 5) If a cell contains a fish, and two or more of its neighbors are sharks, then
    // a new shark is born in that cell.  Sharks are well-fed at birth; _after_ they
    // are born, they can survive an additional starveTime timesteps without eating.
    // (But they will die at the end of starveTime + 1 consecutive timesteps without
    // eating.)

    // 6) If a cell is empty, and fewer than two of its neighbors are fish, then the
    // cell remains empty.

    // 7) If a cell is empty, at least two of its neighbors are fish, and at most one
    // of its neighbors is a shark, then a new fish is born in that cell.
    for(int i = 0; i < currentArr.length; i++) {
      for(int j = 0; j < currentArr[0].length; j++) {
        if(currentArr[i][j].getType() == FISH) {
          int fish = checkFish(currentArr, i, j);
        }
      }
    }



    // 8) If a cell is empty, at least two of its neighbors are fish, and at least two
    // of its neighbors are sharks, then a new shark is born in that cell.  (The new
    // shark is well-fed at birth, even though it hasn't eaten a fish yet.)






    Ocean newOcean = new Ocean(width, height, starveTime);
    newOcean.currentArr = nextArr;


    return newOcean;
  }

  public int checkFish(Cell[][] arr, int x, int y) {
    int fishCounter = 0;




    if(arr[normalizeX(x - 1)][y].getType() == FISH) { //Checks left for fish
      fishCounter++;
      System.out.print("1 " + fishCounter);
    }
    if(arr[normalizeX(x + 1)][y].getType() == FISH) { //Checks right for fish
      fishCounter++;
      System.out.print("2 " + fishCounter);
    }
    if(arr[normalizeX(x)][normalizeY(y - 1)].getType() == FISH) { //Checks up for fish
      fishCounter++;
      System.out.print("3 " + fishCounter);
    }
    if(arr[normalizeX(x)][normalizeY(y + 1)].getType() == FISH) { //Checks down for fish
      fishCounter++;
      System.out.print("4 " + fishCounter);
    }
    if(arr[normalizeX(x + 1)][y].getType() == FISH) { //Checks right for fish
      fishCounter++;
      System.out.print("5 " + fishCounter);
    }
    if(arr[normalizeX(x + 1)][y].getType() == FISH) { //Checks right for fish
      fishCounter++;
      System.out.print("6 " + fishCounter);
    }
    if(arr[normalizeX(x + 1)][y].getType() == FISH) { //Checks right for fish
      fishCounter++;
      System.out.print("!! " + fishCounter);
    }

    return fishCounter;
  }

  public int normalizeX(int x) {
    return Math.abs(x) % width;
  }
  public int normalizeY(int y) {
    return Math.abs(y) % height;
  }




  /**
   *  The following method is required for Part II.
   */

  /**
   *  addShark() (with three parameters) places a shark in cell (x, y) if the
   *  cell is empty.  The shark's hunger is represented by the third parameter.
   *  If the cell is already occupied, leave the cell as it is.  You will need
   *  this method to help convert run-length encodings to Oceans.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   *  @param feeding is an integer that indicates the shark's hunger.  You may
   *         encode it any way you want; for instance, "feeding" may be the
   *         last timestep the shark was fed, or the amount of time that has
   *         passed since the shark was last fed, or the amount of time left
   *         before the shark will starve.  It's up to you, but be consistent.
   */

  public void addShark(int x, int y, int feeding) {
    // Your solution here.
  }

  /**
   *  The following method is required for Part III.
   */

  /**
   *  sharkFeeding() returns an integer that indicates the hunger of the shark
   *  in cell (x, y), using the same "feeding" representation as the parameter
   *  to addShark() described above.  If cell (x, y) does not contain a shark,
   *  then its return value is undefined--that is, anything you want.
   *  Normally, this method should not be called if cell (x, y) does not
   *  contain a shark.  You will need this method to help convert Oceans to
   *  run-length encodings.
   *  @param x is the x-coordinate of the cell whose contents are queried.
   *  @param y is the y-coordinate of the cell whose contents are queried.
   */

  public int sharkFeeding(int x, int y) {
    // Replace the following line with your solution.
    return 0;
  }


  public class Cell { //represents empty cell

    public int getType() {
      return EMPTY;
    }
  } //Defines empty cells, sharks, and fish as objects
  public class Shark extends Cell {
    private int hunger = 0; // 0 = fed, goes up as timeSteps without food increase

    public int getType() {
      return SHARK;
    }
  }
  public class Fish extends Cell {
    public int getType() {
      return FISH;
    }
  }

}
