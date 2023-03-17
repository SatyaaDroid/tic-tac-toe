package com.satya.mytictoe

data class GameState(
    val playerCircleCount: Int = 0,
    val playerCrossCount: Int = 0,
    val drawCount: Int = 0,
    val hintText: String = "Player 'O' turn",
    val currentTurn: BoardCellValue = BoardCellValue.CIRCLE,
    val hasWon: Boolean = false,
    val victoryType: VType = VType.NONE
)

enum class BoardCellValue {
    CIRCLE,
    CROSS,
    NONE
}

enum class VType {
    NONE,
    HORIZONTAL1,
    HORIZONTAL2,
    HORIZONTAL3,
    VERTICAL1,
    VERTICAL2,
    VERTICAL3,
    DIAGONAL1,
    DIAGONAL2,

}