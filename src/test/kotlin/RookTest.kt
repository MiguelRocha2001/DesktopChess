import junit.framework.Assert
import model.Board
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class RookTest {
    @Test
    fun `Makes one step forward with Rook in Board`() {
        val sut = Board().makeMove("Ra1a2")!!.first
        assertNull(sut)
    }

    @Test
    fun `Makes two steps forward with Rook in Board`() {
        val sut = Board().makeMove("Ra1a3")!!.first
        assertNull(sut)
    }

    @Test
    fun `Move Rook forward and to the side`() {
        val sut = Board()
            .makeMove("Pa2a4")!!.first//W
            .makeMove("Pa7a5")!!.first
            .makeMove("Ra1a3")!!.first//W
            .makeMove("Pb7b5")!!.first
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