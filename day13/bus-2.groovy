def numbers = new File('input.txt').text.split('\n')[1].split(',') as List
def offsets = numbers
        .withIndex()
        .collect { it, index -> [v: it == 'x' ? new BigDecimal(-1) : it as long, i: index as long] }
        .findAll { it.v > -1 }
        .collect { [v: it.v, i: it.i % it.v ]}
def product = offsets.inject(1) { p, o -> p * o.v }
def results = offsets.collect { (product / it.v) as long }
def limits = offsets.withIndex().collect { it, i ->
    def x = 1 as long
    while (((x * results[i]) as long % it.v) != 1) x++
    x
}
def i = 0
println offsets.inject(0 as long) { s, it ->
    def t = (it.v - it.i) * limits[i]
    while (t-- > 0) {
        s = (s + results[i]) % product
    }
    i++
    return s
}
