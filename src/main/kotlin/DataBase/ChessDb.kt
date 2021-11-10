package DataBase


import Move
import Player
import mongoDb.*
import org.apache.logging.log4j.message.Message

/**
 * The billboard basic operations.
 * Contract to be implemented by any concrete database.
 */
interface ChessDb {
    /**
     * Posts the [message] to the billboard
     * @param message   the message to be posted
     */
    fun postMessage(msg: Message): Boolean
    /**
     * Gets all messages posted on the billboard, regardless of their author
     * @return  the messages on the billboard
     */
    fun getAllMessages(): Iterable<Message>
    /**
     * Gets all messages posted on the billboard by [author]
     * @param [author] the author
     * @return  the messages from the given author
     */
    fun getMessagesByPlayer(author: Player): Iterable<Message>
}

/**
 * Implements the billboard operations using a MongoDB instance.
 * @property driver to access MongoDb
 */
class MongoChessCommands(val driver: MongoDriver): ChessDb {
    override fun postMessage(msg: Message) =
        driver.getCollection<Move>(msg.author.id).insertDocument(msg)

    override fun getAllMessages() =
        driver.getAllCollections<Message>().flatMap{ it.getAllDocuments() }

    override fun getMessagesByPlayer(author: Player) =
        driver.getCollection<Move>(author.id).getAllDocuments()
}