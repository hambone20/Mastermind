import collection.mutable.ArrayBuffer

class Guess(val numSpots: Int) {
	var pattern = ArrayBuffer[String]()
	var feedback = ArrayBuffer[String]()
	
	for(x <- 0 until numSpots){
		pattern += "-"
		feedback += " "
	}
	
	def setFeedback(arr: ArrayBuffer[String]){
	  feedback = arr
	}
	
	def setPattern(pat: String){
	  val arr = pat.toArray
	  var newPat = ArrayBuffer[String]()
	  for(i <- 0 until arr.size){
	    newPat += arr(i).toString
	  }
	  pattern = newPat
	}
  override def toString = s"${pattern.mkString}   ${feedback.mkString}"
}