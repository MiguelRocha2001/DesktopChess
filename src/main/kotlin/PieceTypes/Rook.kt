package PieceTypes

fun tryToMoveRook(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    // if the player chooses the same place
    if (move.newSquare.row === move.curSquare.row && move.newSquare.column === move.curSquare.column) return false
    if (tryToMoveHorizontal(move,board)) return true
    return tryToMoveVertical(move,board)
}

private fun tryToMoveHorizontal(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    val player = board[move.curSquare.row.ordinal][move.curSquare.column.ordinal]!!.player
    var rowDif = move.newSquare.row.ordinal - move.curSquare.row.ordinal
    var colDif = move.newSquare.column.ordinal - move.curSquare.column.ordinal
    if (player===Board.Player.BLACK) {
        rowDif = -rowDif
        colDif = -colDif
    }
    // if the player tries to move vertically
    if (rowDif == 0) {
        if (colDif > 0) {
            // if can eat
            if (player===Board.Player.WHITE && board[move.newSquare.row.ordinal][move.newSquare.column.ordinal]!!.player===Board.Player.BLACK) return true
            if (player===Board.Player.BLACK && board[move.newSquare.row.ordinal][move.newSquare.column.ordinal]!!.player===Board.Player.WHITE) return true
        }
        if (colDif < 0) {
            for (i in move.curSquare.row.ordinal-1 downTo move.newSquare.row.ordinal) {
                if (board[move.curSquare.row.ordinal][i] != null)
                    return false
            }
            // if can eat
            if (player===Board.Player.WHITE && board[move.newSquare.row.ordinal][move.newSquare.column.ordinal]!!.player===Board.Player.BLACK) return true
            if (player===Board.Player.BLACK && board[move.newSquare.row.ordinal][move.newSquare.column.ordinal]!!.player===Board.Player.WHITE) return true
        }
    }
    return false
}

private fun tryToMoveVertical(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    val player = board[move.curSquare.row.ordinal][move.curSquare.column.ordinal]!!.player
    var rowDif = move.newSquare.row.ordinal - move.curSquare.row.ordinal
    var colDif = move.newSquare.column.ordinal - move.curSquare.column.ordinal
    if (player===Board.Player.BLACK) {
        rowDif = -rowDif
        colDif = -colDif
    }
    // if the player tries to move vertically
    if (colDif == 0) {
        if (rowDif > 0) {
            for (i in move.curSquare.row.ordinal+1..move.newSquare.row.ordinal) {
                if (board[i][move.curSquare.column.ordinal] != null)
                    return false
            }
            return true
        }
        if (rowDif < 0) {
            for (i in move.curSquare.row.ordinal-1 downTo move.newSquare.row.ordinal) {
                if (board[i][move.curSquare.column.ordinal] != null)
                    return false
            }
            return true
        }
    }
    return false
}
