/**
 * Password audit:
 * Given a line like "5-13 j: jjjjjjcjjjjkjjjjj"
 * Check that the character "j" appears between 5 and 13 times in the provided password
 */
class PolicyCheck {
	char letter
	int minOccur
	int maxOccur
	String password
}

def policies = new File('password-input.txt').text.split('\n').collect {
	def line = it.split(' ')
	def limits = line[0].split('-')
	return new PolicyCheck(letter: line[1].charAt(0), minOccur: limits[0].toInteger(), maxOccur: limits[1].toInteger(), password: line[2])
}

def valid = policies.findAll { check ->
	def count = check.password.toCharArray().findAll { it == check.letter }.size()
	return count >= check.minOccur && count <= check.maxOccur
}

println valid.size()
println valid.collect { "$it.minOccur-$it.maxOccur $it.letter: $it.password"}.join('\n')
