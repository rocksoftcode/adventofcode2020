def input = new File('input.txt').text.split('\n') as List
def mask = input.pop().split(' = ')[1].toCharArray()
def sums = [:]
input.each {
    if (it.startsWith('mask')) {
        mask = it.split(' = ')[1].toCharArray()
    } else {
        def address = it.replace('mem[', '').replaceAll(/].*$/, '').toInteger()
        def value = it.split(' = ')[1].toInteger()
        def binary = Integer.toBinaryString(value).padLeft(36, '0').toCharArray()
        mask.findIndexValues { it != 'X' }.collect(Long::toInteger).each {
            binary[it] = mask[it]
        }
        sums[address] = Long.parseLong(new String(binary), 2)
    }
}
println sums.values().sum()
