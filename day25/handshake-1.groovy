def input = new File('input.txt').text.split('\n')
def card = input[0].toLong()
def door = input[1].toLong()
def mod = { n, exp -> new BigInteger(n).modPow(new BigInteger(exp), new BigInteger(20201227)) as long }
def pad = 1000000L
def key = mod(7, pad)
def loopNum = pad
while (key != card && key != door) {
    key = (key * 7) % 20201227L
    loopNum++
}
println mod((key^card^door) as long, loopNum)