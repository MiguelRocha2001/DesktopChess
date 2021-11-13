package PieceTypes

import kotlin.math.abs

fun tryToMoveBishop(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    val player = board[move.curSquare.row.ordinal][move.curSquare.column.ordinal]!!.player

    // if the player chooses the same place
    if (move.newSquare.row === move.curSquare.row && move.newSquare.column === move.curSquare.column) return false

    //A valid movement occurs when the
    if(board[move.newSquare.row.ordinal][move.newSquare.column.ordinal] !== null) {
        if (player === Board.Player.WHITE && board[move.newSquare.row.ordinal][move.newSquare.column.ordinal]!!.player === Board.Player.BLACK
            && abs(move.curSquare.row.ordinal - move.newSquare.row.ordinal) == abs(move.curSquare.column.ordinal - move.newSquare.column.ordinal)) return true

        if (player === Board.Player.BLACK && board[move.newSquare.row.ordinal][move.newSquare.column.ordinal]!!.player === Board.Player.WHITE
            && abs(move.curSquare.row.ordinal - move.newSquare.row.ordinal) == abs(move.curSquare.column.ordinal - move.newSquare.column.ordinal)) return true
    }
    else return abs(move.curSquare.row.ordinal - move.newSquare.row.ordinal) == abs(move.curSquare.column.ordinal - move.newSquare.column.ordinal)
    return false
}