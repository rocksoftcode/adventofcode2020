def input = new File('input.txt').text.split('\n').collect { new BigInteger(it) }
final def PREAMBLE_SIZE = 25

(PREAMBLE_SIZE..input.size() -1).each { i ->
	def cur = input[i]
	def chunk = input.subList(i - PREAMBLE_SIZE, PREAMBLE_SIZE + i)
	def possible = chunk.collect {left ->
		def others = chunk.findAll { right -> left != right }
		others.collect { it + left }
	}.flatten()
	if (!possible.contains(cur)) {
		println cur
		System.exit 0
	}
}