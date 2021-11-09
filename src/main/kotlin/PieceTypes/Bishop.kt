package PieceTypes

import kotlin.math.abs

fun tryToMoveBishop(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    val player = board[move.cur.row.n][move.cur.column.n]!!.player

    // if the player chooses the same place
    if (move.target.row === move.cur.row && move.target.column === move.cur.column) return false

    //A valid movement occurs when the
    val x = board[move.target.row.n][move.target.column.n]
    if(board[move.target.row.n][move.target.column.n] !== null) {
        if (player === Board.Player.WHITE && board[move.target.row.n][move.target.column.n]!!.player === Board.Player.BLACK
            && abs(move.cur.row.n - move.target.row.n) == abs(move.cur.column.n - move.target.column.n)) return true

        if (player === Board.Player.BLACK && board[move.target.row.n][move.target.column.n]!!.player === Board.Player.WHITE
            && abs(move.cur.row.n - move.target.row.n) == abs(move.cur.column.n - move.target.column.n)) return true
    }
    else return abs(move.cur.row.n - move.target.row.n) == abs(move.cur.column.n - move.target.column.n)
    return false
}