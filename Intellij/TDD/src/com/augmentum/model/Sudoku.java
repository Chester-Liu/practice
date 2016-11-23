package com.augmentum.model;

/**
 * Created by Chester.Liu on 11/17/2016.
 */
public class Sudoku {

    private int[][] numberArray;

    public int numberCount;

    private boolean isSuccess;

    public Sudoku() {
        numberArray = new int[9][9];
        numberCount = 0;
        isSuccess = false;
    }

    public void playNumber(int enterNumber, int xAxis, int yAxis) throws Exception {

        checkEnterNumberAndXAxisAndYAxisIsValid(enterNumber, xAxis, yAxis);

        checkTheEnterNumberIsExistInXAxisOrYAxisOrCell(enterNumber, xAxis, yAxis);

        numberArray[xAxis - 1][yAxis - 1] = enterNumber;
        numberCount ++;

        if (IsSuccess()) {
            printResult();
        }
    }

    private void checkEnterNumberAndXAxisAndYAxisIsValid(int enterNumber, int xAxis, int yAxis) throws Exception {
        if (enterNumber < 1 || enterNumber > 9) {
            throw new Exception("The enter number must greater than zero and less than ten.");
        }
        if (xAxis > 9 || xAxis < 1) {
            throw new Exception("The xAxis number must greater than zero and less than ten.");
        }

        if (yAxis > 9 || yAxis < 1) {
            throw new Exception("The yAxis number must greater than zero and less than ten.");
        }
    }

    private void checkTheEnterNumberIsExistInXAxisOrYAxisOrCell(int enterNumber, int xAxis, int yAxis) throws Exception {
        if (numberArray[xAxis - 1][yAxis - 1] != 0) {
            throw new Exception();
        }

        for (int i = 0; i < 9; i ++) {
            if (enterNumber == numberArray[xAxis - 1][i]) {
                throw new Exception();
            }
        }

        for (int i = 0; i < 9; i ++) {
            if (enterNumber == numberArray[i][yAxis - 1]) {
                throw new Exception();
            }
        }

        int xAxisCell = (xAxis - 1) / 3 * 3;
        int yAxisCell = (yAxis - 1) / 3 * 3;

        for (int i = xAxisCell; i < xAxisCell + 3; i ++) {
            for (int j = yAxisCell; j < yAxisCell + 3; j ++) {
                if (enterNumber == numberArray[i][j]) {
                    throw new Exception();
                }
            }
        }
    }

    public boolean IsSuccess() {
        if (numberCount == 81) {
            isSuccess = true;
        }

        return isSuccess;
    }

    public int getCount() {
        return numberCount;
    }

    public boolean isMaxNumberCell(int enterNumber) {
        int numberCount = 0;
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                if (enterNumber == numberArray[i][j]) {
                    numberCount ++;
                }
            }
        }

        if (numberCount < 9) {
            for (int i = 0; i < 9; i ++) {
                for (int j = 0; j < 9; j ++) {
                    try {
                        checkTheEnterNumberIsExistInXAxisOrYAxisOrCell(enterNumber, i, j);
                        return true;
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
            return false;
        }

        return false;
    }

    public void clearNumber(int clearNumber) {
        int clearNumberCount = 0;
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                if (clearNumber == numberArray[i][j]) {
                    numberArray[i][j] = 0;
                    clearNumberCount ++;
                }
            }
        }
        numberCount = numberCount - clearNumberCount;
    }

    private void printResult() {
        for (int i = 0; i < 9; i ++) {
            if (i == 0) {
                System.out.println("         Sudoku          ");
                System.out.println("-------------------------");
            }

            if (i == 3 || i == 6) {
                System.out.println("|-------|-------|-------|");
            }

            for (int j = 0; j < 9; j ++) {
                if (j == 0 || j == 3 || j == 6) {
                    System.out.print("| ");
                }
                System.out.print(numberArray[i][j] == 0 ? " " : numberArray[i][j]);
                System.out.print(" ");
            }

            System.out.println("|");
            if (i == 8) {
                System.out.println("-------------------------");
            }
        }
    }

    public static void main(String[] orgs) {
        Sudoku sudoku = new Sudoku();

        for (int i = 1; i < 10; i ++) {
            int j = 1;
            while (i * j <= 81 && j < 10) {
                try {
                    if (sudoku.isMaxNumberCell(i)) {
                        sudoku.playNumber(i, (int)(1 + Math.random() * 9), (int)(1 + Math.random() * 9));
                        //System.out.println("Progress: " + sudoku.getCount() + "/81.");
                        j ++;
                    } else {
                        sudoku.clearNumber(i);
                        j = 1;
                    }
                } catch (Exception e) {
                }
            }
        }
    }
}
