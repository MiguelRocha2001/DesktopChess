import junit.framework.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertNull

class PawnTest {
    @Test
    fun `Makes one step forward with Pawn in Board`() {
        val sut = Board().makeMove("Pe2e3")
        assertEquals(
            "rnbqkbnr"+
                    "pppppppp"+
                    "        "+
                    "        "+
                    "        "+
                    "    P   "+
                    "PPPP PPP"+
                    "RNBQKBNR", sut.toString() )
    }

    @Test
    fun `Makes two steps forward with Pawn in Board`() {
        val sut = Board().makeMove("Pe2e4")
        assertEquals(
            "rnbqkbnr"+
                    "pppppppp"+
                    "        "+
                    "        "+
                    "    P   "+
                    "        "+
                    "PPPP PPP"+
                    "RNBQKBNR", sut.toString() )
    }

    @Test
    fun `Makes one step forward-left with Pawn in Board`() {
        val sut = Board().makeMove("Pe2d3")
        assertNull(sut)
    }

    @Test
    fun `Makes one step back with Pawn in Board`() {
        val sut = Board().makeMove("Pe2e1")
        assertNull(sut)
    }
}