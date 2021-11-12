package DataBase


import Moves
import Game
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
    fun postMoves(moves: Moves): Boolean
    /**
     * Gets all messages posted on the billboard by [author]
     * @param [author] the author
     * @return  the messages from the given author
     */
    fun getMovesByPlayer(moves: Game): Iterable<Moves>
}

/**
 * Implements the billboard operations using a MongoDB instance.
 * @property driver to access MongoDb
 */
class MongoChessCommands(val driver: MongoDriver): ChessDb {
    override fun postMoves(moves: Moves) =
        driver.getCollection<Moves>(moves.game.id).insertDocument(moves)

    override fun getMovesByPlayer(game: Game) =
        driver.getCollection<Moves>(game.id).getAllDocuments()
}