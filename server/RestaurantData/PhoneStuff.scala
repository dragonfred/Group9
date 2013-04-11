import scala.io.Source


// This generates a SQL file to add phone numbers missing in the 
// previous import. 

object PhoneStuff {

  val csvFile = Source.fromFile("/Users/phil/ucf/uml-cop4331/Group9/server/RestaurantData/Orlando_Florida.csv")
  val csvList = csvFile.getLines.toList
  val csvVals = csvList.map (_.split(",")) 
  val namePhoneAdd = (for (i <- csvVals) yield {
	  	val phone = i(5).toList.filterNot(_ == ' ')
	  	val area = phone.take(3).mkString
	  	val prefix = phone.drop(3).take(3).mkString
	  	val suffix = phone.drop(6).take(4).mkString
    	(i(0).replace("'","\\\'"), 
    	area+"-"+prefix+"-"+suffix,
    	i(1).split(" ").toList.head.toList.filter(_.isDigit) mkString)
  }).tail
  
  val sqls = for (i <- namePhoneAdd) yield "UPDATE Restaurants SET Phone='"+i._2+"' WHERE Name = '"+i._1+"' AND Address LIKE '%"+i._3+"%';"
  def main(args: Array[String]): Unit = {
    println("USE Restaurant");
	  println(sqls.mkString("\n"))
  }

}
