def input = new File("input.txt").text.split('\n')
def x = 0
def y = 0
def z = 0
def w = 0
def width = 0
def height = 0

def grid = [] as Set
input.each {
    it.split('').each {
        if (it == '#') grid << "$x:$y:$z:$w"
        x++
    }
    x = 0
    height = ++y
    width = it.length()
}

def neighborCount = { key ->
    def parts = key.split(':');
    def cx = parts[0].toInteger()
    def cy = parts[1].toInteger()
    def cz = parts[2].toInteger()
    def cw = parts[3].toInteger()
    def count = 0;
    (-1..1).each { xp ->
        (-1..1).each { yp ->
            (-1..1).each { zp ->
                (-1..1).each { wp ->
                    if (![xp, yp, zp, wp].every { it == 0 } && grid.contains("${cx + xp}:${cy + yp}:${cz + zp}:${cw + wp}")) count++
                }
            }
        }
    }
    count
}

def reps = 6
reps.times { i ->
    def shift = [] as Set
    (-reps - 1..width + reps + 1).each { cx ->
        (-reps - 1..height + reps + 1).each { cy ->
            (-reps - 1..reps + 1).each { cz ->
                (-reps - 1..reps + 1).each { cw ->
                    def key = "$cx:$cy:$cz:$cw"
                    def numNs = neighborCount(key)
                    def curr = grid.contains(key)
                    def next = false
                    if (curr) {
                        next = numNs in [2, 3]
                    } else {
                        next = numNs == 3
                    }
                    if (next) shift << key
                }
            }
        }
    }
    grid = shift
}

println grid.size()
