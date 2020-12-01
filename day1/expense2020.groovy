/**
 * Find 3 numbers in the data that add up to 2020, print their product
 */
final def TARGET = 2020
def entries = new File('expense-input.txt').text.split('\n').collect {Integer.parseInt(it)}
entries.forEach {e1 ->
	def disjunct = entries.findAll {j -> j != e1}
	disjunct.forEach {e2 ->
		entries.findAll {j -> !(j in [e1, e2])}
				.forEach {e3 ->
					if (e1 + e2 + e3 == TARGET) {
						println e1 * e2 * e3
						System.exit 0
					}
				}
	}
}
