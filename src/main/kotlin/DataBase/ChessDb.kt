package DataBase


import Move
import Player
import mongoDb.*

/**
 * The billboard basic operations.
 * Contract to be implemented by any concrete database.
 */
interface ChessDb {
    /**
     * Posts the [message] to the billboard
     * @param message   the message to be posted
     */
    fun postMoves(move: Move): Boolean
    /**
     * Gets all messages posted on the billboard by [author]
     * @param [author] the author
     * @return  the messages from the given author
     */
    fun getMovesByPlayer(moves: Player): Iterable<Move>
}

/**
 * Implements the billboard operations using a MongoDB instance.
 * @property driver to access MongoDb
 */
class MongoChessCommands(val driver: MongoDriver): ChessDb {
    override fun postMoves(move: Move) =
        driver.getCollection<Move>(move.player.id).insertDocument(move)

    override fun getMovesByPlayer(player: Player) =
        driver.getCollection<Move>(player.id).getAllDocuments()
}