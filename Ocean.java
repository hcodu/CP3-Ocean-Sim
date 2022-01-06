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
  private int counter = 0;

  Ocean newOcean;


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

  public Ocean(int i, int j, int sT ) {
    // Your solution here.
    width = i;
    height = j;
    starveTime = sT;
    currentArr = new Cell[height][width];
    nextArr = new Cell[height][width];


    for(int h = 0; h < height; h++) {
      for(int w = 0; w < width; w++) {
        currentArr[h][w] = new Cell(1, EMPTY);
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

  public void setCurrentArr(Cell[][] arr) {
    for(int i = 0; i < arr.length; i++) {
      for(int j = 0; j < arr[0].length; j++) {
        currentArr[i][j] = arr[i][j];
      }
    }
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
    if(currentArr[y][x].getType() == EMPTY) {
      this.currentArr[y][x] = new Cell(1, FISH);
    }

  }

  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is. WRONG!!!?!?!?!?!?
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
    // Your solution here.
    if(currentArr[y][x].getType() == EMPTY || currentArr[y][x].getType() == FISH) {
      currentArr[y][x] =  new Cell(1, SHARK);
    }
  }

  /**
   *  cellContents() returns EMPTY if cell (x, y) is empty, FISH if it contains
   *  a fish, and SHARK if it contains a shark.
   *  @param x is the x-coordinate of the cell whose contents are queried.
   *  @param y is the y-coordinate of the cell whose contents are queried.
   */

  public int cellContents(int x, int y) {
    return currentArr[y][x].getType();
  }

  /**
   *  timeStep() performs a simulation timestep as described in README.
   *  @return an ocean representing the elapse of one timestep.
   */

  public Ocean timeStep() {
    Ocean newOcean = new Ocean(width, height, starveTime);
    newOcean.setCurrentArr(currentArr);

    //Visits every cell, applying the logic of Rule #1
    /* 1) If a cell contains a shark, and any of its neighbors is a fish, then the
     shark eats during the timestep, and it remains in the cell at the end of the
     timestep with its hunger completely gone.  (We may have multiple sharks sharing
    the same fish.  This is fine; miraculously, they all get enough to eat.) */
    for(int i = 0; i < currentArr.length; i++) { //Y COORDINATE
      for(int j = 0; j < currentArr[0].length; j++) { //X COORDINATE
        //System.out.println("At " + j + " " + i + "  there is a  " + currentArr[i][j].getType() + " cell.");

        if(this.currentArr[i][j].getType() == SHARK) {
          int numFish = checkFish(this.currentArr, j, i); //number of fish in 8 adjacent

          if(numFish > 0) {
            newOcean.currentArr[i][j].resetHunger();
          }

        }
      }
    }

    //Vists every cell, applying the logic of Rule #2
    /* 2) If a cell contains a shark, and none of its neighbors is a fish, it gets
     hungrier during the timestep.  If this timestep is the (starveTime + 1)th
     consecutive timestep the shark has gone through without eating, then the shark
     dies (disappears).  Otherwise, it remains in the cell.  An example
     demonstrating this rule appears below. */
    for(int i = 0; i < currentArr.length; i++) { //Y COORDINATE
      for(int j = 0; j < currentArr[0].length; j++) { //X COORDINATE
        //System.out.println("At " + j + " " + i + "  there is a  " + currentArr[i][j].getType() + " cell.");

        if(this.currentArr[i][j].getType() == SHARK) {
          int numFish = checkFish(this.currentArr, j, i); //number of fish in 8 adjacent

          if(numFish == 0) {
            newOcean.currentArr[i][j].increaseHunger();
            if(newOcean.currentArr[i][j].getHunger() > starveTime) {
              newOcean.emptyCell(j, i);
            }
          }

        }
      }
    }

    // Visits every cell, applying the logic of Rule #4
    /* 4) If a cell contains a fish, and one of its neighbors is a shark, then the
    // fish is eaten by a shark, and therefore disappears. */
    for(int i = 0; i < currentArr.length; i++) { //Y COORDINATE
      for(int j = 0; j < currentArr[0].length; j++) { //X COORDINATE
        //System.out.println("At " + j + " " + i + "  there is a  " + currentArr[i][j].getType() + " cell.");

        if(this.currentArr[i][j].getType() == FISH) {
          int numSharks = checkSharks(this.currentArr, j, i); //number of sharks in 8 adjacent

          if(numSharks == 1) {
            newOcean.emptyCell(j, i);
          }

        }
      }
    }

    // Visits every cell, applying the logic of Rule #5
    /* 5) If a cell contains a fish, and two or more of its neighbors are sharks, then
     a new shark is born in that cell.  Sharks are well-fed at birth; _after_ they
     are born, they can survive an additional starveTime timesteps without eating.
      (But they will die at the end of starveTime + 1 consecutive timesteps without
     eating.) */
    for(int i = 0; i < currentArr.length; i++) { //Y COORDINATE
      for(int j = 0; j < currentArr[0].length; j++) { //X COORDINATE

        if(this.currentArr[i][j].getType() == FISH) {
          int numSharks = checkSharks(this.currentArr, j, i); //number of sharks in 8 adjacent

          if(numSharks >= 2) {
            //System.out.println("At " + j + " " + i + "  there is a  " + currentArr[i][j].getType() + " cell. There are " + numSharks + " sharks surrounding this cell, adding a shark to " + j + " " + i);
            newOcean.addShark(j, i); //Rule 5 //Isn't properly adding sharks
            //System.out.println("Now in the new array at " + j + " " + i + " there is a " + newOcean.currentArr[i][j].getType());
          }

        }
      }
    }

    // Visits every cell, applying the logic of Rule #7 and Rule #8
    /*7) If a cell is empty, at least two of its neighbors are fish, and at most one
    of its neighbors is a shark, then a new fish is born in that cell.
     8) If a cell is empty, at least two of its neighbors are fish, and at least two
     of its neighbors are sharks, then a new shark is born in that cell.  (The new
     shark is well-fed at birth, even though it hasn't eaten a fish yet' */
    for(int i = 0; i < currentArr.length; i++) { //Y COORDINATE
      for(int j = 0; j < currentArr[0].length; j++) { //X COORDINATE
        //System.out.println("At " + j + " " + i + "  there is a  " + currentArr[i][j].getType() + " cell.");

        if(this.currentArr[i][j].getType() == EMPTY) {
          int numFish = checkFish(this.currentArr, j, i); //number of fish in 8 adjacent
          int numSharks = checkSharks(this.currentArr, j, i); //number of sharks in 8 adjacent

          if(numFish >= 2) {
            if(numSharks <= 1) {
              newOcean.addFish(j, i); //If more than or 2 fish and less than or 1 shark, adds a fish to the cell
            }
            else if(numSharks >= 2) {
              newOcean.addShark(j, i); //If more than or 2 fish and more than or 2 sharks, adds a shark to cell
            }
          }

        }
      }
    }


    return newOcean;
  }

  public int checkFish(Cell[][] arr, final int x, final int y) {
    //JAVA 2D ARRAYS ARE [Y][X] down is +, right is +, up is -, left is -
    int fishCounter = 0;



    //System.out.println("L: At " + normalizeX(x - 1) + " , " + y + "there is a " + arr[y][normalizeX(x - 1)].getType());
    if(arr[normalizeY(y)][normalizeX(x - 1)].getType() == FISH) { //Checks left for fish
      fishCounter++;
      //System.out.println("L: Fish found! at" + normalizeX(x - 1) + " " + y);
    }

    //System.out.println("R: At " + normalizeX(x + 1) + " , " + y + "there is a " + arr[y][normalizeX(x + 1)].getType());
    if(arr[normalizeY(y)][normalizeX(x + 1)].getType() == FISH) { //Checks right for fish
      fishCounter++;
      //System.out.print("R " + fishCounter);
        //System.out.println("R: Fish found! at" + normalizeX(x + 1) + " " + y);

    }

    //System.out.println("U: At " + x + " , " + normalizeY(y - 1) + "there is a " + arr[y][normalizeX(x - 1)].getType());
    if(arr[normalizeY(y - 1)][normalizeX(x)].getType() == FISH) { //Checks up for fish
      fishCounter++;
      //System.out.print("U " + fishCounter);
        //System.out.println("U: Fish found! at" + normalizeX(x) + " " + normalizeY(y - 1));

    }

    //System.out.println("D: At " + x + " , " + normalizeY(y + 1) + "there is a " + arr[normalizeY(y + 1)][normalizeX(x)].getType());
    if(arr[normalizeY(y + 1)][normalizeX(x)].getType() == FISH) { //Checks down for fish
      fishCounter++;
      //System.out.print("D " + fishCounter);
        //System.out.println("D: Fish found! at" + normalizeX(x) + " " + normalizeY(y + 1));

    }

    //System.out.println("uL: At " + normalizeX(x - 1) + " , " + normalizeY(y - 1) + "there is a " + arr[normalizeY(y - 1)][normalizeX(x - 1)].getType());
    if(arr[normalizeY(y - 1)][normalizeX(x - 1)].getType() == FISH) { //Checks up-left for fish
      fishCounter++;
      //System.out.print("uL " + fishCounter);
        //System.out.println("uL: Fish found! at" + normalizeX(x - 1) + " " + normalizeY(y - 1));

    }

    //System.out.println("uR: At " + normalizeX(x + 1) + " , " + normalizeY(y - 1) + "there is a " + arr[normalizeY(y - 1)][normalizeX(x + 1)].getType());
    if(arr[normalizeY(y - 1)][normalizeX(x + 1)].getType() == FISH) { //Checks up-right for fish
      fishCounter++;
      //System.out.print("uR " + fishCounter);
        //System.out.println("uR: Fish found! at" + normalizeX(x + 1) + " " + normalizeY(y - 1));

    }

    //System.out.println("dL: At " + normalizeX(x - 1) + " , " + normalizeY(y + 1) + "there is a " + arr[normalizeY(y + 1)][normalizeX(x - 1)].getType());
    if(arr[normalizeY(y + 1)][normalizeX(x - 1)].getType() == FISH) { //Checks down-left for fish
      fishCounter++;
      //System.out.print("dL " + fishCounter);
        //System.out.println("dL: Fish found! at" + normalizeX(x - 1) + " " + normalizeY(y + 1));

    }

    //System.out.println("dr: At " + normalizeX(x + 1) + " , " + normalizeY(y + 1) + "there is a " + arr[normalizeY(y - 1)][normalizeX(x + 1)].getType());
    if(arr[normalizeY(y + 1)][normalizeX(x + 1)].getType() == FISH) { //Checks down-right for fish
      fishCounter++;
      //System.out.print("dR " + fishCounter);
        //System.out.println("dr: Fish found! at" + normalizeX(x + 1) + " " + normalizeY(y - 1));
    }

    return fishCounter;
  }

  public int checkSharks(Cell[][] arr, final int x, final int y) {
      //JAVA 2D ARRAYS ARE [Y][X] down is +, right is +, up is -, left is -
      int sharkCounter = 0;



      //System.out.println("L: At " + normalizeX(x - 1) + " , " + y + "there is a " + arr[y][normalizeX(x - 1)].getType());
      if(arr[normalizeY(y)][normalizeX(x - 1)].getType() == SHARK) { //Checks left for fish
          sharkCounter++;
         // System.out.println("L: Fish found! at" + normalizeX(x - 1) + " " + y);
      }

      //System.out.println("R: At " + normalizeX(x + 1) + " , " + y + "there is a " + arr[y][normalizeX(x + 1)].getType());
      if(arr[normalizeY(y)][normalizeX(x + 1)].getType() == SHARK) { //Checks right for fish
          sharkCounter++;
          //System.out.print("R " + fishCounter);
          //System.out.println("R: Fish found! at" + normalizeX(x + 1) + " " + y);
      }

      //System.out.println("U: At " + x + " , " + normalizeY(y - 1) + "there is a " + arr[y][normalizeX(x - 1)].getType());
      if(arr[normalizeY(y - 1)][normalizeX(x)].getType() == SHARK) { //Checks up for fish
          sharkCounter++;
          //System.out.print("U " + fishCounter);
          //System.out.println("U: Fish found! at" + normalizeX(x) + " " + normalizeY(y - 1));
      }

      //System.out.println("D: At " + x + " , " + normalizeY(y + 1) + "there is a " + arr[normalizeY(y + 1)][normalizeX(x)].getType());
      if(arr[normalizeY(y + 1)][normalizeX(x)].getType() == SHARK) { //Checks down for fish
          sharkCounter++;
          //System.out.print("D " + fishCounter);
          //System.out.println("D: Fish found! at" + normalizeX(x) + " " + normalizeY(y + 1));
      }

      //System.out.println("uL: At " + normalizeX(x - 1) + " , " + normalizeY(y - 1) + "there is a " + arr[normalizeY(y - 1)][normalizeX(x - 1)].getType());
      if(arr[normalizeY(y - 1)][normalizeX(x - 1)].getType() == SHARK) { //Checks up-left for fish
          sharkCounter++;
          //System.out.print("uL " + fishCounter);
          //System.out.println("uL: Fish found! at" + normalizeX(x - 1) + " " + normalizeY(y - 1));
      }

      //System.out.println("uR: At " + normalizeX(x + 1) + " , " + normalizeY(y - 1) + "there is a " + arr[normalizeY(y - 1)][normalizeX(x + 1)].getType());
      if(arr[normalizeY(y - 1)][normalizeX(x + 1)].getType() == SHARK) { //Checks up-right for fish
          sharkCounter++;
          //System.out.print("uR " + fishCounter);
          //System.out.println("uR: Fish found! at" + normalizeX(x + 1) + " " + normalizeY(y - 1));
      }

      //System.out.println("dL: At " + normalizeX(x - 1) + " , " + normalizeY(y + 1) + "there is a " + arr[normalizeY(y + 1)][normalizeX(x - 1)].getType());
      if(arr[normalizeY(y + 1)][normalizeX(x - 1)].getType() == SHARK) { //Checks down-left for fish
          sharkCounter++;
          //System.out.print("dL " + fishCounter);
          //System.out.println("dL: Shark found! at" + normalizeX(x - 1) + " " + normalizeY(y + 1));
      }

      //System.out.println("dr: At " + normalizeX(x + 1) + " , " + normalizeY(y + 1) + "there is a " + arr[normalizeY(y - 1)][normalizeX(x + 1)].getType());
      if(arr[normalizeY(y + 1)][normalizeX(x + 1)].getType() == SHARK) { //Checks down-right for fish
          sharkCounter++;
          //System.out.print("dR " + fishCounter);
          //System.out.println("dr: Shark found! at" + normalizeX(x + 1) + " " + normalizeY(y - 1));
      }
      return sharkCounter;
  }

  public void emptyCell(int x, int y) {
    currentArr[y][x] = new Cell(1, EMPTY);
  }

  public int normalizeX(int x) { //Link top and bottom edges like torus
        return Math.floorMod(x, width);
  }

  public int normalizeY(int y) { //Links side edges like torus
    return Math.floorMod(y, height);
  }

  public Cell[][] getCurrentArr() {
    return currentArr;
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
    if(currentArr[y][x].getType() == EMPTY || currentArr[y][x].getType() == FISH) {
      currentArr[y][x] = new Cell(1, SHARK, feeding);

    }
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
    return currentArr[y][x].quantity;
  }

  public void printArr() {
    int width = this.width();
    int height = this.height();

    for (int x = 0; x < width + 2; x++) {
      System.out.print("-");
    }
    System.out.println();
    for (int y = 0; y < height; y++) {
      System.out.print("|");
      for (int x = 0; x < width; x++) {
        int contents = this.cellContents(x, y);
        if (contents == Ocean.SHARK) {
          System.out.print('S');
        }
        else if (contents == Ocean.FISH) {
          System.out.print('~');
        }
        else {
          System.out.print('.');
        }
      }
      System.out.println("|");
    }
    for (int x = 0; x < width + 2; x++) {
      System.out.print("-");
    }
    System.out.println();
  }


  public static class Cell extends Object { //represents empty cell
    int quantity;
    int type;
    int hunger;

    public Cell(int q, int t) {
      quantity = q;
      type = t;
      hunger = 0;
    }

    public Cell(int q, int t, int h) {
      quantity = q;
      type = t;
      hunger = h;
    }

    public int getType() {
      return type;
    }

    public int getQuantity() {
      return quantity;
    }

    public void increaseHunger() {
      hunger++;
    }
    public void resetHunger() {
      hunger = 0;
    }
    public int getHunger() {
      return hunger;
    }
  }

}