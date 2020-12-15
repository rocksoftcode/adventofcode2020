def input = [19,0,5,1,10,13]
def hist = input.withIndex().collectEntries { v, i -> [v, i+1] }
def index = -1
def target = input.last()
for (def i = input.size(); i < 30000000; i++) {
    target = hist[target] ? i - hist[target] : 0
    hist[index] = i
    index = target
}
println target