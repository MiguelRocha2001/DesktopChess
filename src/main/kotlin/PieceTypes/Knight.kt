package PieceTypes

import Board
import kotlin.math.abs


fun tryToMoveKnight(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    val player = board[move.cur.row.n][move.cur.column.n]!!.player
    val o = move.target.row.n
    val k = move.target.column.n
    val h = move.cur.row.n
    val g = move.cur.column.n

    val x = intArrayOf(2, 1, -1, -2, -2, -1, 1, 2)
    val y = intArrayOf(1, 2, 2, 1, -1, -2, -2, -1)

    // if the player chooses the same place
    if (move.target.row === move.cur.row && move.target.column === move.cur.column) return false

    for (i in 0..7) {
        if(move.cur.row.n + x[i] in 0..7 && move.cur.column.n + y[i] in 0..7 ) {
            val n = move.cur.row.n + x[i]
            val j = move.cur.column.n + y[i]
            if (board[move.target.row.n][move.target.column.n] !== null) {
                if (player === Board.Player.WHITE && board[move.target.row.n][move.target.column.n]!!.player === Board.Player.BLACK &&
                    move.target.row.n == move.cur.row.n + x[i] && move.target.column.n == move.cur.column.n + y[i]
                ) return true

                if (player === Board.Player.BLACK && board[move.target.row.n][move.target.column.n]!!.player === Board.Player.WHITE &&
                    move.target.row.n == move.cur.row.n + x[i] && move.target.column.n == move.cur.column.n + y[i]
                ) return true
            } else if(move.target.row.n == move.cur.row.n + x[i] && move.target.column.n == move.cur.column.n + y[i])
                return true
        }
    }
    return false
}