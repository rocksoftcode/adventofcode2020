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
		[(it.split(/\d+\s/)[1]): new Integer(it.split(' ')[0])]
	}
}

def containers = rel.findAll { k, v -> v.find {it && it['shiny gold'] } }.keySet()
def count = containers.size()
def traverse
traverse = { color ->
	def children = rel.findAll { k, v -> v && v.find { it[color] } }
	if (children) {
		count++
		children.keySet().each { traverse(it) }
	}
}
containers.each { traverse(it) }
println count