package com.maroieqrwlk.unpi.white

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maroieqrwlk.unpi.R
import com.maroieqrwlk.unpi.databinding.ActivityGameBinding
import com.maroieqrwlk.unpi.white.game.Board
import com.maroieqrwlk.unpi.white.game.GameSettings
import com.maroieqrwlk.unpi.white.game.generators.FieldGenerationArguments
import com.maroieqrwlk.unpi.white.game.generators.RandomFieldGenerator
import com.maroieqrwlk.unpi.white.ui.BoardView
import com.maroieqrwlk.unpi.white.ui.SettingsActivity

class Game : AppCompatActivity() {

    private val prefs by lazy {
        this.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }

    private lateinit var binding: ActivityGameBinding
    private lateinit var settings: GameSettings
    private lateinit var board: Board

    private val generator = RandomFieldGenerator()
    private var started = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initialSetup(savedInstanceState)
        initGame()
    }


    private fun initGame() {
        resetLayout()
        updateHeader()
        binding.boardView.board = board
        settings = GameSettings.load(prefs)
        binding.boardView.settings = settings
        binding.boardView.moveListener = object : BoardView.OnMoveListener {
            override fun onMove(board: Board, state: Board.State) {
                when (state) {
                    Board.State.Win -> {
                        binding.boardView.isEnabled = false
                        AlertDialog.Builder(this@Game)
                            .setMessage(R.string.dialog_win)
                            .setPositiveButton(R.string.action_restart) { _, _ -> restartGame() }
                            .setNegativeButton(R.string.action_new_game) { _, _ -> newGame() }
                            .create()
                            .show()

                    }
                    Board.State.Loss -> {
                        binding.boardView.isEnabled = false
                        AlertDialog.Builder(this@Game)
                            .setMessage(R.string.dialog_lose)
                            .setPositiveButton(R.string.action_restart) { _, _ -> restartGame() }
                            .setNegativeButton(R.string.action_new_game) { _, _ -> newGame() }
                            .setNeutralButton(R.string.action_undo_last) { _, _ -> undo() }
                            .create()
                            .show()
                    }
                    Board.State.Neutral -> {
                        binding.boardView.isEnabled = true

                        updateHeader()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.game_menu, menu)
        return true
    }

    @SuppressLint("ResourceType")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.action_new -> newGame()
            R.id.action_restart -> restartGame()
            R.id.action_undo -> undo()
            else -> return false
        }
        return true
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.v("Minesweeper", "saving")
        with(outState) {
            putParcelable(KEY_BOARD, board)
        }
    }

    private fun updateHeader() {
        binding.textRemaining.text =
            getString(R.string.remaining_flags).format(board.mines - board.flagged)
    }


    private fun initialSetup(bundle: Bundle?) {
        started = false

        val stored = bundle?.getParcelable<Board>(KEY_BOARD)
        Log.v("Minesweeper", stored.toString())

        settings = GameSettings.load(prefs)
        val field = generator.generate(
            settings.rows,
            settings.columns,
            FieldGenerationArguments(settings.mines)
        )

        started = false
        board = Board(field)
        board.getStartedData()?.observe(this, {})
    }

    fun restartGame() {

        board.restart()
        resetLayout()
    }

    fun newGame() {

        initialSetup(null)
        resetLayout()
    }

    fun resetLayout() {
        binding.boardView.board = board
        updateHeader()
    }

    fun undo() {
        if (!binding.boardView.undo()) {
            Toast.makeText(
                this,
                R.string.empty_undo_stack,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}