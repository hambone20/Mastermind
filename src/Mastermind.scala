/** Nick Horner - Mastermind game */

object Mastermind extends App {
  val field = new Field

  while (!field.checkWin && field.currentGuess < field.maxGuesses) {
    println(field)
    print(s"Input your ${field.numSpots} letter sequence guess ('show' to cheat): ")

    var guess: String = scala.io.StdIn.readLine()
    if (guess.length == field.numSpots) {
      if (guess == "show") {
        println(s"(${field.pattern.mkString})")
      } else {
        field.testGuess(guess)
      }
    }
  }
  if (field.checkWin) {
    println("You win!")
  } else {
    println("You lose...")
  }
}