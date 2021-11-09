import junit.framework.Assert
import org.junit.Test
import kotlin.test.assertNull

class RookTest {
    @Test
    fun `Makes one step forward with Rook in Board`() {
        val sut = Board().makeMove("Ra1a2")
        Assert.assertEquals(
            "rnbqkbnr" +
                    "pppppppp" +
                    "        " +
                    "        " +
                    "        " +
                    "        " +
                    "PPPPPPPP" +
                    "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `Makes two steps forward with Rook in Board`() {
        val sut = Board().makeMove("Ra1a3")
        Assert.assertEquals(
            "rnbqkbnr" +
                    "pppppppp" +
                    "        " +
                    "        " +
                    "        " +
                    "        " +
                    "PPPPPPPP" +
                    "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `Makes two steps forward with Pawn in Board`() {
        val sut = Board().makeMove("Pe2e4")
        Assert.assertEquals(
            "rnbqkbnr" +
                    "pppppppp" +
                    "        " +
                    "        " +
                    "    P   " +
                    "        " +
                    "PPPP PPP" +
                    "RNBQKBNR", sut.toString()
        )
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