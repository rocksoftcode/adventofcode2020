def input = new File('input.txt').text.split('\n\n')
def groups = []
input.each {
	def lines = it.split('\n')
	def answers = [] as Set
	lines.each { line ->
		answers.addAll(line.toCharArray().collect { ''+it })
	}
	groups << answers
}

println groups.sum {
	it.size()
}
