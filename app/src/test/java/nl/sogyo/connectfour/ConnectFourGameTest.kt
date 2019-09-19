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
        gameBoard!!.placeDiscInGridCell(0)
        TestCase.assertEquals(Disc(DiscColor.YELLOW), gameBoard!!.gridContainer[5][0].disc)
        TestCase.assertEquals(41, gameBoard!!.discs.size)

        TestCase.assertEquals(null, gameBoard!!.gridContainer[4][0].disc)
        gameBoard!!.placeDiscInGridCell(0)
        TestCase.assertEquals(Disc(DiscColor.RED), gameBoard!!.gridContainer[4][0].disc)
        TestCase.assertEquals(40, gameBoard!!.discs.size)
    }

    @Test
    fun checkForHorizontalWinTest() {
        gameBoard!!.placeDiscInGridCell(0)
        gameBoard!!.placeDiscInGridCell(0)
        gameBoard!!.placeDiscInGridCell(1)
        gameBoard!!.placeDiscInGridCell(1)
        gameBoard!!.placeDiscInGridCell(2)
        gameBoard!!.placeDiscInGridCell(2)
        gameBoard!!.placeDiscInGridCell(3)


//        for (row in gameBoard!!.gridContainer) {
//            row.forEach{print("${it.disc} ")}
//            println()
//        }
        TestCase.assertTrue(gameBoard!!.isFourConnectedHorizontally())
    }
}