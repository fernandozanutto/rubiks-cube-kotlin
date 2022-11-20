package cube

import kotlin.random.Random

class Cube {
    var faces: List<Face> = getCleanFaces()

    init {
        assert(faces.size == 6)
    }

    fun reset() {
        faces = getCleanFaces()
    }

    fun isDone(): Boolean {
        return faces.all {
            it.isFaceMonochromatic()
        }
    }

    fun scramble() {
        repeat(1_000) {
            val faceNumber = Random.nextInt(0, 6)
            if (Random.nextInt() % 2 == 0) {
                faces[faceNumber].rotateClockwise()
            } else {
                faces[faceNumber].rotateCounterClockwise()
            }
        }
    }

    companion object {
        private fun getCleanFaces(): List<Face> {
            val colorList = Color.values()

            val faces = colorList.map { color ->
                List(9) {
                    Node(color)
                }
            }.map { nodeList ->
                Face(nodeList)
            }

            faces.front.setNeighbours(faces.up, faces.down, faces.left, faces.right)
            faces.up.setNeighbours(faces.back, faces.front, faces.left, faces.right)
            faces.left.setNeighbours(faces.up, faces.down, faces.back, faces.front)
            faces.right.setNeighbours(faces.up, faces.down, faces.front, faces.back)
            faces.back.setNeighbours(faces.up, faces.down, faces.right, faces.left)
            faces.down.setNeighbours(faces.front, faces.back, faces.left, faces.right)

            return faces
        }
    }
}

val List<Face>.front get() = this[0]
val List<Face>.up get() = this[1]
val List<Face>.left get() = this[2]
val List<Face>.right get() = this[3]
val List<Face>.back get() = this[4]
val List<Face>.down get() = this[5]