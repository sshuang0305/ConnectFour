package nl.sogyo.connectfour

import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*


class MainActivity : AppCompatActivity() {

    val gameBoard = GameBoard(7, 6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showGridContainer()
    }

    private fun getDiscViewToPlay() {
        val discView = ImageView(this)
        for (disc in gameBoard.discContainer) {
            when {
                disc.myDisc -> discView.setImageResource(R.drawable.totoro_disc)
                else -> discView.setImageResource(R.drawable.soot_disc)
            }
        }
    }

    private fun showGridContainer() {
        for (row in gameBoard.gridContainer) {
            val rowsInGrid = TableRow(this)
            for (gridCell in row) {
                rowsInGrid.addView(createGridCellView(gridCell))
            }
            gridContainerLayout.addView(rowsInGrid)
        }
    }

    private fun createGridCellView(gridCell: GridCell): ImageView {
        val gridCellView = ImageView(this)
        gridCellView.setLayoutParams(TableRow.LayoutParams(125,125, 1f))
        gridCellView.scaleType = ImageView.ScaleType.FIT_XY
        when {
            gridCell.isEmpty() -> gridCellView.setImageResource(R.drawable.no_disc)
            gridCell.disc!!.myDisc -> gridCellView.setImageResource(R.drawable.totoro_disc)
            else -> gridCellView.setImageResource(R.drawable.soot_disc)
        }
        return gridCellView
    }
}
