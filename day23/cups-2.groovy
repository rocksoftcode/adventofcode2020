def input = '167248359'
def cups = new int[1000001]
def arr = input.split('').collect { it.toInteger() }
arr.eachWithIndex{ v, i -> cups[v] = (v == arr[-1] ? 10 : arr[i+1]) }
(10..1000000).each { cups[it] = it+1 }
cups[1000000] = arr[0]
def cup = input.substring(0, 1).toInteger()
10000000.times {
    def t1 = cups[cup]
    def t2 = cups[t1]
    def t3 = cups[t2]
    def dest = cup-1
    if (dest <= 0)
        dest = cups.size()-1
    while (dest == t1 || dest == t2 || dest == t3) {
        dest--
        if (dest <= 0) dest = cups.size()-1
    }
    cups[cup] = cups[t3]
    def temp = cups[dest]
    cups[dest] = t1
    cups[t3] = temp
    cup = cups[cup]
}
println cups[1] as long * cups[cups[1]] as long
