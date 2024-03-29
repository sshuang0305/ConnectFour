/**
 * MainActivity.kt
 *
 * @since 18/09/19
 * @author Shan Shan Huang
 */

package nl.sogyo.connectfour

import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*


class MainActivity : AppCompatActivity() {

    private var gameBoard = GameBoard(7, 6)
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
        if (gameBoard.isGameEndedInATie() || gameBoard.isGameWonByPlayerOne() || gameBoard.isGameWonByPlayerTwo()) {
            showEndOfGame()
        }
        else {
            positionOfDiscToDrop = gameBoard.gridContainer[0].size / 2
            discLayout.removeViewAt(positionOfDiscToDrop)
            discLayout.addView(getDiscToDropView(), positionOfDiscToDrop)
        }
    }

    private fun showEndOfGame() {
        makeGameBoardLayoutInvisible()
        winnerLayout.addView(getEndOfGameTextView())
        winnerLayout.addView(getEndOfGameImageView())
    }

    private fun getEndOfGameTextView(): TextView {
        val endOfGameTextView = TextView(this)
        endOfGameTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 64f)
        endOfGameTextView.gravity =Gravity.CENTER
        when {
            gameBoard.isGameWonByPlayerOne() || gameBoard.isGameWonByPlayerTwo()-> endOfGameTextView.text = "WINNER"
            gameBoard.isGameEndedInATie() -> endOfGameTextView.text = "DRAW"
        }
        return endOfGameTextView
    }

    private fun getEndOfGameImageView(): ImageView {
        val endOfGameImageView = ImageView(this)
        val imageLayout = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        endOfGameImageView.layoutParams = imageLayout
        when {
            gameBoard.isGameEndedInATie() -> endOfGameImageView.setImageResource(R.drawable.no_winner)
            gameBoard.isGameWonByPlayerOne() -> endOfGameImageView.setImageResource(R.drawable.player1_winner)
            gameBoard.isGameWonByPlayerTwo() -> endOfGameImageView.setImageResource(R.drawable.player2_winner)
        }
        return endOfGameImageView
    }

    private fun makeGameBoardLayoutInvisible() {
        discLayout.visibility = View.GONE
        gameButtonsLayout.visibility = View.GONE
    }

    fun startNewGame(view: View) {
        gridContainerLayout.removeAllViews()
        winnerLayout.removeAllViews()
        discLayout.removeAllViews()
        gameBoard = GameBoard(7, 6)
        createDiscContainer()
        createGridContainer()
        gridContainerLayout.visibility = View.VISIBLE
        discLayout.visibility = View.VISIBLE
        gameButtonsLayout.visibility = View.VISIBLE
    }

    private fun getDiscToDropView(): ImageView {
        val disc = gameBoard.discContainer[0]
        return when {
            disc.myDisc -> createImageViewForDisc(R.drawable.player1_disc_todrop)
            else -> createImageViewForDisc(R.drawable.player2_disc_todrop)
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
            gridCell.disc!!.myDisc -> createImageViewForDisc(R.drawable.player1_disc)
            else -> createImageViewForDisc(R.drawable.player2_disc)
        }
    }

    private fun createImageViewForDisc(imageResource: Int): ImageView {
        val imageView = ImageView(this)
        imageView.setImageResource(imageResource)
        imageView.layoutParams = TableRow.LayoutParams(125,125, 1f)
        return imageView
    }

    private fun modulo(x: Int, y: Int): Int {
        var result = x % y
        if (result < 0) {result += y}
        return result
    }
}