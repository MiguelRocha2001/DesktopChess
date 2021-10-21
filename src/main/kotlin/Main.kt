
class Board {
    enum class PieceType { K, Q, B, N, R, P, k, q, b, n, r, p }
    private class Piece(val type: PieceType, val line: Int, val col: Int)

    private val board: Map<Pair<Int,Int>,Piece?>
    private val LINES = 8
    private val COLS = 8

    constructor() {
        board = HashMap()
        init()
    }

    private constructor(board: Map<Pair<Int,Int>,Piece?>) {
        this.board = board
    }

    private fun init() {
        initPlayer1()
        initPlayer2()
    }

    private fun initPlayer2() {
        addPiece(PieceType.R, 1, 1)
        addPiece(PieceType.N, 1, 2)
        addPiece(PieceType.B, 1, 3)
        addPiece(PieceType.Q, 1, 4)
        addPiece(PieceType.K, 1, 5)
        addPiece(PieceType.B, 1, 6)
        addPiece(PieceType.N, 1, 7)
        addPiece(PieceType.R, 1, 8)
        for (i in 1..8) {
            addPiece(PieceType.P, 1, i)
        }
    }

    private fun initPlayer1() {
        addPiece(PieceType.r, 8, 1)
        addPiece(PieceType.n, 8, 2)
        addPiece(PieceType.b, 8, 3)
        addPiece(PieceType.q, 8, 4)
        addPiece(PieceType.k, 8, 5)
        addPiece(PieceType.b, 8, 6)
        addPiece(PieceType.n, 8, 7)
        addPiece(PieceType.r, 8, 8)
        for (i in 1..8) {
            addPiece(PieceType.p, 7, i)
        }
    }


    private fun addPiece(type: PieceType, line: Int, col: Int) {
        board.put(Pair(line,col-'a'), createPiece(type,line,col))
    }


    private fun createPiece(type: PieceType, line: Int, col: Int) = Piece(type, line, col)


    override fun toString(): String {
        var str = ""
        for (l in 1..LINES) {
            for (c in +1..COLS) {
                val piece = board.get(Pair(l,c))
                str = piece?.type.toString()?:" "
                if (piece != null)
                    str += piece.type
            }
        }
        return str
    }

    fun makeMove(move: String) {
        val type = move[0] // type of the piece
        val piece = board.get(Pair(move[2].toInt(),move[1]-'a')
        return Board(board + (Pair(move[2].toInt(),move[1]-'a', Piece(move[2].toInt(),move[1]-'a')))
    }
}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")

    val board = mapOf<Int,Int>()
    val boa = listOf<Int>()
    boa + 3
    board[] = 3
}