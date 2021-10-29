
//enum class PieceType { K, Q, B, N, R, P, k, q, b, n, r, p }

abstract class Piece(val line: Int, val col: Int) {
    abstract fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean
    fun move(board: Array<Array<Piece?>>, l: Int, c: Int): Piece? {
        val new = Pawn(l,c)
        return if (new.valid(board,this)) new else null
    }
}

class Pawn(line: Int, col: Int): Piece(line, col) {
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (col != prev.col) return false
        if (line - prev.line > 2) return false
        return true
    }
}

class Knight(line: Int, col: Int): Piece(line, col) {
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (kotlin.math.abs(line - prev.line) == 1 && kotlin.math.abs(col - prev.col) == 2) return true
        if (kotlin.math.abs(line - prev.line) == 2 && kotlin.math.abs(col - prev.col) == 1) return true
        return false
    }

}

class Bishop(line: Int, col: Int): Piece(line, col) {
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (line - prev.line == kotlin.math.abs(col-prev.col)) return true
        return false
    }
}

class Rook(line: Int, col: Int): Piece(line, col) {
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (line == prev.line || col == prev.col) return true
        return false
    }
}

class Queen(line: Int, col: Int): Piece(line, col) {
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (line == prev.line || col == prev.col) return true
        if (board[line][col] != null) return false
        if (line - prev.line == kotlin.math.abs(col-prev.col)) return true
        return false
    }
}

class King(line: Int, col: Int): Piece(line, col) {
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (kotlin.math.abs(line - prev.line) <= 1 && kotlin.math.abs(col - prev.col) <= 1) return true
        return false
    }
}