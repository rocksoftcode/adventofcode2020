def tickets = new File('seats.txt').text.split('\n')
def topHalf = { range -> range.subList(0, Math.round(range.size() / 2) as int) }
def bottomHalf = { range -> range.subList(Math.floor(range.size() / 2) as int, range.size()) }
def seats = []
tickets.each { code ->
	def range = new ArrayList(0..127)
	(0..6).each { i ->
		if (code[i] == 'F') range = topHalf(range)
		if (code[i] == 'B') range = bottomHalf(range)
	}
	def row = range[0]
	range = new ArrayList(0..7)
	(7..9).each { i ->
		if (code[i] == 'L') range = topHalf(range)
		if (code[i] == 'R') range = bottomHalf(range)
	}
	def seat = range[0]
	seats << row * 8 + seat
}
def seq = seats.min()..seats.max()
println seq + seats - seats.intersect(seq)

