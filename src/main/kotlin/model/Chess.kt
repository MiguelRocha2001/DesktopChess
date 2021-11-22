package model

import DataBase.MongoChessCommands
import PieceType
import chess.model.Square
import toStr

enum class Player {
    WHITE, BLACK;
    fun advance() = if (this === WHITE) BLACK else WHITE
}

data class StatusGame(val board: Board?, val list: List<String>, val currentPlayer: Player?)

data class Move(val piece: PieceType, val curSquare: Square, val newSquare: Square) {
    override fun toString(): String {
       return piece.toStr() + curSquare.column.letter + curSquare.row.digit + newSquare.column.letter + newSquare.row.digit
    }
}

data class GameChess(val mongoChessCommands: MongoChessCommands, val gameId: String?, val player: Player?, val status: StatusGame)