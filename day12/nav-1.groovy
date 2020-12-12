def input = new File('input.txt').text.split('\n')

def dirs = [N: 0, E: 0, S: 0, W: 0]
def ord = 90
def turn = { amt ->
	ord += amt
	if (ord < 0) ord += 360
	if (ord > 360) ord -= 360
}
def currDir = {
	if (ord >= 0 && ord < 45 || ord > 270) return 'N'
	else if (ord >= 45 && ord <= 90) return 'E'
	else if (ord > 45 && ord <= 180) return 'S'
	else if (ord > 180 && ord <= 270) return 'W'
}
input.each {
	def dir = it[0]
	def amt = it.substring(1).toInteger()
	if (dir == 'L') turn(amt * -1)
	else if (dir == 'R') turn(amt)
	else if (dir in ['N', 'E', 'S', 'W']) dirs[dir] += amt
	else if (dir == 'F') dirs[currDir()] += amt
	println "$dir $amt .. ${currDir()} $dirs"
}
def manhattanDistance = Math.abs(dirs.N - dirs.S) + Math.abs(dirs.E - dirs.W)
println manhattanDistance