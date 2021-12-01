//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class Test3 {
    private int i;
    private int j;
    private int starve;
    private Test2 fiR;
    private Test2 thR;
    private int olc;

    public Test3(int var1, int var2, int var3) {
        this.i = var1;
        this.j = var2;
        this.starve = var3;
        this.fiR = new Test2(0, var1 * var2 - 1);
        this.restartRuns();
    }

    public Test3(int var1, int var2, int var3, int[] var4, int[] var5) {
        this.i = var1;
        this.j = var2;
        this.starve = var3;
        this.fiR = null;
        Test2 var6 = null;
        int var7 = -1;

        for(int var8 = 0; var8 < var4.length; ++var8) {
            var7 += var5[var8];
            Test2 var9 = new Test2(var4[var8] == Test4.empty ? 0 : (var4[var8] == Test4.fish ? -1 : 1), var7, (Test2)null);
            if (var6 == null) {
                this.fiR = var9;
            } else {
                var6.nextRun = var9;
            }

            var6 = var9;
        }

        if (var7 < var1 * var2 - 1) {
            Test2 var10 = new Test2(Test4.empty, var1 * var2 - 1, (Test2)null);
            if (var6 == null) {
                this.fiR = var10;
            } else {
                var6.nextRun = var10;
            }
        }

        this.restartRuns();
    }

    public void restartRuns() {
        this.thR = this.fiR;
        this.olc = -1;
    }

    public int[] nextRun() {
        if (this.thR == null) {
            return null;
        } else {
            int[] var1 = new int[2];
            int var2 = this.thR.species;
            if (var2 == 0) {
                var1[0] = Test4.empty;
            } else if (var2 < 0) {
                var1[0] = Test4.fish;
            } else {
                var1[0] = Test4.shark;
            }

            var1[1] = this.thR.lastCell - this.olc;
            this.olc = this.thR.lastCell;
            this.thR = this.thR.nextRun;
            return var1;
        }
    }

    public Test1 toOcean() {
        Test1 var1 = new Test1(this.i, this.j, this.starve);
        Test2 var2 = this.fiR;

        for(int var3 = 0; var2 != null; var2 = var2.nextRun) {
            if (var2.species != 0) {
                for(int var4 = var3; var4 <= var2.lastCell; ++var4) {
                    if (var2.species < 0) {
                        var1.addFish(var4 % this.i, var4 / this.i);
                    } else {
                        var1.addShark(var4 % this.i, var4 / this.i, var2.species);
                    }
                }
            }

            var3 = var2.lastCell + 1;
        }

        return var1;
    }

    public Test3(Test1 var1) {
        this.i = var1.width();
        this.j = var1.height();
        this.starve = var1.starveTime();
        this.fiR = null;
        Test2 var5 = null;
        int var6 = -1;
        int var7 = 0;

        Test2 var9;
        for(int var8 = 0; var7 < this.j; var5 = var9) {
            int var2 = var1.cellContents(var8, var7);
            int var3 = var2 == 0 ? 0 : (var2 == 2 ? -1 : var1.sharkFeeding(var8, var7));

            int var4;
            do {
                ++var6;
                ++var8;
                if (var8 == this.i) {
                    ++var7;
                    var8 = 0;
                }

                var2 = var1.cellContents(var8, var7);
                var4 = var2 == 0 ? 0 : (var2 == 2 ? -1 : var1.sharkFeeding(var8, var7));
            } while(var7 < this.j && var3 == var4);

            var9 = new Test2(var3, var6, (Test2)null);
            if (var5 == null) {
                this.fiR = var9;
            } else {
                var5.nextRun = var9;
            }
        }

        this.restartRuns();
    }

    public void addFish(int var1, int var2) {
        var1 = this.wrapX(var1);
        var2 = this.wrapY(var2);
        int var3 = var2 * this.i + var1;
        Test2 var4 = null;
        int var5 = -1;

        Test2 var6;
        for(var6 = this.fiR; var3 > var6.lastCell; var6 = var6.nextRun) {
            var4 = var6;
            var5 = var6.lastCell;
        }

        if (var6.species == 0) {
            if (var4 != null && var4.species == -1 && var5 + 1 == var3) {
                var4.lastCell = var3;
                if (var3 == var6.lastCell) {
                    var4.nextRun = var6.nextRun;
                    if (var6.nextRun != null && var6.nextRun.species == -1) {
                        var4.lastCell = var6.nextRun.lastCell;
                        var4.nextRun = var6.nextRun.nextRun;
                    }
                }

            } else if (var6.nextRun != null && var6.nextRun.species == -1 && var6.lastCell == var3) {
                if (var5 + 1 == var3) {
                    if (var4 == null) {
                        this.fiR = var6.nextRun;
                    } else {
                        var4.nextRun = var6.nextRun;
                    }
                } else {
                    var6.lastCell = var3 - 1;
                }

            } else {
                if (var5 + 1 == var3) {
                    var6.species = -1;
                    if (var6.lastCell != var3) {
                        var6.nextRun = new Test2(0, var6.lastCell, var6.nextRun);
                        var6.lastCell = var3;
                    }
                } else {
                    if (var6.lastCell != var3) {
                        var6.nextRun = new Test2(0, var6.lastCell, var6.nextRun);
                    }

                    var6.lastCell = var3 - 1;
                    var6.nextRun = new Test2(-1, var3, var6.nextRun);
                }

            }
        }
    }

    public void addShark(int var1, int var2) {
        var1 = this.wrapX(var1);
        var2 = this.wrapY(var2);
        int var3 = var2 * this.i + var1;
        Test2 var4 = null;
        int var5 = -2;

        Test2 var6;
        for(var6 = this.fiR; var3 > var6.lastCell; var6 = var6.nextRun) {
            var4 = var6;
            var5 = var6.lastCell;
        }

        if (var6.species == 0) {
            if (var4 != null && var4.species == 1 && var5 + 1 == var3) {
                var4.lastCell = var3;
                if (var3 == var6.lastCell) {
                    var4.nextRun = var6.nextRun;
                    if (var6.nextRun != null && var6.nextRun.species == 1) {
                        var4.lastCell = var6.nextRun.lastCell;
                        var4.nextRun = var6.nextRun.nextRun;
                    }
                }

            } else if (var6.nextRun != null && var6.nextRun.species == 1 && var6.lastCell == var3) {
                if (var5 + 1 == var3) {
                    var4.nextRun = var6.nextRun;
                } else {
                    var6.lastCell = var3 - 1;
                }

            } else {
                if (var5 + 1 == var3) {
                    var6.species = 1;
                    if (var6.lastCell != var3) {
                        var6.nextRun = new Test2(0, var6.lastCell, var6.nextRun);
                        var6.lastCell = var3;
                    }
                } else {
                    if (var6.lastCell != var3) {
                        var6.nextRun = new Test2(0, var6.lastCell, var6.nextRun);
                    }

                    var6.lastCell = var3 - 1;
                    var6.nextRun = new Test2(1, var3, var6.nextRun);
                }

            }
        }
    }

    public void check() {
    }

    private int wrapX(int var1) {
        while(var1 >= this.i) {
            var1 -= this.i;
        }

        while(var1 < 0) {
            var1 += this.i;
        }

        return var1;
    }

    private int wrapY(int var1) {
        while(var1 >= this.j) {
            var1 -= this.j;
        }

        while(var1 < 0) {
            var1 += this.j;
        }

        return var1;
    }
}
