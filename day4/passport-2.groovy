def credentials = new File('credentials.txt').text.split('\n\n')
		.collect {
			def datum = it.split('\\s')
			datum.collectEntries {
				def item = it.split(':')
				[item[0], item[1]]
			}
		}

/**
 byr (Birth Year) - four digits; at least 1920 and at most 2002.
 iyr (Issue Year) - four digits; at least 2010 and at most 2020.
 eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
 hgt (Height) - a number followed by either cm or in:
 If cm, the number must be at least 150 and at most 193.
 If in, the number must be at least 59 and at most 76.
 hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
 ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
 pid (Passport ID) - a nine-digit number, including leading zeroes.
 cid (Country ID) - ignored, missing or not.
 */
def validHeight = {
	def len = it.length()
	def last2 = it.substring(len - 2)
	def numPart = it.substring(0, len - 2) as Integer
	last2 in ['cm', 'in'] &&
			(last2 == 'cm' && numPart >= 150 && numPart <= 193 || last2 == 'in' && numPart >= 59 && numPart <= 76)
}
println credentials.findAll {
	def byr = it.byr as Integer
	def eyr = it.eyr as Integer
	def iyr = it.iyr as Integer
	def hgt = it.hgt as String
	def hcl = it.hcl as String
	def ecl = it.ecl as String
	def pid = it.pid as String
	(byr && byr >= 1920 && byr <= 2002) &&
			(iyr >= 2010 && iyr <= 2020) &&
			(eyr >= 2020 && eyr <= 2030) &&
			(hgt && validHeight(hgt)) &&
			(hcl ==~ /^#[0-9a-f]+$/) &&
			(ecl in ['amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth']) &&
			(pid && pid.length() == 9 && pid ==~ /[0-9]+/)
}.size()