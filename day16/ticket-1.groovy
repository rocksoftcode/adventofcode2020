def input = new File('input.txt').text.split('\n\n')
def rules = input[0].split('\n').collectEntries {
    def parts = it.split(': ')
    def ranges = parts[1].split(' or ').collect {
        def ends = it.split('-')
        (ends[0].toInteger()..ends[1].toInteger())
    }
    return [parts[0], ranges]
}

def nearby = input[2].split('\n')[1..-1].collect { it.split(',')*.toInteger() }
def invalids = []
nearby.each {
    invalids.addAll(it.findAll { x ->
        rules.values().every {
            !it[0].containsWithinBounds(x) && !it[1].containsWithinBounds(x)
        }
    })
}
println invalids.sum()