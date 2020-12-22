def input = new File('input.txt').text.split('\n\n')
def cards1 = input[0].split('\n')[1..-1].collect { it.toInteger() } as Deque
def cards2 = input[1].split('\n')[1..-1].collect { it.toInteger() } as Deque

def cstr = { c1, c2 -> c1.join(' ') + ' | ' + c2.join(' ') }
def play
play = { c1, c2 ->
    def rounds = [cstr(c1, c2)]
    while (c1 && c2) {
        def card1 = c1.pop()
        def card2 = c2.pop()
        if (rounds.toSet().size() < rounds.size()) {
            return 1
        } else if (card1 <= c1.size() && card2 <= c2.size()) {
            def s1 = c1.subList(0, card1).collect()
            def s2 = c2.subList(0, card2).collect()
            def winner = play(s1 as Deque, s2 as Deque)
            if (winner == 1) {
                c1 << card1
                c1 << card2
            } else {
                c2 << card2
                c2 << card1
            }
        } else if (card1 > card2) {
            c1 << card1
            c1 << card2
        } else if (card2 > card1) {
            c2 << card2
            c2 << card1
        }
        rounds << cstr(c1, c2)
    }
    return c1 ? 1 : 2
}

play(cards1, cards2)
def winner = cards2 ?: cards1
def sum = 0L
def i = 1L
while (winner) {
    sum += winner.removeLast() * i++
}
println sum