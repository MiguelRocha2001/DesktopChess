package DataBase

import Moves

/**
 * Retrieves the document with the [gameId].
 */
fun getMoves(chessDb: ChessDb, gameId: String): Moves? {
    return try {
        chessDb.getDocument(gameId)
    } catch (ex: Exception) {
        null
    }
}

/**
 * Posts a new document containing the [content] String with the [gameId] associated
 */
fun postMoves(chessDb: ChessDb, gameId: String, content: String) {
    check(chessDb.insertDocument(Moves(gameId, content))) { "Post failed" }
}

/**
 * Replaces the content in the specific document given by the [gameId]
 */
fun replaceMoves(chessDb: ChessDb, gameId: String, content: String) {
    check(chessDb.replaceDocument(Moves(gameId, content))) { "Post failed" }
}

