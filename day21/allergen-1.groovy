def input = new File('input.txt').text.split('\n')
def allergens = [:]
def counts = [:]
def all = [] as Set

input.each {
    def sides = it.replace(')', '').split(' \\(contains ')
    def ingredients = sides[0].split(' ')
    def currAllergens = sides[1].split(', ')
    currAllergens.each { a ->
        if (allergens[a]) allergens[a] = ingredients.findAll { allergens[a].contains(it) } as Set
        else allergens[a] = ingredients as Set
    }
    ingredients.each {
        if (counts[it]) counts[it]++
        else counts[it] = 1
        all << it
    }
}
def resAllergens = [:]
def tAllergens = allergens.keySet()
while (tAllergens) {
    def allergen = tAllergens.find { allergens[it].size() == 1 }
    def ingredient = allergens[allergen].first()
    resAllergens[allergen] = ingredient
    allergens.remove(allergen)
    allergens.each {
        it.value.remove(ingredient)
    }
    tAllergens = allergens.keySet()
}

def resIngredients = [:]
resAllergens.each {
    resIngredients[resAllergens[it.key]] = it.key
}

def nonAllergenic = all.findAll { !resIngredients[it] }
println nonAllergenic.sum { counts[it] }