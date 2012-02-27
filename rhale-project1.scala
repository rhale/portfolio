/*
Project 1 
Richard Hale
CS3693
 */
import java.io._
  class aFile(var content:Int, var name:String){}
  def compare(a: Array[aFile]): Int= {
    var foo = List[Int]()
    var n = 0; var n2=0; var i = 0;
    var l = new Array[aFile](a.length); var l2 =new Array[aFile](a.length)
    for(i <- 0 until a.length){
     if(a(i) != null){if(a(i).content!=a(0).content) {l(n) = a(i); n=n+1} else{ l2(n2)=a(i); n2=n2+1}
    }}
    for(i <- 0 until l2.length)
     if(l2(i)!=null)
      println(l2(i).name)
    println("are duplicates\n\n")
    if(a(1)!=null) compare(l) else 0
    0
  }
  def main(args: Array[String]): Unit = {
    println("Enter a directory")
    var dir = new File(if(readLine()!=null) readLine() else "/Users/Example")
    println(dir)
    var children = (dir.list())
    var blah=new Array[aFile](children.length)
    println("Files found:")
    if(children != null)
      for(i <- 0 until children.length){
        var filename = children(i)
        var fis = new FileInputStream(children(i))
        blah(i)=new aFile(fis.read(),children(i))
      }
    compare(blah)
  }
