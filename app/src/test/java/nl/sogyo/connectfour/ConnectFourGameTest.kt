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
    fun isDiscDroppedCorrectlyInColumnTest() {
        TestCase.assertEquals(null, gameBoard!!.gridContainer[5][0].disc)
        gameBoard!!.dropDiscIntoColumn(0)
        TestCase.assertEquals(true, gameBoard!!.gridContainer[5][0].disc?.myDisc)
        TestCase.assertEquals(41, gameBoard!!.discContainer.size)

        TestCase.assertEquals(null, gameBoard!!.gridContainer[4][0].disc)
        gameBoard!!.dropDiscIntoColumn(0)
        TestCase.assertEquals(false, gameBoard!!.gridContainer[4][0].disc?.myDisc)
        TestCase.assertEquals(40, gameBoard!!.discContainer.size)
    }

    @Test
    fun checkForHorizontalWinTest() {
        gameBoard!!.gridContainer[5][0].placeDisc(Disc(true))
        gameBoard!!.gridContainer[5][1].placeDisc(Disc(true))
        gameBoard!!.gridContainer[5][2].placeDisc(Disc(true))
        gameBoard!!.gridContainer[5][3].placeDisc(Disc(true))
        TestCase.assertTrue(gameBoard!!.isConnectedHorizontally())
    }

    @Test
    fun checkForVerticalWinTest() {
        gameBoard!!.gridContainer[0][0].placeDisc(Disc(true))
        gameBoard!!.gridContainer[1][0].placeDisc(Disc(true))
        gameBoard!!.gridContainer[2][0].placeDisc(Disc(true))
        gameBoard!!.gridContainer[3][0].placeDisc(Disc(true))
        TestCase.assertTrue(gameBoard!!.isConnectedVertically())
    }

    @Test
    fun checkForLeftDownToRightUpWinTest() {
        gameBoard!!.gridContainer[5][3].placeDisc(Disc(true))
        gameBoard!!.gridContainer[4][4].placeDisc(Disc(true))
        gameBoard!!.gridContainer[3][5].placeDisc(Disc(true))
        gameBoard!!.gridContainer[2][6].placeDisc(Disc(true))
        TestCase.assertTrue(gameBoard!!.isConnectedLeftDownToRightUp())
    }

    @Test
    fun checkForRightDownToLeftUpWinTest() {
        gameBoard!!.gridContainer[2][0].placeDisc(Disc(true))
        gameBoard!!.gridContainer[3][1].placeDisc(Disc(true))
        gameBoard!!.gridContainer[4][2].placeDisc(Disc(true))
        gameBoard!!.gridContainer[5][3].placeDisc(Disc(true))
        TestCase.assertTrue(gameBoard!!.isConnectedRightDownToLeftUp())
    }

    @Test
    fun isGameFinishedTest() {
        TestCase.assertFalse(gameBoard!!.isGameFinished())
        gameBoard!!.gridContainer[2][0].placeDisc(Disc(true))
        gameBoard!!.gridContainer[3][1].placeDisc(Disc(true))
        gameBoard!!.gridContainer[4][2].placeDisc(Disc(true))
        gameBoard!!.gridContainer[5][3].placeDisc(Disc(true))
        TestCase.assertTrue(gameBoard!!.isGameFinished())
    }
}