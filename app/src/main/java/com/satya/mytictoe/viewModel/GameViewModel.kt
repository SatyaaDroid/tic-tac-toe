package com.satya.mytictoe.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.satya.mytictoe.BoardCellValue
import com.satya.mytictoe.GameState
import com.satya.mytictoe.UserAction
import com.satya.mytictoe.VType

class GameViewModel : ViewModel() {

    var state by mutableStateOf(GameState())

    val boardItems: MutableMap<Int, BoardCellValue> = mutableMapOf(
        1 to BoardCellValue.NONE,
        2 to BoardCellValue.NONE,
        3 to BoardCellValue.NONE,
        4 to BoardCellValue.NONE,
        5 to BoardCellValue.NONE,
        6 to BoardCellValue.NONE,
        7 to BoardCellValue.NONE,
        8 to BoardCellValue.NONE,
        9 to BoardCellValue.NONE,

        )


    fun onAction(action: UserAction) {
        when (action) {
            is UserAction.BoardTapped -> {
                addValuesToBoard(action.CellNo)
            }
            UserAction.PlayAgainButtonClick -> {
                resetGame()
            }
        }
    }

    private fun resetGame() {
        boardItems.forEach { (i, _) ->
            boardItems[i] = BoardCellValue.NONE
        }
        state = state.copy(
            hintText = "Player 'O' turn",
            currentTurn = BoardCellValue.CIRCLE,
            hasWon = false,
            victoryType = VType.NONE
        )
    }

    private fun addValuesToBoard(cellNo: Int) {
        if (boardItems[cellNo] != BoardCellValue.NONE) {
            return
        }
        if (state.currentTurn == BoardCellValue.CIRCLE) {
            boardItems[cellNo] = BoardCellValue.CIRCLE
            if (checkWinner(BoardCellValue.CIRCLE)) {
                state = state.copy(
                    hintText = "Player 'O' Won",
                    playerCircleCount = state.playerCircleCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else {
                state = if (isBoardFull()) {
                    state.copy(
                        hintText = "Game Draw",
                        drawCount = state.drawCount + 1
                    )
                } else {
                    state.copy(
                        hintText = "Player 'X' Turn",
                        currentTurn = BoardCellValue.CROSS
                    )
                }
            }

        } else if (state.currentTurn == BoardCellValue.CROSS) {
            boardItems[cellNo] = BoardCellValue.CROSS
            if (checkWinner(BoardCellValue.CROSS)) {
                state = state.copy(
                    hintText = "Player 'X' Won",
                    playerCrossCount = state.playerCrossCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else {
                state = if (isBoardFull()) {
                    state.copy(
                        hintText = "Game Draw",
                        drawCount = state.drawCount + 1
                    )
                } else {
                    state.copy(
                        hintText = "Player 'O' Turn",
                        currentTurn = BoardCellValue.CIRCLE
                    )
                }
            }
        }
    }

    private fun checkWinner(
        boardValue: BoardCellValue
    ): Boolean {
        when {
            boardItems[1] == boardValue && boardItems[2] == boardValue && boardItems[3] == boardValue -> {
                state = state.copy(
                    victoryType = VType.HORIZONTAL1
                )
                return true
            }
            boardItems[4] == boardValue && boardItems[5] == boardValue && boardItems[6] == boardValue -> {
                state = state.copy(
                    victoryType = VType.HORIZONTAL2
                )
                return true
            }
            boardItems[7] == boardValue && boardItems[8] == boardValue && boardItems[9] == boardValue -> {
                state = state.copy(
                    victoryType = VType.HORIZONTAL3
                )
                return true
            }
            boardItems[1] == boardValue && boardItems[4] == boardValue && boardItems[7] == boardValue -> {
                state = state.copy(
                    victoryType = VType.VERTICAL1
                )
                return true
            }
            boardItems[2] == boardValue && boardItems[5] == boardValue && boardItems[8] == boardValue -> {
                state = state.copy(
                    victoryType = VType.VERTICAL2
                )
                return true
            }
            boardItems[3] == boardValue && boardItems[6] == boardValue && boardItems[9] == boardValue -> {
                state = state.copy(
                    victoryType = VType.VERTICAL3
                )
                return true
            }
            boardItems[1] == boardValue && boardItems[5] == boardValue && boardItems[9] == boardValue -> {
                state = state.copy(
                    victoryType = VType.DIAGONAL1
                )
                return true
            }
            boardItems[3] == boardValue && boardItems[5] == boardValue && boardItems[7] == boardValue -> {
                state = state.copy(
                    victoryType = VType.DIAGONAL2
                )
                return true
            }
            else -> return false
        }
    }

    private fun isBoardFull(): Boolean {
        return !boardItems.containsValue(BoardCellValue.NONE)
    }

}