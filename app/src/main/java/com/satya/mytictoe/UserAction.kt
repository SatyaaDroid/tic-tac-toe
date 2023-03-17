package com.satya.mytictoe

sealed class UserAction {

    object PlayAgainButtonClick : UserAction()

    data class BoardTapped(val CellNo: Int) : UserAction()
}
