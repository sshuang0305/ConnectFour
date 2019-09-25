package nl.sogyo.connectfour

import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout;


class MainActivity : AppCompatActivity() {

    val gameBoard = GameBoard(7, 6)
    val gridContainer = gameBoard.gridContainer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showGridContainerView()

    }

    private fun showGridContainerView() {
        val param = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)

        for (row in gridContainer) {
            val rowsInGrid = LinearLayout(this)
            for (gridCell in row) {
                rowsInGrid.addView(createGridCellView(gridCell), param)
            }
            gridContainerLayout.addView(rowsInGrid)
        }
    }

    private fun createGridCellView(gridCell: GridCell): ImageView {
        val gridCellView = ImageView(this)

        when {
            gridCell.isEmpty() -> gridCellView.setImageResource(R.drawable.no_disc)
            gridCell.disc!!.myDisc -> gridCellView.setImageResource(R.drawable.soot_disc)
            else -> gridCellView.setImageResource(R.drawable.sootsprite)
        }
        return gridCellView
    }

}
