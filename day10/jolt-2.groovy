def input = new File('input.txt').text.split('\n').collect {Integer.parseInt(it)}.sort()
List<BigInteger> steps = [0, 0, 1] + [0] * input.last()
input.each {
	def step = it + 2
	steps[step] = new BigInteger(steps[step - 3..step].sum().toString())
}
println steps.last()

