def input = new File('input.txt').text.split('\n').collect { it.toCharArray().collect { it.toString() } }
def done = false
def occupiedAround = { grid, row, col ->
	def spots = [[row - 1, col], [row - 1, col - 1], [row, col - 1], [row + 1, col - 1],
	             [row + 1, col], [row + 1, col + 1], [row, col + 1], [row - 1, col + 1]]
	return spots.count {
				(it[0] >= 0 && it[0] < grid.size()) &&
				(it[1] >= 0 && it[1] < grid[0].size()) &&
				grid[it[0]][it[1]] == '#'
	}
}

def printGrid = { grid ->
	println ''
	println grid.collect{ it.join(' ') }.join('\n')
	println ''
}

def last = input
while(!done) {
	def grid = last.collect { it.collect() }
	last.eachWithIndex { row, i ->
		row.eachWithIndex{ v,  j ->
			def status = occupiedAround(last, i, j)
			if (v == 'L' && status == 0) grid[i][j] = '#'
			else if (v == '#' && status > 3) grid[i][j] = 'L'
			else grid[i][j] = v
		}
	}
	if (last.toString() == grid.toString()) done = true
	last = grid
}

printGrid(last)
println last.sum { it.count { it == '#' } }