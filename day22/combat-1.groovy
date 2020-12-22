def input = new File('input.txt').text.split('\n\n')
def cards1 = input[0].split('\n')[1..-1].collect { it.toInteger() } as Deque
def cards2 = input[1].split('\n')[1..-1].collect { it.toInteger() } as Deque

while (cards1 && cards2) {
    def card1 = cards1.pop()
    def card2 = cards2.pop()
    if (card1 > card2) {
        cards1.add(card1)
        cards1.add(card2)
    } else if (card2 > card1) {
        cards2.add(card2)
        cards2.add(card1)
    }
}

def winner = cards2 ?: cards1
def sum = 0L
def i = 1L
while (winner) {
    sum += winner.removeLast() * i++
}
println sum