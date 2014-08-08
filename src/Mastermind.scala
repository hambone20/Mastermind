/** Nick Horner - Mastermind game */

object Mastermind extends App{
  val maxGuesses = 8
  val numLetters = 4
  val numSpots   = 4
  val debug = true
  
  val field = new Field
  
  while(!field.checkWin){
      println(field)
      val guess = readLine()
      if(debug)println(s"(${field.pattern.mkString})")
      if(guess == "show"){
        println(s"(${field.pattern.mkString})")
      }else{
    	  field.testGuess(guess)
      }
  }
  if(field.checkWin)println("You win!")
  else println("You lose")
}