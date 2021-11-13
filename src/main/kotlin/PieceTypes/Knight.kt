package PieceTypes

import Board


fun tryToMoveKnight(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    val player = board[move.curSquare.row.ordinal][move.curSquare.column.ordinal]!!.player
    val x = intArrayOf(2, 1, -1, -2, -2, -1, 1, 2)
    val y = intArrayOf(1, 2, 2, 1, -1, -2, -2, -1)

    // if the player chooses the same place
    if (move.newSquare.row === move.curSquare.row && move.newSquare.column === move.curSquare.column) return false

    for (i in 0..7) {
        if(move.curSquare.row.ordinal + x[i] in 0..7 && move.curSquare.column.ordinal + y[i] in 0..7 ) {
            val n = move.curSquare.row.ordinal + x[i]
            val j = move.curSquare.column.ordinal + y[i]
            if (board[move.newSquare.row.ordinal][move.newSquare.column.ordinal] !== null) {
                if (player === Board.Player.WHITE && board[move.newSquare.row.ordinal][move.newSquare.column.ordinal]!!.player === Board.Player.BLACK &&
                    move.newSquare.row.ordinal == move.curSquare.row.ordinal + x[i] && move.newSquare.column.ordinal == move.curSquare.column.ordinal + y[i]
                ) return true

                if (player === Board.Player.BLACK && board[move.newSquare.row.ordinal][move.newSquare.column.ordinal]!!.player === Board.Player.WHITE &&
                    move.newSquare.row.ordinal == move.curSquare.row.ordinal + x[i] && move.newSquare.column.ordinal == move.curSquare.column.ordinal + y[i]
                ) return true
            } else if(move.newSquare.row.ordinal == move.curSquare.row.ordinal + x[i] && move.newSquare.column.ordinal == move.curSquare.column.ordinal + y[i])
                return true
        }
    }
    return false
}