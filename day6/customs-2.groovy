def input = new File('input.txt').text.split('\n\n')
def count = 0
input.each {
	def lines = it.split('\n').collect { (it.toCharArray() as List).sort() }
	def all = lines.flatten().unique()
	count += all.count { letter -> lines.every { it.contains(letter) } }
}
println count
