def input = new File('input.txt').text.split('\n')
def dirs = [ne: [1, 1], nw: [-1, 1], se: [1, -1], sw: [-1, -1], e: [2, 0], w: [-2, 0]]
def tiles = []
def coord = { x, y -> "$x:$y".toString() }
input.each {
    def x = 0, y = 0
    def path = it.split('') as List
    while (path) {
        def d = path.remove(0)
        if (!(d in ['e', 'w'])) {
            d += path.remove(0)
        }
        x += dirs[d][0]
        y += dirs[d][1]
    }
    if (tiles.contains(coord(x,y))) {
        tiles.remove(coord(x,y))
    } else {
        tiles << coord(x,y)
    }
}
def neighbors = { t ->
    def pos = t.split(':').collect { it.toInteger() }
    dirs.collect { coord(pos[0] + it.value[0], pos[1] + it.value[1]) }
}
def turn = { t ->
    def counts = [:]
    t.each {
        neighbors(it).each {
            counts[it] = (counts[it] ?: 0) + 1
        }
    }

    def out = [] as Set
    counts.each { if ((t.contains(it.key) && it.value == 1) || it.value == 2) out << it.key }
    out
}
100.times { tiles = turn(tiles) }
println tiles.size()

