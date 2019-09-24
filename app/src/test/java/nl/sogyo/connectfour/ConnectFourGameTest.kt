/**
 * ConnectFourGameTest.kt
 *
 * @since 18/09/19
 * @author Shan Shan Huang
 */

package nl.sogyo.connectfour

import junit.framework.TestCase
import org.junit.*

class ConnectFourGameTest {

    var gameBoard: GameBoard? = null

    @Before
    fun setUp() {
        gameBoard = GameBoard(7, 6)
    }

    @After
    fun tearDown() {
        gameBoard = null
    }

    @Test
    fun isInputValidTest() {
        TestCase.assertEquals(true, gameBoard!!.isInputValid(0))
        TestCase.assertEquals(true, gameBoard!!.isInputValid(6))
        TestCase.assertEquals(false, gameBoard!!.isInputValid(-10))
        TestCase.assertEquals(false, gameBoard!!.isInputValid(7))
    }

    @Test
    fun isDiscCorrectlyPlacedInGridCellTest() {
        TestCase.assertEquals(null, gameBoard!!.gridContainer[5][0].disc)
        gameBoard!!.dropDiscIntoColumn(0)
        TestCase.assertEquals(Disc(DiscColor.YELLOW), gameBoard!!.gridContainer[5][0].disc)
        TestCase.assertEquals(41, gameBoard!!.discs.size)

        TestCase.assertEquals(null, gameBoard!!.gridContainer[4][0].disc)
        gameBoard!!.dropDiscIntoColumn(0)
        TestCase.assertEquals(Disc(DiscColor.RED), gameBoard!!.gridContainer[4][0].disc)
        TestCase.assertEquals(40, gameBoard!!.discs.size)
    }

    @Test
    fun checkForHorizontalWinTest() {
        gameBoard!!.dropDiscIntoColumn(0)
        gameBoard!!.dropDiscIntoColumn(0)
        gameBoard!!.dropDiscIntoColumn(1)
        gameBoard!!.dropDiscIntoColumn(1)
        gameBoard!!.dropDiscIntoColumn(2)
        gameBoard!!.dropDiscIntoColumn(2)
        gameBoard!!.dropDiscIntoColumn(3)
        TestCase.assertTrue(gameBoard!!.isConnectedHorizontally())
    }

    @Test
    fun checkForVerticalWinTest() {
        gameBoard!!.dropDiscIntoColumn(0)
        gameBoard!!.dropDiscIntoColumn(1)
        gameBoard!!.dropDiscIntoColumn(0)
        gameBoard!!.dropDiscIntoColumn(1)
        gameBoard!!.dropDiscIntoColumn(0)
        gameBoard!!.dropDiscIntoColumn(1)
        gameBoard!!.dropDiscIntoColumn(0)
        TestCase.assertTrue(gameBoard!!.isConnectedVertically())
    }

    @Test
    fun checkForLeftDownToRightUpWinTest() {
        gameBoard!!.dropDiscIntoColumn(3)
        gameBoard!!.dropDiscIntoColumn(2)
        gameBoard!!.dropDiscIntoColumn(5)
        gameBoard!!.dropDiscIntoColumn(6)
        gameBoard!!.dropDiscIntoColumn(6)
        gameBoard!!.dropDiscIntoColumn(4)
        gameBoard!!.dropDiscIntoColumn(4)
        gameBoard!!.dropDiscIntoColumn(5)
        gameBoard!!.dropDiscIntoColumn(5)
        gameBoard!!.dropDiscIntoColumn(6)
        gameBoard!!.dropDiscIntoColumn(6)


        for (row in gameBoard!!.gridContainer) {
            row.forEach{ print("${it.disc?.color} \t\t" )}
            println()
        }

        TestCase.assertTrue(gameBoard!!.isConnectedLeftDownToRightUp())
    }

}