List<char[]> rows = new File('tree-slope.txt').text.split('\n').collect { it.toCharArray() }

int totalTrees = 0
int x = 0
int y = 0
for (def i = 0; i < rows.size() - 1; i++) {
	x += 3
	y += 1
	// This wasn't called out in the problem,
	// but if you get to the end of the row,
	// you have to "wrap around"
	if (x >= rows[y].size()) x -= rows[y].size()
	if (rows[y][x] == '#'.toCharacter()) totalTrees++
}

println totalTrees