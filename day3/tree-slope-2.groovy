def findTrees(xFactor, yFactor) {
	List<char[]> rows = new File('tree-slope.txt').text.split('\n').collect {it.toCharArray()}

	int totalTrees = 0
	int x = 0
	int y = 0
	for (def i = 0; i < rows.size() - 1 && y < rows.size() - 1; i++) {
		x += xFactor
		y += yFactor

		// This wasn't called out in the problem,
		// but if you get to the end of the row,
		// you have to "wrap around"
		if (x >= rows[y].size()) x -= rows[y].size()
		if (rows[y][x] == '#'.toCharacter()) totalTrees++
	}

	totalTrees
}

// Bigger that Integer.MAX_VALUE - gotta use BigInteger
BigInteger p1 = findTrees(1, 1)
BigInteger p2 = findTrees(3, 1)
BigInteger p3 = findTrees(5, 1)
BigInteger p4 = findTrees(7, 1)
BigInteger p5 = findTrees(1, 2)

println p1.multiply(p2).multiply(p3).multiply(p4).multiply(p5)
