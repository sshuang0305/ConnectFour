package nl.sogyo.connectfour

class GridCell() {
    var disc: Disc? = null
        private set
    fun isEmpty() = disc == null
    fun placeDisc(discToPlace: Disc) {disc = discToPlace}
}