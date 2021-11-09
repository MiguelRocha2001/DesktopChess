import junit.framework.Assert
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class RookTest {
    @Test
    fun `Makes one step forward with Rook in Board`() {
        val sut = Board().makeMove("Ra1a2")
        assertNull(sut)
    }

    @Test
    fun `Makes two steps forward with Rook in Board`() {
        val sut = Board().makeMove("Ra1a3")
        assertNull(sut)
    }

    @Test
    fun `Moves some Pieces`() {
        val sut = Board().makeMove("Pa2a4")!!.makeMove("Pa7a5")!!.makeMove("Ra1a3")
        assertEquals(
            "rnbqkbnr"+
                    " ppppppp"+
                    "        "+
                    "p       "+
                    "P       "+
                    "R       "+
                    " PPPPPPP"+
                    " NBQKBNR", sut.toString() )
    }
}