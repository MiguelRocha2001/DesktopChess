
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
        board[7][0] = Rook(7,0)
        board[7][1] = Knight(7,1)
        board[7][2] = Bishop(7,2)
        board[7][3] = Queen(7,3)
        board[7][4] = King(7,4)
        board[7][5] = Bishop(7,5)
        board[7][6] = Knight(7,1)
        board[7][7] = Rook(7,7)
        for (i in 0..7) {
            board[6][i] = Pawn(6,i)
        }
    }

    /**
     * Iniciates the board fo the second player
     */
    private fun initPlayer2() {
        board[7][0] = Rook(0,0)
        board[7][1] = Knight(0,1)
        board[7][2] = Bishop(0,2)
        board[7][3] = Queen(0,3)
        board[7][4] = King(0,4)
        board[7][5] = Bishop(0,5)
        board[7][6] = Knight(0,1)
        board[7][7] = Rook(0,7)
        for (i in 0..7) {
            board[1][i] = Pawn(1,i)
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


    fun makeMove(move: Move): Board? {
        val cur = board[move.cline][move.ccol]
        if (cur == null) return null
        val new = cur.move(board,move.tline,move.tcol)
        if (new == null) return null
        // Creates a new array board
        val newBoard = board.clone()
        newBoard[move.cline][move.ccol] = null
        newBoard[move.tline][move.cline] = new
        return Board(newBoard)
    }
}