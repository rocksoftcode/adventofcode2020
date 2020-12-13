def input = new File('input.txt').text.split('\n')
def time = input[0].toInteger()
def buses = input[1].split(',').findAll { it != 'x' }.collect { Integer.parseInt(it) }
def closest = [:]
buses.each {
	def slot = time
	while (slot % it != 0) { slot++ }
	closest[it] = slot - time
}
def choice = closest.entrySet().min { it.value }
println choice.key * choice.value
