package cube

import kotlin.test.Test
import kotlin.test.assertEquals

internal class FaceTest {

    @Test
    fun `Test neighbour nodes changes when rotate clock wise`() {
        val cube = Cube()
        cube.scramble()

        val faces = cube.faces
        val oldUpNodes = cube.faces.up.nodes
        val oldDownNodes = cube.faces.down.nodes
        val oldLeftNodes = cube.faces.left.nodes
        val oldRightNodes = cube.faces.right.nodes

        faces.front.rotateClockwise()

        assertEquals(oldRightNodes.subList(0, 3), faces.down.nodes.subList(0, 3))
        assertEquals(oldDownNodes.subList(3, 9), faces.down.nodes.subList(3, 9))

        assertEquals(oldUpNodes.subList(0, 3), faces.right.nodes.subList(0, 3))
        assertEquals(oldRightNodes.subList(3, 9), faces.right.nodes.subList(3, 9))

        assertEquals(oldLeftNodes.subList(0, 3), faces.up.nodes.subList(0, 3))
        assertEquals(oldUpNodes.subList(3, 9), faces.up.nodes.subList(3, 9))

        assertEquals(oldDownNodes.subList(0, 3), faces.left.nodes.subList(0, 3))
        assertEquals(oldLeftNodes.subList(3, 9), faces.left.nodes.subList(3, 9))
    }

    @Test
    fun `Test neighbour nodes changes when rotate counter clock wise`() {
        val cube = Cube()
        cube.scramble()

        val faces = cube.faces
        val oldUpNodes = cube.faces.up.nodes
        val oldDownNodes = cube.faces.down.nodes
        val oldLeftNodes = cube.faces.left.nodes
        val oldRightNodes = cube.faces.right.nodes

        faces.front.rotateCounterClockwise()

        assertEquals(oldRightNodes.subList(0, 3), faces.up.nodes.subList(0, 3))
        assertEquals(oldUpNodes.subList(3, 9), faces.up.nodes.subList(3, 9))

        assertEquals(oldUpNodes.subList(0, 3), faces.left.nodes.subList(0, 3))
        assertEquals(oldLeftNodes.subList(3, 9), faces.left.nodes.subList(3, 9))

        assertEquals(oldLeftNodes.subList(0, 3), faces.down.nodes.subList(0, 3))
        assertEquals(oldDownNodes.subList(3, 9), faces.down.nodes.subList(3, 9))

        assertEquals(oldDownNodes.subList(0, 3), faces.right.nodes.subList(0, 3))
        assertEquals(oldRightNodes.subList(3, 9), faces.right.nodes.subList(3, 9))
    }

    @Test
    fun `Test only self nodes when rotate clock wise`() {
        val originalColorList = listOf(
            Color.Blue,
            Color.Green,
            Color.Yellow,
            Color.Blue,
            Color.White,
            Color.Green,
            Color.Green,
            Color.Orange,
            Color.Green,
        )

        val nodes = originalColorList.map {
            Node(it)
        }

        val face = Face(nodes)
        val fakeFace = Face(List(9) { Node(Color.values().random()) })
        face.up = fakeFace
        face.down = fakeFace
        face.left = fakeFace
        face.right = fakeFace

        face.rotateClockwise()

        // 0 1 2      6 3 0
        // 3 4 5   -> 7 4 1
        // 6 7 8      8 5 2

        assertEquals(9, face.nodes.size)

        assertEquals(originalColorList[0], face.nodes[2].color)
        assertEquals(originalColorList[1], face.nodes[5].color)
        assertEquals(originalColorList[2], face.nodes[8].color)
        assertEquals(originalColorList[3], face.nodes[2].color)
        assertEquals(originalColorList[4], face.nodes[4].color)
        assertEquals(originalColorList[5], face.nodes[7].color)
        assertEquals(originalColorList[6], face.nodes[0].color)
        assertEquals(originalColorList[7], face.nodes[3].color)
        assertEquals(originalColorList[8], face.nodes[6].color)
    }

    @Test
    fun `Test self nodes when rotate counter clock wise`() {
        val originalColorList = listOf(
            Color.Blue,
            Color.Green,
            Color.Yellow,
            Color.Blue,
            Color.White,
            Color.Green,
            Color.Green,
            Color.Orange,
            Color.Green,
        )

        val nodes = originalColorList.map {
            Node(it)
        }

        val face = Face(nodes)
        val fakeFace = Face(List(9) { Node(Color.values().random()) })
        face.up = fakeFace
        face.down = fakeFace
        face.left = fakeFace
        face.right = fakeFace

        face.rotateCounterClockwise()

        // 0 1 2      2 5 8
        // 3 4 5   -> 1 4 7
        // 6 7 8      0 3 6

        assertEquals(9, face.nodes.size)

        assertEquals(originalColorList[0], face.nodes[6].color)
        assertEquals(originalColorList[1], face.nodes[3].color)
        assertEquals(originalColorList[2], face.nodes[0].color)
        assertEquals(originalColorList[3], face.nodes[7].color)
        assertEquals(originalColorList[4], face.nodes[4].color)
        assertEquals(originalColorList[5], face.nodes[1].color)
        assertEquals(originalColorList[6], face.nodes[8].color)
        assertEquals(originalColorList[7], face.nodes[5].color)
        assertEquals(originalColorList[8], face.nodes[2].color)
    }
}