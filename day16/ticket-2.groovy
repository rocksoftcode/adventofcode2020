def input = new File('input.txt').text.split('\n\n')
def rules = input[0].split('\n').collect {
    def parts = it.split(': ')
    def ranges = parts[1].split(' or ').collect {
        def ends = it.split('-')
        [ends[0].toInteger(), ends[1].toInteger()]
    }
    return [name: parts[0], rules: ranges]
}

def inRange = { x, r -> (x >= r[0][0] && x <= r[0][1]) || (x >= r[1][0] && x <= r[1][1]) }
def ticket = input[1].split('\n')[1].split(',')*.toInteger()
def nearby = input[2].split('\n')[1..-1].collect { it.split(',')*.toInteger() }
nearby.removeAll { it.findAll { x -> rules.every { !inRange(x, it.rules) } } }

def all = [ticket] + nearby
def poss = (0..all[0].size() - 1).collect { rules }
all.each { it.eachWithIndex { v, i -> poss[i] = poss[i].findAll { r -> inRange(v, r.rules) } } }

do {
    def complete = poss.findAll { it.size() == 1 }.collect { it[0].name }
    poss = poss.collect { it.size() == 1 ? it : it.findAll { r -> !complete.find { it == r.name } } }
    if (complete.size() == all[0].size()) break
} while (true)
println poss.flatten().withIndex().inject(1L) { acc, it -> it[0].name.startsWith('departure') ? ticket[it[1]] *= acc : acc }