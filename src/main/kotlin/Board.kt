
class Move(val pieceType: Char, val cline: Int, val ccol: Int, val tline: Int, val tcol: Int)

class Board {

    private val LINES = 8
    private val COLS = 8
    private val board: Array<Array<Piece?>>

    constructor() {
        board = Array(LINES) { Array(COLS) { null } }
        init()
    }

    private constructor(board: Array<Array<Piece?>>) {
        this.board = board
    }


    /**
     * Iniciates the board
     */
    private fun init() {
        initPlayer1()
        initPlayer2()
    }

    /**
     * Iniciates the board fo the first player
     */
    private fun initPlayer1() {
        addPiece(PieceType.r, 7, 0)
        addPiece(PieceType.n, 7, 1)
        addPiece(PieceType.b, 7, 2)
        addPiece(PieceType.q, 7, 3)
        addPiece(PieceType.k, 7, 4)
        addPiece(PieceType.b, 7, 5)
        addPiece(PieceType.n, 7, 6)
        addPiece(PieceType.r, 7, 7)
        for (i in 0..7) {
            addPiece(PieceType.p, 6, i)
        }
    }

    /**
     * Iniciates the board fo the second player
     */
    private fun initPlayer2() {
        addPiece(PieceType.R, 0, 0)
        addPiece(PieceType.N, 0, 1)
        addPiece(PieceType.B, 0, 2)
        addPiece(PieceType.Q, 0, 3)
        addPiece(PieceType.K, 0, 4)
        addPiece(PieceType.B, 0, 5)
        addPiece(PieceType.N, 0, 6)
        addPiece(PieceType.R, 0, 7)
        for (i in 0..7) {
            addPiece(PieceType.P, 1, i)
        }
    }

    /**
     * Ads a new piece to the boad
     */
    private fun addPiece(type: PieceType, line: Int, col: Int) {
        board[line][col] = Piece(type, line, col)
    }

    /**
     * Indicates if the given line is empty
     */
    private fun howManyPiecesInLine(line: Int): Int {
        var count = 0
        for (piece in board[line])
            if (piece != null) ++count
        return count
    }

    /**
     * Converts the current state of the game in a String
     */
    override fun toString(): String {
        var str = ""
        for (line in board.indices) {
            if (howManyPiecesInLine(line) == 0) {
                str = ' ' + str
                continue
            }
            var aux = ""
            for (piece in board[line]) {
                aux += piece?.type?.toString() ?: ' '
            }
            // if the line has a single piece, the spaces are eliminated
            if (howManyPiecesInLine(line) == 1) {
                aux = aux.trim()
                aux = ' ' + aux + ' '
            }
            str = aux + str
        }
        return str
    }


    fun makeMove(move: Move): Board {
        val type = move[0] // type of the piece
        when {
            type == 'P' ->
        }
        val l1 = move[2]-'0'-1
        val c1 = move[1]-'a'
        val l2 = move[4]-'0'-1
        val c2 = move[3]-'a'
        val piece = board[l1][c1]
        val newBoard = board.clone()
        newBoard[l1][c1] = null
        newBoard[l2][c2] = piece
        return Board(newBoard)
    }

    private fun Pawn.move(line: Int, col: Int):  {
        val curLine = this.line
        val curCol = this.col
        when {
            col != curCol ret
        }
    }

}

fun main(args: Array<String>) {
    val arr1 = arrayOf(1,2,3,4,5,6)
    val arr2 = arr1.copyOf()
    println(arr1.asList())
    arr2[2] = 9
    println(arr2.asList())
}