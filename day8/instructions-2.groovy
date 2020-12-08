def instructions = new File('instructions.txt').text.split('\n')
def steps = instructions.collect {
	def step = it.split(' ')
	[step[0], step[1].replace('+', '') as Integer]
}
LinkedList<Integer> path = []
def execute = { rep ->
	def acc = 0
	def ptr = 0
	def log = []
	while (!log[ptr]) {
		if (ptr >= instructions.size()) {
			return acc
		}
		log[ptr] = 1
		def op = steps[ptr][0]
		if (ptr == rep && op in ['jmp', 'nop']) {
			op = op == 'jmp' ? 'nop' : 'jmp'
		}
		def arg = steps[ptr][1]
		if (op == 'nop') {
			ptr++
		} else if (op == 'acc') {
			acc += arg
			ptr++
		} else if (op == 'jmp') {
			ptr += arg
		}
		if (rep < 0) { path << ptr }
	}
	return -1
}

def acc = -1
execute(-1)
while (acc < 0 && path.size() > 0) {
	acc = execute(path.removeLast())
}
println acc