//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Random;

class Test1 {
    public static final int MT = 0;
    public static final int SK = 1;
    public static final int FH = 2;
    private int i;
    private int j;
    private int[][] cell;
    private int tx;
    private int ty;
    private int starve;

    public Test1(int var1, int var2, int var3) {
        this.i = var1;
        this.j = var2;
        this.starve = var3;
        this.cell = new int[var1][var2];
        this.tx = 0;
        this.ty = 0;
    }

    public Test1(Ocean var1) {
        if (var1 != null) {
            this.i = var1.width();
            this.j = var1.height();
            this.starve = var1.starveTime();
            if (this.i >= 1 && this.j >= 1) {
                this.cell = new int[this.i][this.j];

                for(int var2 = 0; var2 < this.i; ++var2) {
                    for(int var3 = 0; var3 < this.j; ++var3) {
                        int var4 = var1.cellContents(var2, var3);
                        if (var4 == Test4.shark) {
                            this.cell[var2][var3] = 1;
                        } else if (var4 == Test4.fish) {
                            this.cell[var2][var3] = -1;
                        } else {
                            this.cell[var2][var3] = 0;
                        }
                    }
                }

            }
        }
    }

    public int width() {
        return this.i;
    }

    public int height() {
        return this.j;
    }

    public int starveTime() {
        return this.starve;
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

    public void addFish(int var1, int var2) {
        var1 = this.wrapX(var1);
        var2 = this.wrapY(var2);
        if (this.cell[var1][var2] == 0) {
            this.cell[var1][var2] = -1;
        }

    }

    public void addShark(int var1, int var2) {
        this.addShark(var1, var2, 1);
    }

    public int cellContents(int var1, int var2) {
        var1 = this.wrapX(var1);
        var2 = this.wrapY(var2);
        if (this.cell[var1][var2] == 0) {
            return 0;
        } else {
            return this.cell[var1][var2] == -1 ? 2 : 1;
        }
    }

    void fishOcean() {
        Random var1 = new Random(0L);
        int var2 = 0;
        int var3 = 0;

        for(int var4 = 0; var4 < this.i; ++var4) {
            var2 = (var2 + 78887) % this.i;

            for(int var5 = 0; var5 < this.j; ++var5) {
                var3 = (var3 + 78887) % this.j;
                int var6 = var1.nextInt();
                if (var6 > 1932735282) {
                    this.addFish(var2, var3);
                }
            }
        }

    }

    void sharkOcean() {
        Random var1 = new Random(0L);
        int var2 = 0;
        int var3 = 0;

        for(int var4 = 0; var4 < this.i; ++var4) {
            var2 = (var2 + 78887) % this.i;
            if ((var2 & 8) == 0) {
                for(int var5 = 0; var5 < this.j; ++var5) {
                    var3 = (var3 + 78887) % this.j;
                    if ((var3 & 8) == 0) {
                        int var6 = var1.nextInt();
                        if (var6 < 0) {
                            this.addFish(var2, var3);
                        } else if (var6 > 1500000000) {
                            this.addShark(var2, var3);
                        }
                    }
                }
            }
        }

    }

    public Test1 timeStep() {
        Test1 var1 = new Test1(this.i, this.j, this.starve);

        for(int var2 = 0; var2 < this.i; ++var2) {
            for(int var3 = 0; var3 < this.j; ++var3) {
                int var4 = this.wrapX(var2 - 1);
                int var5 = this.wrapX(var2 + 1);
                int var6 = this.wrapY(var3 - 1);
                int var7 = this.wrapY(var3 + 1);
                int var8 = this.cell[var4][var6];
                int var9 = this.cell[var2][var6];
                int var10 = this.cell[var5][var6];
                int var11 = this.cell[var4][var3];
                int var12 = this.cell[var5][var3];
                int var13 = this.cell[var4][var7];
                int var14 = this.cell[var2][var7];
                int var15 = this.cell[var5][var7];
                int var16 = (var8 > 0 ? 1 : 0) + (var9 > 0 ? 1 : 0) + (var10 > 0 ? 1 : 0) + (var11 > 0 ? 1 : 0) + (var12 > 0 ? 1 : 0) + (var13 > 0 ? 1 : 0) + (var14 > 0 ? 1 : 0) + (var15 > 0 ? 1 : 0);
                int var17 = (var8 < 0 ? 1 : 0) + (var9 < 0 ? 1 : 0) + (var10 < 0 ? 1 : 0) + (var11 < 0 ? 1 : 0) + (var12 < 0 ? 1 : 0) + (var13 < 0 ? 1 : 0) + (var14 < 0 ? 1 : 0) + (var15 < 0 ? 1 : 0);
                var1.cell[var2][var3] = 0;
                if (this.cell[var2][var3] == 0) {
                    if (var17 > 1) {
                        if (var16 < 2) {
                            var1.cell[var2][var3] = -1;
                        } else {
                            var1.cell[var2][var3] = 1;
                        }
                    }
                } else if (this.cell[var2][var3] < 0) {
                    if (var16 == 0) {
                        var1.cell[var2][var3] = -1;
                    } else if (var16 > 1) {
                        var1.cell[var2][var3] = 1;
                    }
                } else if (var17 > 0) {
                    var1.cell[var2][var3] = 1;
                } else if (this.cell[var2][var3] <= this.starve) {
                    var1.cell[var2][var3] = this.cell[var2][var3] + 1;
                }
            }
        }

        return var1;
    }

    public void addShark(int var1, int var2, int var3) {
        var1 = this.wrapX(var1);
        var2 = this.wrapY(var2);
        if (this.cell[var1][var2] == 0) {
            this.cell[var1][var2] = var3;
        }

    }

    public int sharkFeeding(int var1, int var2) {
        var1 = this.wrapX(var1);
        var2 = this.wrapY(var2);
        return this.cell[var1][var2];
    }

    public void paint() {
        int var1;
        for(var1 = 0; var1 < this.i + 2; ++var1) {
            System.out.print("-");
        }

        System.out.println();

        for(var1 = 0; var1 < this.j; ++var1) {
            System.out.print("|");

            for(int var2 = 0; var2 < this.i; ++var2) {
                if (this.cell[var2][var1] == 0) {
                    System.out.print(".");
                } else if (this.cell[var2][var1] == -1) {
                    System.out.print("~");
                } else {
                    System.out.print("S");
                }
            }

            System.out.println("|");
        }

        for(var1 = 0; var1 < this.i + 2; ++var1) {
            System.out.print("-");
        }

        System.out.println();
    }

    public boolean equals(Test1 var1) {
        if (this.i == var1.i && this.j == var1.j) {
            for(int var2 = 0; var2 < this.j; ++var2) {
                for(int var3 = 0; var3 < this.i; ++var3) {
                    if (this.cell[var3][var2] != var1.cell[var3][var2] && (this.cell[var3][var2] < 1 || var1.cell[var3][var2] < 1)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean innerEquals(Test1 var1, int var2) {
        if (this.i == var1.i && this.j == var1.j) {
            for(int var3 = var2; var3 < this.j - var2; ++var3) {
                for(int var4 = var2; var4 < this.i - var2; ++var4) {
                    if (this.cell[var4][var3] != var1.cell[var4][var3] && (this.cell[var4][var3] < 1 || var1.cell[var4][var3] < 1)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
