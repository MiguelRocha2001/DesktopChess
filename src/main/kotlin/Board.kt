
class Board {
    private val LINES = 8
    private val COLS = 8
    private val board: Array<Array<Piece?>>

    private val player = true

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
        initBlack()
        initWhite()
    }

    private fun initWhite() {
        val whitePos = 0
        board[whitePos][0] = Rook()
        board[whitePos][1] = Knight()
        board[whitePos][2] = Bishop()
        board[whitePos][3] = Queen()
        board[whitePos][4] = King()
        board[whitePos][5] = Bishop()
        board[whitePos][6] = Knight()
        board[whitePos][7] = Rook()
        for (i in 0..7) {
            board[whitePos+1][i] = Pawn()
        }
    }

    private fun initBlack() {
        board[7][0] = Rook()
        board[7][1] = Knight()
        board[7][2] = Bishop()
        board[7][3] = Queen()
        board[7][4] = King()
        board[7][5] = Bishop()
        board[7][6] = Knight()
        board[7][7] = Rook()
        for (i in 0..7) {
            board[6][i] = Pawn()
        }
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
            for (piece in board[line]) {
                if (piece != null)
                    str += piece.toStr()
                else str += ' '
            }
        }
        return str
    }


    fun makeMove(move: String): Board {
        if (!move.isMoveValid()) return this
        val cLine = move[2] - '0'
        val cCol = move[1] - 'a'
        val nLine = move[4] - '0'
        val nCol = move[3] - 'a'
        val piece = board[cLine][cCol] ?: return this

        val direction =
            if (nLine - cLine > 0) {
                if (nCol - cCol > 0) Dir.FRONT_LEFT
                if (nCol - cCol < 0) Dir.FRONT_RIGHT
                else Dir.FRONT
            }
            else if (nLine - cLine < 0) {
                if (nCol - cCol > 0) Dir.BACK_LEFT
                if (nCol - cCol < 0) Dir.BACK_RIGHT
                else Dir.BACK
            }
            else {
                if (nCol - cCol > 0) Dir.LEFT
                if (nCol - cCol < 0) Dir.RIGHT
                else null
            }

        // checks if the direction is valid
        val dirs = piece.getDirections()
        if (!dirs.contains(direction)) return this

        // Creates a new array board
        val newBoard = board.clone()
        newBoard[cLine][cCol] = null
        newBoard[nLine][nCol] = piece
        return Board(newBoard)
    }
}

private fun String.isMoveValid(): Boolean {
    val line = this.trim()
    if (!PieceTypes.contains(line[0])) return false
    val cLine = line[2]
    val cCol = line[1]
    val nLine = line[4]
    val nCol = line[3]
    // checks line
    if (cLine <= '1' || cLine >= '8' || nLine <= '1' || nLine >= '8' ) return false
    // checks col
    if (cCol <= 'a' || cCol >= 'h' || nCol <= 'a' || nCol >= 'h' ) return false
    return true
}