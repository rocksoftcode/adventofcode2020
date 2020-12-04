def credentials = new File('credentials.txt').text.split('\n\n')
		.collect {
			def datum = it.split('\\s')
			datum.collectEntries {
				def item = it.split(':')
				[item[0], item[1]]
			}
		}

/**
 * byr (Birth Year)
 * iyr (Issue Year)
 * eyr (Expiration Year)
 * hgt (Height)
 * hcl (Hair Color)
 * ecl (Eye Color)
 * pid (Passport ID)
 * cid (Country ID) - optional
 */
println credentials.findAll { it.byr && it.iyr && it.eyr && it.hgt && it.hcl && it.ecl && it.pid }.size()