package cube

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

internal class CubeTest {

    @Test
    fun `Test that newly instantiated cube is done`() {
        assertTrue(Cube().isDone())
    }

    @Test
    fun `Test that newly rotating a new cube clockwise makes is not done`() {
        val cube = Cube()
        cube.faces.front.rotateClockwise()
        assertFalse(cube.isDone())
    }

    @Test
    fun `Test that newly rotating a new cube counter clock wise makes is not done`() {
        val cube = Cube()
        cube.faces.front.rotateCounterClockwise()
        assertFalse(cube.isDone())
    }

    @Test
    fun `Test Faces neighbour and location`() {
        val cube = Cube()

        val faces = cube.faces
        val frontColor = faces.front.mainColor
        val leftColor = faces.left.mainColor
        val rightColor = faces.right.mainColor
        val upColor = faces.up.mainColor
        val backColor = faces.back.mainColor
        val downColor = faces.down.mainColor

        val frontNeighboursColor = listOf(
            faces.up.down,
            faces.left.right,
            faces.right.left,
            faces.down.up,
        ).all {
            it.mainColor == frontColor
        }
        assertTrue(frontNeighboursColor)

        val leftNeighboursColor = listOf(
            faces.up.left,
            faces.down.left,
            faces.front.left,
            faces.back.right
        ).all {
            it.mainColor == leftColor
        }
        assertTrue(leftNeighboursColor)

        val rightNeighboursColor = listOf(
            faces.up.right,
            faces.down.right,
            faces.front.right,
            faces.back.left
        ).all {
            it.mainColor == rightColor
        }
        assertTrue(rightNeighboursColor)

        val upNeighboursColor = listOf(
            faces.front.up,
            faces.back.up,
            faces.left.up,
            faces.right.up
        ).all {
            it.mainColor == upColor
        }
        assertTrue(upNeighboursColor)

        val backNeighboursColor = listOf(
            faces.down.down,
            faces.up.up,
            faces.left.left,
            faces.right.right
        ).all {
            it.mainColor == backColor
        }
        assertTrue(backNeighboursColor)

        val downNeighboursColor = listOf(
            faces.front.down,
            faces.back.down,
            faces.left.down,
            faces.right.down
        ).all {
            it.mainColor == downColor
        }
        assertTrue(downNeighboursColor)
    }
}