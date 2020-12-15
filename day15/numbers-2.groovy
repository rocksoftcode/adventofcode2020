def input = [19,0,5,1,10,13]
def hist = input.withIndex().collectEntries { v, i -> [v, [i+1]] }
def last = input.last()
(hist.size()+1..30000000).each {
    def spoken
    def log = hist[last]
    if (!log) {
        spoken = 0
        if (!hist[0]) hist[0] = []
        hist[0] << it
    } else {
        spoken = log.size() == 1 ? 0 : log[-1] - log[-2]
        if (!hist[spoken]) hist[spoken] = []
        hist[spoken] << it
    }
    last = spoken
}
println last