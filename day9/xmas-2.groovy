def input = new File('input.txt').text.split('\n').collect { new BigInteger(it) }
final def INVALID = 32321523

for (i in 0..input.size() -1) {
	(i..input.size() -1).inject(new BigDecimal(0)) { acc, j ->
		if (acc == INVALID) {
			def chunk = input.subList(i, j)
			println chunk.min() + chunk.max()
			System.exit 0
		}
		acc += input[j]
	}
}
