def instructions = new File('instructions.txt').text.split('\n')

def acc = 0
def ptr = 0
def log = []
while (!log[ptr]) {
	log[ptr] = 1
	def instruction = instructions[ptr].split(' ')
	def op = instruction[0]
	def step = instruction[1].replace('+','') as Integer
	if (op == 'nop'){
		ptr++
	} else if (op == 'acc') {
		acc += step
		ptr++
	} else if (op == 'jmp') {
		ptr += step
	}
}
print acc