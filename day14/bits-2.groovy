def input = new File('input.txt').text.split('\n') as List
def mask = input.pop().split(' = ')[1].toCharArray()
def mem = [:]
def indicesFor = { c, m -> m.findIndexValues { it == c }.collect(Long::toInteger) }
input.each {
    if (it.startsWith('mask')) {
        mask = it.split(' = ')[1].toCharArray()
    } else {
        def address = it.replace('mem[', '').replaceAll(/].*$/, '').toInteger()
        def value = it.split(' = ')[1].toLong()
        def binary = Integer.toBinaryString(address).padLeft(36, '0').toCharArray()
        indicesFor('1', mask).each {
            binary[it] = '1'
        }
        def xs = indicesFor('X', mask)
        if (xs.isEmpty()) {
            mem[Long.parseLong(new String(binary), 2)] = value as Long
        } else {
            (0..Math.pow(2, xs.size())).each {
                def p = Integer.toBinaryString(it).padLeft(xs.size(), '0')
                def addr = Arrays.copyOf(binary, binary.length)
                xs.eachWithIndex { x, i -> addr[x] = p[i] }
                mem[Long.parseLong(new String(addr), 2)] = value
            }
        }
    }
}
println mem.values().sum()
