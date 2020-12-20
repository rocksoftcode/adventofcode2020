def input = new File('input.txt').text.split('\n\n')
def tiles = input.collectEntries {
    def lines = it.split('\n')
    def id = lines[0].split(' ')[1][0..-2].toLong()
    def image = lines[1..-1]
    def edges = [image[0], image.last(), image.collect { it[0] }.join(''), image.collect { it[-1] }.join('')]
    [id, edges]
}
def check = { r, v -> r[0] == v || r[1] == v || r[2] == v || r[3] == v}
def compare = { which ->
    def result = 0
    tiles.each {
        def matches = 0
        it.value.each {
            if (check(which.value, it)) matches++
            if (check(which.value, it.reverse())) matches++
        }
        if (matches < 4) result += matches
    }
    if (result == 2) return which.key
    return 1L
}
def product = 1L
tiles.each {
    product *= compare(it)
}
println product