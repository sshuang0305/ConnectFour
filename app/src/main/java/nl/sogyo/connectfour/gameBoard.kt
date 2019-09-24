/**
 * gameBoard.kt
 *
 * @since 18/09/19
 * @author Shan Shan Huang
 */
package nl.sogyo.connectfour

class GameBoard(private val width: Int, private val height: Int) {
    private val NO_DISCS_CONNECTED_FOR_WIN = 4
    val gridContainer = List(height) {row -> (List(width) {column -> GridCell(null)})}
    val discs = MutableList(height * width) {disc -> if ( disc % 2 == 0) Disc(DiscColor.YELLOW) else Disc(DiscColor.RED)}

    fun isInputValid(inputColumn: Int) = inputColumn in 0 until width
    private fun isSpaceAvailableInColumnForDisc(inputColumn: Int) = gridContainer[0][inputColumn].isEmpty()
    private fun getDiscToPlay() = discs.removeAt(0)

    private fun getGridCellFromInput(inputColumn: Int): GridCell {
        for (row in height - 1 downTo 1) {
            if (gridContainer[row][inputColumn].isEmpty()) return gridContainer[row][inputColumn]
        }
        return gridContainer[0][inputColumn]
    }

    fun dropDiscIntoColumn(inputColumn: Int) {
        if (isInputValid(inputColumn) && isSpaceAvailableInColumnForDisc(inputColumn)) {
            getGridCellFromInput(inputColumn).placeDisc(getDiscToPlay())
        }
    }

    private fun areSameDiscsInGridCells(gridCells: List<GridCell>): Boolean {
        val distinctDiscs = gridCells.map{it.disc}.distinct()
        return (distinctDiscs[0] != null && distinctDiscs.size == 1)
    }

    fun isConnectedHorizontally(): Boolean {
        for (row in height - 1 downTo 0) {
            for (col in 0 .. width - NO_DISCS_CONNECTED_FOR_WIN) {
                val gridCells = gridContainer[row].slice(col until col + NO_DISCS_CONNECTED_FOR_WIN)
                if (areSameDiscsInGridCells(gridCells)) {return true}
            }
        }
        return false
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

    fun isConnectedVertically(): Boolean {
        val transposedGridContainer = getTransposedGridContainer()
        for (row in transposedGridContainer) {
            for (col in 0 until NO_DISCS_CONNECTED_FOR_WIN - 1) {
                val gridCells = row.slice(col until col + NO_DISCS_CONNECTED_FOR_WIN)
                if (areSameDiscsInGridCells(gridCells)) return true
            }
        }
        return false
    }

    fun isConnectedLeftDownToRightUp(): Boolean {
        for (row in height - 1 downTo NO_DISCS_CONNECTED_FOR_WIN - 1) {
            for (col in 0..width - NO_DISCS_CONNECTED_FOR_WIN) {
                val gridCells = List(NO_DISCS_CONNECTED_FOR_WIN) { i -> gridContainer[row - i][col + i] }
                if (areSameDiscsInGridCells(gridCells)) return true
            }
        }
        return false
    }
}
