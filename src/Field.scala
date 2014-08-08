import collection.mutable.ArrayBuffer
import util.Random

class Field(val maxGuesses: Int = 8, val numLetters: Int = 4, val numSpots: Int = 4) {
  var currentGuess = 0
  var win = false
  var pattern = makePattern()
  val guesses = (for (_ <- 0 until maxGuesses)
    yield new Guess(numSpots))(scala.collection.breakOut)

  def makePattern(): String = {
    def randLetter(): Char = {
      (97 + Random.nextInt(numLetters)).toChar
    }
    (for (_ <- 0 until numSpots) yield randLetter())(scala.collection.breakOut)
  }

  def testGuess(guess: String) = {
    var result = ArrayBuffer[Char]()
    guesses(currentGuess).pattern = guess
    var testPattern = pattern toBuffer
    var testGuess = guess toBuffer
    var numHits = 0

    assert(guess.length == pattern.length, { println("Guess length not the correct size") })
    // have to make two passes

    for (i <- 0 until numLetters) {
      if (testGuess(i) == testPattern(i)) {
        result += 'R'
        numHits += 1
        testPattern(i) = 'Z' // marking this as used
        testGuess(i) = 'Z' // and this
      }
    }
    for (j <- 0 until numLetters) {
      val idx = testPattern.indexOf(guess(j))
      if (testGuess(j) != 'Z' && idx > -1) {
        result += 'W'
        testPattern(idx) = 'Z'
      }

    }
    assert(result.length <= numLetters, { println("Returned result is too long") })
    guesses(currentGuess).feedback = Random.shuffle(result).mkString

    win = numHits == numLetters

    currentGuess += 1
    result
  }

  def checkWin() = win

  def printGuess = guesses.mkString

  override def toString = guesses.foldLeft("")(_ + "\n" + _)
}