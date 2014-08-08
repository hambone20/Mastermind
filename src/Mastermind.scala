/** Nick Horner - Mastermind game */

object Mastermind extends App{
  val debug = false
  
  val field = new Field
  
  while(!field.checkWin && field.currentGuess < field.maxGuesses){
      println(field)
      val guess: String = scala.io.StdIn.readLine()
      
      if(guess == "show"){
        println(s"(${field.pattern.mkString})")
      } else {
    	  field.testGuess(guess)
      }
  }
  if(field.checkWin){
    println("You win!")
  } else {
    println("You lose")
  }
}