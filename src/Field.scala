import collection.mutable.ArrayBuffer
import util.Random

class Field {
	//Mastermind.maxGuesses
	//Mastermind.numLetters
	//Mastermind.numSpots
	
	var currentGuess = 0
	val guesses = ArrayBuffer[Guess]()
	var win = false
	
	val pattern = makePattern()
	
	for(x <- 0 until Mastermind.maxGuesses){guesses += new Guess(Mastermind.numSpots)}
	
	clearField()
	
	def clearField() = Unit
	
	def makePattern() = {
	  val a = 97
	  var arr = ArrayBuffer[String]()
	  for(x <- 0 until Mastermind.numSpots){
	    arr += ((a + Random.nextInt(Mastermind.numLetters)).toChar).toString
	  }
	  arr.toArray
	}
	
	def testGuess(guess: String) = {
	  var result = ArrayBuffer[String]()
	  var testPattern = pattern
	  var testGuess = guess.toArray
	  guesses(currentGuess).setPattern(guess)
	  //var used = ArrayBuffer[Int]()
	  assert(guess.length == pattern.length, {println("oops guess not long enough")})
	  // have to make two passes

	  for(i <- 0 until Mastermind.numLetters){
		  if(testGuess(i).toString == testPattern(i)){
		    result += "R" 
		    testPattern(i) = "Z"
		    testGuess(i) = 'Z'
		  }
		  if(Mastermind.debug)println(s"pattern: ${testPattern.mkString} for R")
	  }
	  for(j <- 0 until Mastermind.numLetters){
	    if (testGuess(j) != 'Z' && testPattern.contains(testGuess(j).toString)){
		    result += "W"
		    testPattern(testPattern.indexOf(guess(j).toString)) = "Z"
		    if(Mastermind.debug)println(s"${testPattern.mkString} for W")
		  }

	  }
	  guesses(currentGuess).setFeedback(Random.shuffle(result))
	  
	  if(result.mkString == "R" * Mastermind.numLetters) win = true
	  currentGuess += 1
	  result
	}
	
	def checkWin() = win
	
	def printGuess = guesses.mkString
	
	override def toString = guesses.foldLeft("")(_ + "\n" + _)
}