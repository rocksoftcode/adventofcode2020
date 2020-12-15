def input = [19,0,5,1,10,13]
def hist = input.collect()
def last = input.last()
(hist.size()+1..2020).each {
    def spoken
    if (hist.lastIndexOf(last) < input.size()) {
        spoken = 0
    } else {
        def occ = hist.findIndexValues { it == last }.collect(Long::toInteger)
        spoken = occ.size() == 1 ? 0 : occ[-1] - occ[-2]
    }
    last = spoken
    hist << spoken
}
println last