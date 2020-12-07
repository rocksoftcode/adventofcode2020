def rules = new File('bags.txt').text
		.replaceAll(/\sbag[s]?,\s/, ',')
		.replaceAll(/\sbag[s]?\./, '')
		.split('\n')
def rel = [:]
rules.each {
	def parts = it.split(' bags contain ')
	def options = parts[1]
			.split(',')
	rel[parts[0]] = options.contains('no other') ? null : options.collect {
		new Tuple(it.split(/\d+\s/)[1], new Integer(it.split(' ')[0]))
	}
}

def traverse
def count = 0
traverse = { color, last ->
	if (rel[color]) {
		count += rel[color].sum { it[1] } * last
		rel[color].each { child ->
			traverse(child[0], last * rel[color].find { it[0] == child[0] }[1])
		}
	}
}
traverse('shiny gold', 1)

println count