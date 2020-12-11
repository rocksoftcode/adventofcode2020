def input = new File('input.txt').text.split('\n').collect { it.toCharArray().collect { it.toString() } }
def done = false
def occupiedAround = { grid, row, col ->
	def ordinals = [[-1, 0], [-1, -1], [0, -1], [1, -1], [1, 0], [1, 1], [0, 1], [-1, 1]]
	ordinals.count {
		def cRow = row + it[0]
		def cCol = col + it[1]
		while (cCol >= 0 && cCol < grid[0].size() && cRow >= 0 && cRow < grid.size()) {
			if (grid[cRow][cCol] == 'L') {
				return false
			} else if (grid[cRow][cCol] == '#') {
				return true
			}
			cRow += it[0]
			cCol += it[1]
		}
		return false
	}
}

def printGrid = { grid ->
	println ''
	println grid.collect{ it.join(' ') }.join('\n')
	println ''
}

def last = input
while (!done) {
	def grid = last.collect { it.collect() }
	last.eachWithIndex { row, i ->
		row.eachWithIndex{ v,  j ->
			def status = occupiedAround(last, i, j)
			if (v == 'L' && status == 0) grid[i][j] = '#'
			else if (v == '#' && status > 4) grid[i][j] = 'L'
			else grid[i][j] = v
		}
	}
	if (last.toString() == grid.toString()) done = true
	last = grid
}
printGrid(last)
println last.sum { it.count { it == '#' } }