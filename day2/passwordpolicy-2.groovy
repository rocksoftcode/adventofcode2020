/**
 * Password audit:
 * Given a line like "5-13 j: jjjjjjcjjjjkjjjjj"
 * Check that either the 5th or 13th character is "j" (but not both) in the provided password
 */
class PolicyCheck {
	char letter
	int pos1
	int pos2
	String password
}

def policies = new File('password-input.txt').text.split('\n').collect {
	def line = it.split(' ')
	def limits = line[0].split('-')
	return new PolicyCheck(letter: line[1].charAt(0), pos1: limits[0].toInteger(), pos2: limits[1].toInteger(), password: line[2])
}

def valid = policies.findAll { check ->
	def pwd = check.password.toCharArray()
	return pwd[check.pos1 - 1] == check.letter ^ pwd[check.pos2 - 1] == check.letter
}

println valid.size()
println valid.collect { "$it.pos1-$it.pos2 $it.letter: $it.password"}.join('\n')
