def input = new File('input.txt').text.split('\n')

def lookup = [0: 'N', 90: 'E', 180: 'S', 270: 'W']
def dirsWaypoint = [N: 1, E: 10, S: 0, W: 0]
def dirs = [N: 0, E: 0, S: 0, W: 0]
def corr = { amt ->
	if (amt < 0) amt += 360
	if (amt >= 360) amt -= 360
	amt
}
def turn = { amt ->
	def shift = [N: lookup[corr(amt)], E: lookup[corr(90 + amt)], S: lookup[corr(180 + amt)], W: lookup[corr(270 + amt)]]
	dirsWaypoint = [
			N: dirsWaypoint[shift.find { it.value == 'N'}.key],
			E: dirsWaypoint[shift.find { it.value == 'E'}.key],
			S: dirsWaypoint[shift.find { it.value == 'S'}.key],
			W: dirsWaypoint[shift.find { it.value == 'W'}.key]
	]
}
input.each {
	def dir = it[0]
	def amt = it.substring(1).toInteger()
	if (dir == 'L') turn(amt * -1)
	else if (dir == 'R') turn(amt)
	else if (dir in ['N', 'E', 'S', 'W']) dirsWaypoint[dir] += amt
	else if (dir == 'F') {
		dirs.N += amt * dirsWaypoint.N
		dirs.E += amt * dirsWaypoint.E
		dirs.S += amt * dirsWaypoint.S
		dirs.W += amt * dirsWaypoint.W
	}
}
def manhattanDistance = Math.abs(dirs.N - dirs.S) + Math.abs(dirs.E - dirs.W)
println manhattanDistance