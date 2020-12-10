def big = { new BigInteger(it) }
def input = new File('input.txt').text.split('\n').collect {big(it)}.sort()
def cache = [big(0),big(0),big(1)] + [big(0)] * input.last()
input.each {
	def step = it + big(2)
	cache[step] = cache[step - big(3)..step].sum()
}
println cache.last()

