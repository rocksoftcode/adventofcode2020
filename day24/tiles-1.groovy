def input = new File('input.txt').text.split('\n')
def dirs = [ne: [1, 1], nw: [-1, 1], se: [1, -1], sw: [-1, -1], e: [2, 0], w: [-2, 0]]
def tiles = [:]
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
    tiles["$x:$y"] = tiles["$x:$y"] ? !tiles["$x:$y"] : true
}
println tiles.count { it.value == true }