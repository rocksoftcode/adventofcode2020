def ops = ['-':'subtract','*':'multiply']
def input = new File('input.txt').text.split('\n')
println input.sum { eq ->
    def extr = { s, i ->
        def co = 1
        def j = i + 1
        while (co > 0) {
            if (s[j] == '(') co++
            if (s[j] == ')') co--
            j++
        }
        s.substring(i, j)
    }
    def solve = { s ->
        def p = s.split(' ') as LinkedList
        while (p.contains('+')) {
            def i = p.indexOf('+')
            p[i-1] = new BigDecimal(p[i-1]) + new BigDecimal(p[i+1])
            2.times { p.removeAt(i) }
        }
        while (p.size() >= 3) {
            def op = []
            3.times { op << p.pop() }
            p.addFirst(new BigDecimal(op[0])."${ops[op[1]]}"(new BigDecimal(op[2])).toString())
        }
        evaluate(p.join('')).toString()
    }
    while (eq.contains('(')) {
        def i = eq.indexOf('(')
        def r = extr(eq, i)
        String p = r[1..-2]
        while (p.contains('(')) {
            def j = p.indexOf('(')
            String p2 = extr(p, j)
            p = p.replace(p2, solve(p2[1..-2]))
        }
        eq = eq.replace(r, solve(p))
    }
    new BigDecimal(solve(eq))
}
