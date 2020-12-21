class Tile {
    def top
    def right
    def bottom
    def left
    def id
    def corner
    char[][] grid

    Tile(int id, char[][] grid) {
        this.grid = grid
        this.id = id
        corner = false
        top = new String(this.grid[0])
        right = new String(this.grid.collect { it[-1] } as char[])
        bottom = new String(this.grid[-1])
        left = new String(this.grid.collect { it[0] } as char[])
    }

    void flip() {
        char[][] copy = new char[grid.length][grid[0].length]
        for (int r = 0; r < copy.length; r++) {
            for (int c = 0; c < copy[0].length; c++) {
                copy[r][c] = grid[r][grid[0].length - 1 - c]
            }
        }

        String temp = left
        left = right
        right = temp
        top = top.reverse()
        bottom = bottom.reverse()
        grid = copy
    }

    def rotate(int i) {
        for (int j = 1; j <= i; j++) {
            char[][] copy = new char[grid.length][grid[0].length]
            for (int r = 0; r < copy.length; r++) {
                for (int c = 0; c < copy[0].length; c++) {
                    copy[r][c] = grid[grid.length - 1 - c][r]
                }
            }

            String temp = top
            top = left.reverse()
            left = bottom
            bottom = right.reverse()
            right = temp
            grid = copy
        }

    }

    void find() {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (c + 19 < grid[0].length && r + 2 < grid.length)
                    if (grid[r][c + 18] == '#' && grid[r + 1][c] == '#' && grid[r + 1][c + 5] == '#'
                            && grid[r + 1][c + 6] == '#' && grid[r + 1][c + 11] == '#' && grid[r + 1][c + 12] == '#'
                            && grid[r + 1][c + 17] == '#' && grid[r + 1][c + 18] == '#' && grid[r + 1][c + 19] == '#'
                            && grid[r + 2][c + 1] == '#' && grid[r + 2][c + 4] == '#' && grid[r + 2][c + 7] == '#'
                            && grid[r + 2][c + 10] == '#' && grid[r + 2][c + 13] == '#' && grid[r + 2][c + 16] == '#') {

                        grid[r][c + 18] = 'O'
                        grid[r + 1][c] = 'O'
                        grid[r + 1][c + 5] = 'O'
                        grid[r + 1][c + 6] = 'O'
                        grid[r + 1][c + 11] = 'O'
                        grid[r + 1][c + 12] = 'O'
                        grid[r + 1][c + 17] = 'O'
                        grid[r + 1][c + 18] = 'O'
                        grid[r + 1][c + 19] = 'O'
                        grid[r + 2][c + 1] = 'O'
                        grid[r + 2][c + 4] = 'O'
                        grid[r + 2][c + 7] = 'O'
                        grid[r + 2][c + 10] = 'O'
                        grid[r + 2][c + 13] = 'O'
                        grid[r + 2][c + 16] = 'O'

                    }
            }
        }

    }

}

def input = new File('input.txt').text.split('\n\n')
tiles = input.collect {
    def lines = it.split('\n')
    def id = lines[0].split('Tile ')[1][0..-2].toInteger()
    char[][] grid = lines[1..-1].collect { it.toCharArray() }
    return new Tile(id, grid)
}
for (int i = 0; i < tiles.size(); i++) {
    Tile t1 = tiles[i]
    int count = 0
    for (int j = 0; j < tiles.size(); j++) {
        if (i == j) continue

        Tile t2 = tiles[j]
        if (t1.top == t2.top) count++
        else if (t1.top == t2.right) count++
        else if (t1.top == t2.bottom) count++
        else if (t1.top == t2.left) count++
        else if (t1.top == t2.top.reverse()) count++
        else if (t1.top == t2.right.reverse()) count++
        else if (t1.top == t2.bottom.reverse()) count++
        else if (t1.top == t2.left.reverse()) count++
        else if (t1.right == t2.top) count++
        else if (t1.right == t2.right) count++
        else if (t1.right == t2.bottom) count++
        else if (t1.right == t2.left) count++
        else if (t1.right == t2.top.reverse()) count++
        else if (t1.right == t2.right.reverse()) count++
        else if (t1.right == t2.bottom.reverse()) count++
        else if (t1.right == t2.left.reverse()) count++
        else if (t1.bottom == t2.top) count++
        else if (t1.bottom == t2.right) count++
        else if (t1.bottom == t2.bottom) count++
        else if (t1.bottom == t2.left) count++
        else if (t1.bottom == t2.top.reverse()) count++
        else if (t1.bottom == t2.right.reverse()) count++
        else if (t1.bottom == t2.bottom.reverse()) count++
        else if (t1.bottom == t2.left.reverse()) count++
        else if (t1.left == t2.top) count++
        else if (t1.left == t2.right) count++
        else if (t1.left == t2.bottom) count++
        else if (t1.left == t2.left) count++
        else if (t1.left == t2.top.reverse()) count++
        else if (t1.left == t2.right.reverse()) count++
        else if (t1.left == t2.bottom.reverse()) count++
        else if (t1.left == t2.left.reverse()) count++
    }
    if (count == 2) {
        t1.corner = true
    }
}

def size = Math.sqrt(tiles.size()) as int
def puzzle = new Tile[size][size]

int ti = tiles.findIndexOf { it.corner }
puzzle[0][0] = tiles[ti]
tiles.remove(ti)

def matchTop = 0
def matchRight = 0
def matchBottom = 0
def matchLeft = 0
for (int i = 0; i < tiles.size(); i++) {
    def tlc = puzzle[0][0]
    def other = tiles[i]
    if (tlc.top == other.top) matchTop++
    else if (tlc.top == other.right) matchTop++
    else if (tlc.top == other.bottom) matchTop++
    else if (tlc.top == other.left) matchTop++
    else if (tlc.top == other.top.reverse()) matchRight++
    else if (tlc.top == other.right.reverse()) matchRight++
    else if (tlc.top == other.bottom.reverse()) matchRight++
    else if (tlc.top == other.left.reverse()) matchRight++

    if (tlc.right == other.top) matchRight++
    else if (tlc.right == other.right) matchRight++
    else if (tlc.right == other.bottom) matchRight++
    else if (tlc.right == other.left) matchRight++
    else if (tlc.right == other.top.reverse()) matchRight++
    else if (tlc.right == other.right.reverse()) matchRight++
    else if (tlc.right == other.bottom.reverse()) matchRight++
    else if (tlc.right == other.left.reverse()) matchRight++

    if (tlc.bottom == other.top) matchBottom++
    else if (tlc.bottom == other.right) matchBottom++
    else if (tlc.bottom == other.bottom) matchBottom++
    else if (tlc.bottom == other.left) matchBottom++
    else if (tlc.bottom == other.top.reverse()) matchBottom++
    else if (tlc.bottom == other.right.reverse()) matchBottom++
    else if (tlc.bottom == other.bottom.reverse()) matchBottom++
    else if (tlc.bottom == other.left.reverse()) matchBottom++

    if (tlc.left == other.top) matchLeft++
    else if (tlc.left == other.right) matchLeft++
    else if (tlc.left == other.bottom) matchLeft++
    else if (tlc.left == other.left) matchLeft++
    else if (tlc.left == other.top.reverse()) matchLeft++
    else if (tlc.left == other.right.reverse()) matchLeft++
    else if (tlc.left == other.bottom.reverse()) matchLeft++
    else if (tlc.left == other.left.reverse()) matchLeft++
}

if (matchTop == 0 && matchRight == 0) puzzle[0][0].rotate(3)
else if (matchRight == 0 && matchBottom == 0) puzzle[0][0].rotate(2)
else if (matchBottom == 0 && matchLeft == 0) puzzle[0][0].rotate(1)
for (int row = 0; row < puzzle.length; row++) {
    for (int col = 0; col < puzzle[0].length; col++) {
        if (puzzle[row][col] == null) {
            if (row == 0) {
                def edgeToMatch = puzzle[row][col - 1].right
                def i = 0
                def pieceFound = false
                while (!pieceFound) {
                    def t = tiles[i]
                    if (t.top == edgeToMatch) {
                        t.flip()
                        t.rotate(3)
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.right == edgeToMatch) {
                        t.flip()
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.bottom == edgeToMatch) {
                        t.rotate(1)
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.left == edgeToMatch) {
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.top.reverse() == edgeToMatch) {
                        t.rotate(3)
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.right.reverse() == edgeToMatch) {
                        t.rotate(2)
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.bottom.reverse() == edgeToMatch) {
                        t.flip()
                        t.rotate(1)
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.left.reverse() == edgeToMatch) {
                        t.flip()
                        t.rotate(2)
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    }
                    i++
                }
            } else {
                def edge = puzzle[row - 1][col].bottom
                int i = 0
                boolean pieceFound = false
                while (!pieceFound) {
                    Tile t = tiles[i]
                    if (t.top == edge) {
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.right == edge) {
                        t.rotate(3)
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.bottom == edge) {
                        t.flip()
                        t.rotate(2)
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.left == edge) {
                        t.rotate(1)
                        t.flip()
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.top.reverse() == edge) {
                        t.flip()
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.right.reverse() == edge) {
                        t.flip()
                        t.rotate(1)
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.bottom.reverse() == edge) {
                        t.rotate(2)
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    } else if (t.left.reverse() == edge) {
                        t.rotate(1)
                        puzzle[row][col] = t
                        tiles.remove(i)
                        pieceFound = true
                    }
                    i++
                }
            }
        }
    }
}

def image = new char[puzzle.length * 8][puzzle.length * 8]
int imgRow = 0
int imgCol = 0
for (int i = 0; i < puzzle.length; i++) {
    for (int j = 0; j < puzzle[0].length; j++) {
        Tile t = puzzle[i][j]
        char[][] g = t.grid
        imgRow = i * 8
        imgCol = j * 8
        for (int row = 1; row <= g.length - 2; row++) {
            for (int col = 1; col <= g[0].length - 2; col++) {
                image[imgRow][imgCol] = g[row][col]
                imgCol++
            }
            imgRow++
            imgCol = j * 8
        }
    }
}

def ordered = new Tile(0, image)
ordered.find()
ordered.rotate(1)
ordered.find()
ordered.rotate(1)
ordered.find()
ordered.rotate(1)
ordered.find()
ordered.rotate(1)
ordered.flip()
ordered.find()
ordered.rotate(1)
ordered.find()
ordered.rotate(1)
ordered.find()
ordered.rotate(1)
ordered.find()
println ordered.grid.sum { new String(it).findIndexValues { it == '#' }.size() }