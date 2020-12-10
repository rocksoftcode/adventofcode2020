def input = new File('input.txt').text.split('\n').collect {Integer.parseInt(it)}.sort()
def diff1 = 1, diff3 = 1 // bookends!
def findDiffs
findDiffs = {
	if (input.contains(it + 1)) {
		diff1++
		findDiffs(it + 1)
	} else if (input.contains(it + 3)) {
		diff3++
		findDiffs(it + 3)
	}
}
findDiffs(input[0])
println diff1 * diff3


