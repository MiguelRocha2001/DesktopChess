package PieceTypes

fun tryToMoveRook(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    // if the player chooses the same place
    if (move.target.row === move.cur.row && move.target.column === move.cur.column) return false
    if (tryToMoveHorizontal(move,board)) return true
    return tryToMoveVertical(move,board)
}

private fun tryToMoveHorizontal(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    val player = board[move.cur.row.n][move.cur.column.n]!!.player
    var colDif = move.target.column.n - move.cur.column.n
    if (player===Board.Player.BLACK) {
        colDif = -colDif
    }
    // if the player tries to MOVE vertically
    if (colDif == 0) {
        for (i in move.cur.column.n..move.target.column.n) {
            if (board[i][move.cur.column.n] != null)
                return false
        }
    }
    return false
}

private fun tryToMoveVertical(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    val player = board[move.cur.row.n][move.cur.column.n]!!.player
    var rowDif = move.target.row.n - move.cur.row.n
    if (player===Board.Player.BLACK) {
        rowDif = -rowDif
    }
    // if the player tries to MOVE horizontally
    if (rowDif == 0) {
        for (i in move.cur.row.n..move.target.row.n) {
            if (board[i][move.cur.column.n] != null)
                return false
        }
    }
    return false
}
