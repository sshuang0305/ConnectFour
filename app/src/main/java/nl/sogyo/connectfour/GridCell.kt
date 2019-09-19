package nl.sogyo.connectfour

class GridCell(var disc: Disc?) {
    fun isEmpty() = disc == null
    fun placeDisc(discToPlace: Disc) {disc = discToPlace}
}