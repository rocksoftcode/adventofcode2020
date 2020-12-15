def input = [19,0,5,1,10,13]
def hist = input.withIndex().collectEntries { v, i -> [v, [i+1]] }
def last = input.last()
(hist.size()+1..30000000).each {
    def log = hist[last]
    def spoken = !log || log.size() == 1 ? 0 : log[-1] - log[-2]
    if (!hist[spoken]) hist[spoken] = []
    hist[spoken] << it
    last = spoken
}
println last