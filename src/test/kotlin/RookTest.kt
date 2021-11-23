import junit.framework.Assert
import model.Board
import model.Player
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class RookTest {
    @Test
    fun `Move Rook forward and to the side`() {
        val sut = Board()
            .makeMove("Pa2a4",Player.WHITE)!!.first//W
            .makeMove("Pa7a5",Player.BLACK)!!.first
            .makeMove("Ra1a3",Player.WHITE)!!.first//W
            .makeMove("Pb7b5",Player.BLACK)!!.first
        assertEquals(
            "rnbqkbnr"+
                    "  pppppp"+
                    "        "+
                    "pp      "+
                    "P       "+
                    "R       "+
                    " PPPPPPP"+
                    " NBQKBNR", sut.toStringTest() )
    }
}