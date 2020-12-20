import java.util.regex.Pattern

def input = new File('input.txt').text.split('\n\n')
def data = input[1].split('\n')
def rules = input[0].split('\n')
        .collectEntries {
            def parts = it.split(': ')
            def rule
            if (parts[1] ==~ /"[ab]"/) rule = [[letter : parts[1].replace('"', '')]]
            else rule = parts[1].split(' \\| ').collect {
                [paths: it.split(' ').collect { it.toInteger() }]
            }
            [parts[0].toInteger(), rule]
        }
def rex
rex =  { start -> { key ->
    def rule = start[key]
    if (rule[0].letter) return rule[0].letter
    def paths = rule.collect { it.paths.collect { rex(start)(it) }.join('') }
    "(${paths.join('|')})"
} }

def checker = Pattern.compile(rex(rules)(0))
println data.count { checker.matcher(it).matches() }