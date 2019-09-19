/**
 * gameBoard.kt
 *
 * @since 18/09/19
 * @author Shan Shan Huang
 */
package nl.sogyo.connectfour

class GameBoard(val width: Int, val height: Int) {
    val NO_DISCS_CONNECTED_FOR_WIN = 4
    val gridContainer = List(height) {row -> (List(width) {column -> GridCell(null)})}
    val discs = MutableList(height * width) {disc -> if ( disc % 2 == 0) Disc(DiscColor.YELLOW) else Disc(DiscColor.RED)}

    fun isInputValid(inputColumn: Int) = inputColumn in 0 until width
    fun isSpaceAvailableInColumnForDisc(inputColumn: Int) = gridContainer[0][inputColumn].isEmpty()
    fun getDiscToPlay() = discs.removeAt(0)

    fun getGridCellFromInput(inputColumn: Int): GridCell {
        for (row in height - 1 downTo 1) {
            if (gridContainer[row][inputColumn].isEmpty()) return gridContainer[row][inputColumn]
        }
        return gridContainer[0][inputColumn]
    }

    fun placeDiscInGridCell(inputColumn: Int) {
        if (isInputValid(inputColumn) && isSpaceAvailableInColumnForDisc(inputColumn)) {
            getGridCellFromInput(inputColumn).placeDisc(getDiscToPlay())
        }
    }

    fun isFourConnectedHorizontally(): Boolean {
        for (row in height - 1 downTo 0) {
            for (col in 0 .. width - (NO_DISCS_CONNECTED_FOR_WIN)) {
                val fourGridCells = gridContainer[row].slice(col until col + NO_DISCS_CONNECTED_FOR_WIN)
            }
        }
        return true
    }
}
