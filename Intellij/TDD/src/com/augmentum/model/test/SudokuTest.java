package com.augmentum.model.test;

import com.augmentum.model.Sudoku;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testng.annotations.BeforeTest;

/**
 * Created by Chester.Liu on 11/17/2016.
 */
public class SudokuTest {

    private Sudoku sudoku;
    private int enterNumber;
    private int xAxis;
    private int yAxis;
    private int[][] numberArray;

    @Before
    public void before() {
        sudoku = new Sudoku();
        enterNumber = 1;
        xAxis = 1;
        yAxis = 1;
    }

    @BeforeTest
    public void init() {
        enterNumber = 1;
        xAxis = 1;
        yAxis = 1;
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void giveEnterNumberWhenNumberLessThanOneThenThrowException() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("The enter number must greater than zero and less than ten.");
        enterNumber = 0;
        sudoku.playNumber(enterNumber, xAxis, yAxis);
    }

    @Test
    public void giveEnterNumberWhenNumberGreaterThanNineThenThrowException() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("The enter number must greater than zero and less than ten.");
        enterNumber = 10;
        sudoku.playNumber(enterNumber, xAxis, yAxis);
    }

    @Test
    public void giveXAxisWhenXAxisGreaterThanNineThenThrowException() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("The xAxis number must greater than zero and less than ten.");
        xAxis = 10;
        sudoku.playNumber(enterNumber, xAxis, yAxis);
    }

    @Test
    public void giveXAxisWhenXAxisLessThanOneThenThrowException() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("The xAxis number must greater than zero and less than ten.");
        xAxis = 0;
        sudoku.playNumber(enterNumber, xAxis, yAxis);
    }

    @Test
    public void giveYAxisWhenXAxisGreaterThanNineThenThrowException() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("The yAxis number must greater than zero and less than ten.");
        yAxis = 10;
        sudoku.playNumber(enterNumber, xAxis, yAxis);
    }

    @Test
    public void giveYAxisWhenXAxisLessThanOneThenThrowException() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("The yAxis number must greater than zero and less than ten.");
        yAxis = 0;
        sudoku.playNumber(enterNumber, xAxis, yAxis);
    }

    @Test
    public void giveEnterNumberAndXAxisWhenTheNumberIsExistInXAxisThenThrowException() throws Exception {
        xAxis = 1;
        enterNumber = 1;
        sudoku.playNumber(enterNumber, xAxis, yAxis);

        for (yAxis = 1; yAxis < 10; yAxis ++) {
            try {
                sudoku.playNumber(enterNumber, xAxis, yAxis);
                Assert.fail();
            } catch (Exception e){
            }
        }
    }

    @Test
    public void giveEnterNumberAndYAxisWhenTheNumberIsExistInYAxisThenThrowException() throws Exception {
        yAxis = 1;
        enterNumber = 1;
        sudoku.playNumber(enterNumber, xAxis, yAxis);

        for (xAxis = 1; xAxis < 10; xAxis ++) {
            try {
                sudoku.playNumber(enterNumber, xAxis, yAxis);
                Assert.fail();
            } catch (Exception e){
            }
        }
    }

    @Test
    public void giveEnterNumberAndXAxisAndYAxisWhenTheNumberIsExistInSameCellThenThrowException() throws Exception {
        sudoku.playNumber(1, 1, 1);
        sudoku.playNumber(1, 2, 4);
        sudoku.playNumber(1, 3, 7);
        sudoku.playNumber(1, 4, 2);
        sudoku.playNumber(1, 5, 5);
        sudoku.playNumber(1, 6, 8);
        sudoku.playNumber(1, 7, 3);
        sudoku.playNumber(1, 8, 6);
        sudoku.playNumber(1, 9, 9);

        enterNumber = 1;

        for (xAxis = 1; xAxis < 10; xAxis ++) {
            for (yAxis = 1; yAxis < 10; yAxis ++) {
                try {
                    sudoku.playNumber(enterNumber, xAxis, yAxis);
                    Assert.fail();
                } catch (Exception e){
                }
            }
        }
    }

    @Test
    public void giveEnterNumberAndXAxisAndYAxisWhenAnotherNumberIsExistThenThrowException() throws Exception {
        sudoku.playNumber(1, 1, 1);

        expectedException.expect(Exception.class);
        sudoku.playNumber(2, 1, 1);
    }

    /*@Test
    public void giveEnterNumberWhenNumberGreaterThanZeroAndLessThanTenThenReturnSudokuList() {
        for ( enterNumber = 1; enterNumber <= 9; enterNumber ++) {
            Assert.assertTrue(sudoku.playNumber(enterNumber, xAxis, yAxis));
        }
    }*/
}
