package PieceTypes

fun tryToMoveRook(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    // if the player chooses the same place
    if (move.target.row === move.cur.row && move.target.column === move.cur.column) return false
    if (tryToMoveHorizontal(move,board)) return true
    return tryToMoveVertical(move,board)
}

private fun tryToMoveHorizontal(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    val player = board[move.cur.row.n][move.cur.column.n]!!.player
    var rowDif = move.target.row.n - move.cur.row.n
    var colDif = move.target.column.n - move.cur.column.n
    if (player===Board.Player.BLACK) {
        rowDif = -rowDif
        colDif = -colDif
    }
    // if the player tries to move vertically
    if (rowDif == 0) {
        if (colDif > 0) {
            // if can eat
            if (player===Board.Player.WHITE && board[move.target.row.n][move.target.column.n]!!.player===Board.Player.BLACK) return true
            if (player===Board.Player.BLACK && board[move.target.row.n][move.target.column.n]!!.player===Board.Player.WHITE) return true
        }
        if (colDif < 0) {
            for (i in move.cur.row.n-1 downTo move.target.row.n) {
                if (board[move.cur.row.n][i] != null)
                    return false
            }
            // if can eat
            if (player===Board.Player.WHITE && board[move.target.row.n][move.target.column.n]!!.player===Board.Player.BLACK) return true
            if (player===Board.Player.BLACK && board[move.target.row.n][move.target.column.n]!!.player===Board.Player.WHITE) return true
        }
    }
    return false
}

private fun tryToMoveVertical(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    val player = board[move.cur.row.n][move.cur.column.n]!!.player
    var rowDif = move.target.row.n - move.cur.row.n
    var colDif = move.target.column.n - move.cur.column.n
    if (player===Board.Player.BLACK) {
        rowDif = -rowDif
        colDif = -colDif
    }
    // if the player tries to move vertically
    if (colDif == 0) {
        if (rowDif > 0) {
            for (i in move.cur.row.n+1..move.target.row.n) {
                if (board[i][move.cur.column.n] != null)
                    return false
            }
            return true
        }
        if (rowDif < 0) {
            for (i in move.cur.row.n-1 downTo move.target.row.n) {
                if (board[i][move.cur.column.n] != null)
                    return false
            }
            return true
        }
    }
    return false
}
