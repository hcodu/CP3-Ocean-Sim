//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Random;

public class Test {
    private static final int iFish = 50;
    private static final int jFish = 37;
    private static final int iterFish = 15;
    private static final int iShark = 33;
    private static final int jShark = 37;
    private static final int iterShark = 15;
    private static int starveTime = 2;

    public Test() {
    }

    public static void fishOcean(Ocean var0) {
        Random var1 = new Random(0L);
        int var2 = 0;
        int var3 = 0;
        int var4 = var0.width();
        int var5 = var0.height();

        for(int var6 = 0; var6 < var4; ++var6) {
            var2 = (var2 + 78887) % var4;

            for(int var7 = 0; var7 < var5; ++var7) {
                var3 = (var3 + 78887) % var5;
                int var8 = var1.nextInt();
                if (var8 > 1932735282) {
                    var0.addFish(var2, var3);
                }
            }
        }

    }

    public static void sharkOcean(Ocean var0) {
        Random var1 = new Random(0L);
        int var2 = 0;
        int var3 = 0;
        int var4 = var0.width();
        int var5 = var0.height();

        for(int var6 = 0; var6 < var4; ++var6) {
            var2 = (var2 + 78887) % var4;
            if ((var2 & 8) == 0) {
                for(int var7 = 0; var7 < var5; ++var7) {
                    var3 = (var3 + 78887) % var5;
                    if ((var3 & 8) == 0) {
                        int var8 = var1.nextInt();
                        if (var8 < 0) {
                            var0.addFish(var2, var3);
                        } else if (var8 > 1500000000) {
                            var0.addShark(var2, var3);
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] var0) throws InterruptedException {
        byte var5 = 1;
        byte var6 = 1;
        byte var7 = 1;
        byte var8 = 1;
        Test4.init();
        System.out.println("Beginning Part I.");
        System.out.println("Performing a 50x37 test with fish only.  starveTime = " + starveTime + ".");
        Test1 var1 = new Test1(50, 37, starveTime);
        var1.fishOcean();
        Ocean var3 = new Ocean(50, 37, starveTime);
        fishOcean(var3);
        Test1 var4 = new Test1(var3);
        Test1 var2;
        int var9;
        boolean var10;
        if (var1.equals(var4)) {
            for(var9 = 1; var9 <= 15; ++var9) {
                var2 = var1;
                var1 = var1.timeStep();
                var3 = var3.timeStep();
                var4 = new Test1(var3);
                if (!var1.equals(var4)) {
                    var10 = var1.innerEquals(var4, var9);
                    if (var8 == 1) {
                        var8 = 0;
                        System.out.println("Your ocean is incorrect after timestep " + var9 + ".  The previous ocean was:");
                        var2.paint();
                        System.out.println("The correct current ocean is:");
                        var1.paint();
                        System.out.println("Your Ocean is:");
                        var4.paint();
                        if (!var10) {
                            var6 = 0;
                            var7 = 0;
                            break;
                        }

                        System.out.println("(The problem seems to be only at the boundaries.)");
                    }

                    if (!var10) {
                        var6 = 0;
                        var7 = 0;
                        System.out.println("Your ocean's interior is incorrect after timestep " + var9 + ".  The previous ocean was:");
                        var2.paint();
                        System.out.println("The correct current ocean is:");
                        var1.paint();
                        System.out.println("Your Ocean is:");
                        var4.paint();
                        break;
                    }
                }
            }
        } else {
            System.out.println("Your initial ocean is incorrect.  The correct ocean is:");
            var1.paint();
            System.out.println("Your Ocean is:");
            var4.paint();
            var5 = 0;
            var8 = 0;
            var6 = 0;
            var7 = 0;
        }

        if (var8 == 1) {
            System.out.println("  Test successful.");
        }

        System.out.println();
        System.out.println("Performing a 33x37 test with sharks and fish.  starveTime = " + starveTime + ".");
        var1 = new Test1(33, 37, starveTime);
        var1.sharkOcean();
        var3 = new Ocean(33, 37, starveTime);
        sharkOcean(var3);
        var4 = new Test1(var3);
        if (var1.equals(var4)) {
            for(var9 = 1; var9 <= 15; ++var9) {
                var2 = var1;
                var1 = var1.timeStep();
                var3 = var3.timeStep();
                var4 = new Test1(var3);
                if (!var1.equals(var4)) {
                    var10 = var1.innerEquals(var4, var9);
                    if (var8 == 1) {
                        var8 = 0;
                        System.out.println("Your ocean is incorrect after timestep " + var9 + ".  The previous ocean was:");
                        var2.paint();
                        System.out.println("The correct current ocean is:");
                        var1.paint();
                        System.out.println("Your Ocean is:");
                        var4.paint();
                        if (!var10) {
                            var7 = 0;
                            break;
                        }

                        System.out.println("(The problem seems to be only at the boundaries.)");
                    }

                    if (!var10) {
                        var7 = 0;
                        System.out.println("Your ocean's interior is incorrect after timestep " + var9 + ".  The previous ocean was:");
                        var2.paint();
                        System.out.println("The correct current ocean is:");
                        var1.paint();
                        System.out.println("Your Ocean is:");
                        var4.paint();
                        break;
                    }
                }
            }
        } else {
            System.out.println("Your initial ocean is incorrect.  The correct ocean is:");
            var1.paint();
            System.out.println("Your Ocean is:");
            var4.paint();
            var5 = 0;
            var8 = 0;
            var7 = 0;
        }

        if (var8 == 1) {
            System.out.println("  Test successful.");
        }

        System.out.println();
        System.out.println("Total Part I score:  " + (2 * (var5 + var8) + var6 + var7) + " out of 6.");
        System.out.println("Total Autogradable score so far:  " + (2 * (var5 + var8) + var6 + var7) + " out of 6.");
        System.out.println();
        System.out.println("Beginning Part II.");
        System.out.println("Performing a 4x4 RunLengthEncoding-to-Ocean test.");
        byte var30 = 1;
        byte var31 = 1;
        System.out.println("  Calling the five-parameter constructor.");
        int[] var11 = new int[]{Test4.shark, Test4.fish, Test4.empty, Test4.fish, Test4.empty, Test4.fish};
        int[] var12 = new int[]{3, 2, 5, 1, 1, 4};
        Test3 var13 = new Test3(4, 4, starveTime, var11, var12);
        RunLengthEncoding var14 = new RunLengthEncoding(4, 4, starveTime, var11, var12);
        System.out.println("  Reading back the encoding through nextRun.");

        for(int var15 = 0; var15 < var11.length; ++var15) {
            int[] var16 = var14.nextRun();
            if (var16 == null) {
                System.out.println("    Run # " + var15 + " missing.  (Runs are indexed from zero.)");
                System.out.println("    (In other words, your nextRun() is returning null when it shouldn't.)");
                System.out.println("    Here's the correct ocean.");
                var13.toOcean().paint();
                var30 = 0;
                break;
            }

            if (var16[0] != var11[var15] || var16[1] != var12[var15]) {
                System.out.println("    Run # " + var15 + " should be " + var11[var15] + ", " + var12[var15] + ".  (Runs are indexed from zero.)");
                System.out.println("    Instead, it's " + var16[0] + ", " + var16[1]);
                System.out.println("    Here's the correct ocean.");
                var13.toOcean().paint();
                var30 = 0;
                break;
            }
        }

        if (var30 == 1 && var14.nextRun() != null) {
            System.out.println("    Your nextRun() is failing to return null when the runs run out.");
            System.out.println("    Here's the correct ocean.");
            var13.toOcean().paint();
            var30 = 0;
        }

        System.out.println("  Calling toOcean.");
        Ocean var32 = var14.toOcean();
        Test1 var33 = new Test1(var32);
        Test1 var17 = var13.toOcean();
        if (!var17.equals(var33)) {
            System.out.println("The correct ocean is:");
            var17.paint();
            System.out.println("Your Ocean is incorrect:");
            var33.paint();
            var31 = 0;
        }

        if (var30 * var31 == 1) {
            System.out.println("  Test successful.");
        }

        System.out.println();
        System.out.println("Total Part II score:  " + (2 * var31 + var30) + " out of 3.");
        System.out.println("Total Autogradable score so far:  " + (2 * (var5 + var8 + var31) + var6 + var7 + var30) + " out of 9.");
        System.out.println();
        System.out.println("Beginning Part III.");
        System.out.println("Run-length encoding an Ocean.");
        byte var18 = 1;
        byte var19 = 1;
        System.out.println("  Calling the one-parameter constructor.");
        Test3 var20 = new Test3(var1);
        RunLengthEncoding var21 = new RunLengthEncoding(var3);
        int[] var22 = var20.nextRun();
        int[] var23 = var21.nextRun();

        int var24;
        for(var24 = 0; var22 != null; ++var24) {
            if (var23 == null) {
                System.out.println("    Run # " + var24 + " missing.  (Runs are indexed from zero.)");
                System.out.println("    (In other words, your nextRun() is returning null when it shouldn't.)");
                System.out.println("    Here's the correct ocean.");
                var1.paint();
                var18 = 0;
                break;
            }

            if (var22[0] != var23[0] || var22[1] != var23[1]) {
                System.out.println("    Run # " + var24 + " should be " + var22[0] + ", " + var22[1] + ".  (Runs indexed from zero.)");
                System.out.println("    Instead, it's " + var23[0] + ", " + var23[1]);
                System.out.println("    Here's the correct ocean.");
                var1.paint();
                var18 = 0;
                break;
            }

            var22 = var20.nextRun();
            var23 = var21.nextRun();
        }

        if (var18 == 1 && var23 != null) {
            System.out.println("    Your nextRun is failing to return null when the runs run out.");
            System.out.println("    Here's the correct ocean.");
            var1.paint();
            var18 = 0;
        }

        System.out.println("  Converting back to an Ocean.");
        Ocean var25 = var21.toOcean();
        Test1 var26 = new Test1(var25);
        if (!var1.equals(var26)) {
            System.out.println("The correct ocean is:");
            var1.paint();
            System.out.println("Your Ocean is incorrect:");
            var26.paint();
            var19 = 0;
        } else {
            System.out.println("  Running one timestep.");
            var1 = var1.timeStep();
            var25 = var25.timeStep();
            var26 = new Test1(var25);
            if (!var1.equals(var26)) {
                System.out.println("The correct ocean is:");
                var1.paint();
                System.out.println("Your Ocean is incorrect:");
                var26.paint();
                System.out.println("You probably messed up the shark hunger in toOcean().");
                var19 = 0;
            }
        }

        if (var18 * var19 == 1) {
            System.out.println("  Test successful.");
        }

        System.out.println();
        System.out.println("Total Part III score:  " + (2 * var18 + var19) + " out of 3.");
        System.out.println("Total Autogradable score so far:  " + (2 * (var5 + var8 + var31 + var18) + var6 + var7 + var30 + var19) + " out of 12.");
        System.out.println();
        System.out.println("Beginning Part IV.");
        System.out.println("Adding to your 4x4 run-length encoding (from Part II).");
        byte var27 = 1;
        System.out.println("  Adding shark at (2, 1).");
        var13.addShark(2, 1);
        var14.addShark(2, 1);
        var13.restartRuns();
        var14.restartRuns();
        int[] var28 = var13.nextRun();
        int[] var29 = var14.nextRun();

        for(var24 = 0; var28 != null; ++var24) {
            if (var29 == null) {
                System.out.println("    Run # " + var24 + " missing.  (Runs are indexed from zero.)");
                System.out.println("    (In other words, your nextRun() is returning null when it shouldn't.)");
                System.out.println("    Here's the correct ocean.");
                var13.toOcean().paint();
                var27 = 0;
                break;
            }

            if (var28[0] != var29[0] || var28[1] != var29[1]) {
                System.out.println("    Run # " + var24 + " should be " + var28[0] + ", " + var28[1] + ".  (Runs indexed from zero.)");
                System.out.println("    Instead, it's " + var29[0] + ", " + var29[1]);
                System.out.println("    Here's the correct ocean.");
                var13.toOcean().paint();
                var27 = 0;
                break;
            }

            var28 = var13.nextRun();
            var29 = var14.nextRun();
        }

        System.out.println("  Adding shark at (1, 1).");
        var13.addShark(1, 1);
        var14.addShark(1, 1);
        var13.restartRuns();
        var14.restartRuns();
        var28 = var13.nextRun();
        var29 = var14.nextRun();

        for(var24 = 0; var28 != null; ++var24) {
            if (var29 == null) {
                System.out.println("    Run # " + var24 + " missing.  (Runs are indexed from zero.)");
                System.out.println("    (In other words, your nextRun() is returning null when it shouldn't.)");
                System.out.println("    Here's the correct ocean.");
                var13.toOcean().paint();
                var27 = 0;
                break;
            }

            if (var28[0] != var29[0] || var28[1] != var29[1]) {
                System.out.println("    Run # " + var24 + " should be " + var28[0] + ", " + var28[1] + ".  (Runs indexed from zero.)");
                System.out.println("    Instead, it's " + var29[0] + ", " + var29[1]);
                System.out.println("    Here's the correct ocean.");
                var13.toOcean().paint();
                var27 = 0;
                break;
            }

            var28 = var13.nextRun();
            var29 = var14.nextRun();
        }

        System.out.println("  Adding shark at (3, 1).");
        var13.addShark(3, 1);
        var14.addShark(3, 1);
        var13.restartRuns();
        var14.restartRuns();
        var28 = var13.nextRun();
        var29 = var14.nextRun();

        for(var24 = 0; var28 != null; ++var24) {
            if (var29 == null) {
                System.out.println("    Run # " + var24 + " missing.  (Runs are indexed from zero.)");
                System.out.println("    (In other words, your nextRun() is returning null when it shouldn't.)");
                System.out.println("    Here's the correct ocean.");
                var13.toOcean().paint();
                var27 = 0;
                break;
            }

            if (var28[0] != var29[0] || var28[1] != var29[1]) {
                System.out.println("    Run # " + var24 + " should be " + var28[0] + ", " + var28[1] + ".  (Runs indexed from zero.)");
                System.out.println("    Instead, it's " + var29[0] + ", " + var29[1]);
                System.out.println("    Here's the correct ocean.");
                var13.toOcean().paint();
                var27 = 0;
                break;
            }

            var28 = var13.nextRun();
            var29 = var14.nextRun();
        }

        System.out.println("  Adding fish at (3, 2).");
        var13.addFish(3, 2);
        var14.addFish(3, 2);
        var13.restartRuns();
        var14.restartRuns();
        var28 = var13.nextRun();
        var29 = var14.nextRun();

        for(var24 = 0; var28 != null; ++var24) {
            if (var29 == null) {
                System.out.println("    Run # " + var24 + " missing.  (Runs are indexed from zero.)");
                System.out.println("    (In other words, your nextRun() is returning null when it shouldn't.)");
                System.out.println("    Here's the correct ocean.");
                var13.toOcean().paint();
                var27 = 0;
                break;
            }

            if (var28[0] != var29[0] || var28[1] != var29[1]) {
                System.out.println("    Run # " + var24 + " should be " + var28[0] + ", " + var28[1] + ".  (Runs indexed from zero.)");
                System.out.println("    Instead, it's " + var29[0] + ", " + var29[1]);
                System.out.println("    Here's the correct ocean.");
                var13.toOcean().paint();
                var27 = 0;
                break;
            }

            var28 = var13.nextRun();
            var29 = var14.nextRun();
        }

        System.out.println("  Adding fish at (1, 2).");
        var13.addFish(1, 2);
        var14.addFish(1, 2);
        var13.restartRuns();
        var14.restartRuns();
        var28 = var13.nextRun();
        var29 = var14.nextRun();

        for(var24 = 0; var28 != null; ++var24) {
            if (var29 == null) {
                System.out.println("    Run # " + var24 + " missing.  (Runs are indexed from zero.)");
                System.out.println("    (In other words, your nextRun() is returning null when it shouldn't.)");
                System.out.println("    Here's the correct ocean.");
                var13.toOcean().paint();
                var27 = 0;
                break;
            }

            if (var28[0] != var29[0] || var28[1] != var29[1]) {
                System.out.println("    Run # " + var24 + " should be " + var28[0] + ", " + var28[1] + ".  (Runs indexed from zero.)");
                System.out.println("    Instead, it's " + var29[0] + ", " + var29[1]);
                System.out.println("    Here's the correct ocean.");
                var13.toOcean().paint();
                var27 = 0;
                break;
            }

            var28 = var13.nextRun();
            var29 = var14.nextRun();
        }

        if (var27 == 1) {
            System.out.println("  Test successful.");
        }

        System.out.println();
        System.out.println("Total Part IV score:  " + var27 + " out of 1.");
        System.out.println("Total Autogradable score:  " + (2 * (var5 + var8 + var31 + var18) + var6 + var7 + var30 + var19 + var27) + " out of 13.");
    }
}
