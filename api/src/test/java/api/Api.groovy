package api

def toInt(def number) {
    try {
        return number.toInteger()
    } catch (Exception e) {
        return 0
    }
}

def id_coords = new XmlSlurper().parse(new File("universe.xml")).planet
        .collect { ['id': it.@player.toInteger(), 'coords': it.@coords] }

def id_score = new XmlSlurper().parse(new File("points.xml")).player
        .collect { ['id': it.@id.toInteger(), 'score': toInt(it.@score)] }

new XmlSlurper().parse(new File("points-military.xml")).player
        .collect {
    [
            'id'            : toInt(it.@id),
            'military-score': toInt(it.@score),
            'ships'         : toInt(it.@ships) ?: 0,
            'score'         : id_score.find { map -> map['id'] == toInt(it.@id) }?.get('score') ?: 0,
            'coords'        : id_coords.findAll { map -> map['id'] == toInt(it.@id) }.collect {it['coords']}
    ]
}
        .findAll { it['coords'].any { it.toString().split(":")[0].toInteger() == 1 } }
//        .findAll { it['coords'].any { it.toString().split(":")[1].toInteger() < 329 } }// && it.toString().split(":")[1].toInteger() < 429 } }
        .findAll { it['score'] > 2300 }
//        .findAll { it['military-score'] > 0 }
//        .findAll { it['military-score'] == 0 }
//        .findAll { it['ships'] > 30 }
//        .findAll { it['military-score'] < 500 }
//        .findAll { it['score'] > 348 }
//        .sort { it['ships'] / it['military-score'] }
        .sort { -it['military-score'] }
//        .sort { -it['score'] }
        .forEach { println it }
