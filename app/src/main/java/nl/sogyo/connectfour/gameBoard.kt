/**
 * gameBoard.kt
 *
 * @since 18/09/19
 * @author Shan Shan Huang
 */
package nl.sogyo.connectfour

const val DISCS_CONNECTED_FOR_WIN = 4

class GameBoard(private val width: Int, private val height: Int) {

    var gridContainer = List(height) {(List(width) {GridCell()})}
    val discContainer = MutableList(height * width) { disc -> if ( disc % 2 == 0) Disc(true) else Disc(false)}

    private fun isValidInput(inputColumn: Int) = inputColumn in 0 until width
    private fun isSpaceAvailableInColumn(inputColumn: Int) = gridContainer[0][inputColumn].isEmpty()
    private fun getDiscToPlay() = discContainer.removeAt(0)
    private fun getLowestAvailableGridCell(inputColumn: Int): GridCell {
        for (row in height - 1 downTo 1) {
            if (gridContainer[row][inputColumn].isEmpty()) return gridContainer[row][inputColumn]
        }
        return gridContainer[0][inputColumn]
    }

    fun dropDiscIntoColumn(inputColumn: Int) {
        if (isValidInput(inputColumn) && isSpaceAvailableInColumn(inputColumn)) {
            getLowestAvailableGridCell(inputColumn).placeDisc(getDiscToPlay())
        }
    }

    private fun areSameDiscsInGridCells(gridCells: List<GridCell>): Boolean {
        val distinctDiscs = gridCells.map{it.disc}.distinct()
        return (distinctDiscs[0] != null && distinctDiscs.size == 1)
    }

    private fun getTransposedGridContainer(): List< List<GridCell> > {
        val transposedGridContainer = mutableListOf< MutableList<GridCell> >()
        for (col in 0 until width) {
            val rowInTransposedGrid = mutableListOf<GridCell>()
            for (row in gridContainer) {
                rowInTransposedGrid.add(row[col])
            }
            transposedGridContainer.add(rowInTransposedGrid)
        }
        return transposedGridContainer
    }

    fun isConnectedHorizontally(): Boolean {
        for (row in gridContainer) {
            for (col in 0 .. width - DISCS_CONNECTED_FOR_WIN) {
                val gridCells = row.slice(col until col + DISCS_CONNECTED_FOR_WIN)
                if (areSameDiscsInGridCells(gridCells)) {return true}
            }
        }
        return false
    }

    fun isConnectedVertically(): Boolean {
        val transposedGridContainer = getTransposedGridContainer()
        for (row in transposedGridContainer) {
            for (col in 0 until width - DISCS_CONNECTED_FOR_WIN) {
                val gridCells = row.slice(col until col + DISCS_CONNECTED_FOR_WIN)
                if (areSameDiscsInGridCells(gridCells)) return true
            }
        }
        return false
    }

    fun isConnectedLeftDownToRightUp(): Boolean {
        for (row in height - 1 downTo DISCS_CONNECTED_FOR_WIN - 1) {
            for (col in 0..width - DISCS_CONNECTED_FOR_WIN) {
                val gridCells = List(DISCS_CONNECTED_FOR_WIN) { i -> gridContainer[row - i][col + i] }
                if (areSameDiscsInGridCells(gridCells)) return true
            }
        }
        return false
    }

    fun isConnectedRightDownToLeftUp(): Boolean {
        for (row in height - 1 downTo DISCS_CONNECTED_FOR_WIN - 1) {
            for (col in width - 1 downTo  DISCS_CONNECTED_FOR_WIN - 1) {
                val gridCells = List(DISCS_CONNECTED_FOR_WIN) { i -> gridContainer[row - i][col - i] }
                if (areSameDiscsInGridCells(gridCells)) return true
            }
        }
        return false
    }

    fun isGameFinished(): Boolean {
        return isConnectedHorizontally() || isConnectedVertically() || isConnectedLeftDownToRightUp() || isConnectedRightDownToLeftUp()
    }
}
