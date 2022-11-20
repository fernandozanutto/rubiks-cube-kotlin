package cube

data class Face(var nodes: List<Node>) {

    val mainColor = nodes[4].color

    init {
        assert(nodes.size == 9)
    }

    lateinit var up: Face
    lateinit var down: Face
    lateinit var left: Face
    lateinit var right: Face

    fun setUpNeighbour(neighbour: Face) {
        up = neighbour
    }

    fun setLeftNeighbour(neighbour: Face) {
        left = neighbour
    }

    fun setRightNeighbour(neighbour: Face) {
        right = neighbour
    }

    fun setDownNeighbour(neighbour: Face) {
        down = neighbour
    }

    fun setNeighbours(up: Face, down: Face, left: Face, right: Face) {
        this.up = up
        this.down = down
        this.left = left
        this.right = right
    }

    fun rotateClockwise() {
        val newList = listOf(
            nodes[6],
            nodes[3],
            nodes[0],
            nodes[7],
            nodes[4],
            nodes[1],
            nodes[8],
            nodes[5],
            nodes[2]
        )

        nodes = newList

        val oldUpNodes = up.nodes
        val oldLeftNodes = left.nodes
        val oldRightNodes = right.nodes
        val oldDownNodes = down.nodes

        up.nodes = oldLeftNodes.subList(0, 3) + oldUpNodes.subList(3, 9)
        right.nodes = oldUpNodes.subList(0, 3) + oldRightNodes.subList(3, 9)
        down.nodes = oldRightNodes.subList(0, 3) + oldDownNodes.subList(3, 9)
        left.nodes = oldDownNodes.subList(0, 3) + oldLeftNodes.subList(3, 9)
    }


    fun rotateCounterClockwise() {
        val newList = listOf(
            nodes[2],
            nodes[5],
            nodes[8],
            nodes[1],
            nodes[4],
            nodes[7],
            nodes[0],
            nodes[3],
            nodes[6],
        )

        nodes = newList

        val oldUpNodes = up.nodes
        val oldLeftNodes = left.nodes
        val oldRightNodes = right.nodes
        val oldDownNodes = down.nodes

        up.nodes = oldRightNodes.subList(0, 3) + oldUpNodes.subList(3, 9)
        right.nodes = oldDownNodes.subList(0, 3) + oldRightNodes.subList(3, 9)
        down.nodes = oldLeftNodes.subList(0, 3) + oldDownNodes.subList(3, 9)
        left.nodes = oldUpNodes.subList(0, 3) + oldLeftNodes.subList(3, 9)
    }

    fun isFaceMonochromatic(): Boolean {
        return nodes.all {
            it.color == mainColor
        }
    }
}