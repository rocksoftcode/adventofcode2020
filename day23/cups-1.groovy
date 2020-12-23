def input = '167248359'
def cups = [0]
def arr = input.split('').collect { it.toInteger() }
arr.eachWithIndex{ v, i -> cups[v] = (v == arr[-1] ? arr[0] : arr[i+1]) }

def cup = input.substring(0, 1).toInteger()
100.times {
    def t1 = cups[cup]
    def t2 = cups[t1]
    def t3 = cups[t2]
    def dest = cup-1
    if (dest <= 0) dest = cups.size()-1
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

def o = ''
def i = 1
do {
    o+=cups[i]
    i=cups[i]
} while (i!=1)
println o[0..-2]