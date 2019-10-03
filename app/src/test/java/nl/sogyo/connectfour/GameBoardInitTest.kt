/**
 * GameBoardInitTest.kt
 *
 * @since 18/09/19
 * @author Shan Shan Huang
 */

package nl.sogyo.connectfour

import junit.framework.TestCase.assertEquals
import org.junit.*
import org.junit.Assert.*

class GameBoardInitTest {

    private var gameBoard: GameBoard? = null

    @Before fun setUp() {
        gameBoard = GameBoard(7, 6)
    }

    @After fun tearDown() {
        gameBoard = null
    }

    @Test
    fun is7Column6RowBoardCreatedTest() {
        assertEquals(6, gameBoard!!.gridContainer.size)
        assertEquals(7, gameBoard!!.gridContainer[0].size)
    }

    @Test
    fun isGridCellEmptyOnInitTest() {
        assertEquals(true, gameBoard!!.gridContainer[0][0].isEmpty())
    }

    @Test
    fun isNumberOfDiscsCreatedCorrectlyTest() {
        assertEquals(42, gameBoard!!.discContainer.size)
    }

    @Test
    fun isMyAndOpponentsDiscsCreatedTest() {
        assertTrue(gameBoard!!.discContainer[0].myDisc)
        assertFalse(gameBoard!!.discContainer[1].myDisc)
    }
}