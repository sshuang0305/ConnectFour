package nl.sogyo.connectfour

import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*


class MainActivity : AppCompatActivity() {

    private val gameBoard = GameBoard(7, 6)
    private var positionOfDiscToDrop = gameBoard.gridContainer[0].size / 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createDiscContainer()
        createGridContainer()
    }

    private fun createDiscContainer() {
        for (col in 0 until gameBoard.gridContainer[0].size) {
            val positionForDiscToDropView = createImageViewForDisc(R.drawable.possible_position_disc_todrop)
            discLayout.addView(positionForDiscToDropView)
        }
        placeDiscToDropInView()
    }

    private fun placeDiscToDropInView() {
        positionOfDiscToDrop = gameBoard.gridContainer[0].size / 2
        discLayout.removeViewAt(positionOfDiscToDrop)
        discLayout.addView(getDiscToDropView(), positionOfDiscToDrop)
    }

    private fun getDiscToDropView(): ImageView {
        val disc = gameBoard.discContainer[0]
        return when {
            disc.myDisc -> createImageViewForDisc(R.drawable.totoro_disc_todrop)
            else -> createImageViewForDisc(R.drawable.soot_disc_todrop)
        }
    }

    fun moveDiscToDropToLeftOrRight(view: View) {
        exchangeOldDiscForInvisibleDiscView()
        when (view.contentDescription) {
            "Left" -> positionOfDiscToDrop = modulo((positionOfDiscToDrop - 1), gameBoard.gridContainer[0].size)
            "Right" -> positionOfDiscToDrop = modulo((positionOfDiscToDrop + 1), gameBoard.gridContainer[0].size)
        }
        discLayout.removeViewAt(positionOfDiscToDrop)
        discLayout.addView(getDiscToDropView(), positionOfDiscToDrop)
    }

    private fun exchangeOldDiscForInvisibleDiscView() {
        discLayout.removeViewAt(positionOfDiscToDrop)
        val invisibleDiscView = createImageViewForDisc(R.drawable.possible_position_disc_todrop)
        discLayout.addView(invisibleDiscView, positionOfDiscToDrop)
    }

    fun dropDiscIntoColumn(view: View) {
        gameBoard.dropDiscIntoColumn(positionOfDiscToDrop)
        updateGameBoardAfterDiscDrop()
    }

    private fun updateGameBoardAfterDiscDrop() {
        gridContainerLayout.removeAllViews()
        createGridContainer()
        discLayout.removeAllViews()
        createDiscContainer()
    }

    private fun createGridContainer() {
        for (row in gameBoard.gridContainer) {
            val rowsInGrid = TableRow(this)
            for (gridCell in row) {
                rowsInGrid.addView(createGridCellView(gridCell))
            }
            gridContainerLayout.addView(rowsInGrid)
        }
    }

    private fun createGridCellView(gridCell: GridCell): ImageView {
        return when {
            gridCell.isEmpty() -> createImageViewForDisc(R.drawable.no_disc)
            gridCell.disc!!.myDisc -> createImageViewForDisc(R.drawable.totoro_disc)
            else -> createImageViewForDisc(R.drawable.soot_disc)
        }
    }

    private fun createImageViewForDisc(imageResource: Int): ImageView {
        val imageView = ImageView(this)
        imageView.setImageResource(imageResource)
        imageView.setLayoutParams(TableRow.LayoutParams(125,125, 1f))
        return imageView
    }

    private fun modulo(x: Int, y: Int): Int {
        var result = x % y
        if (result < 0) {result += y}
        return result
    }
}
