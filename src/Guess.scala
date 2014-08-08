/*
 * Embodies all guesses
 */
class Guess(val numSpots: Int) {
  var  pattern = "-" * numSpots
  var feedback = " " * numSpots

  override def toString = s"$pattern $feedback"
}