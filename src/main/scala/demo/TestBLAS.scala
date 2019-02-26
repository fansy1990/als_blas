package demo

import breeze.linalg.DenseMatrix


import com.github.fommil.netlib.BLAS.{getInstance => blas}
/**
  * //@Author: fansy 
  * //@Time: 2019/2/25 13:42
  * //@Email: fansy1990@foxmail.com
  */
object TestBLAS {
  def test(n:Int): Unit ={
    val vl = java.text.NumberFormat.getIntegerInstance.format(n)
    println(f"Naive Multipication with vector length " + vl)

    println(blas.getClass().getName())
    val t1 = System.currentTimeMillis()
    val sm: DenseMatrix[Double] = DenseMatrix.rand(n, n)
    val a: DenseMatrix[Double] = DenseMatrix.rand(2,n)
    val b: DenseMatrix[Double] = DenseMatrix.rand(n,3)

    val c: DenseMatrix[Double] = sm * sm
    val cNormal: DenseMatrix[Double] = (a *  c)  * b

    println(s"Dot product of a and b is \n$cNormal")
    val runtime = (System.currentTimeMillis() - t1)/1000.0
    println(s"Elapsed run time  is \n$runtime s")
  }
  def main(args: Array[String]): Unit = {
    val n = if(args.length >0 ) args(0).toInt else 3000
    test1()
    test(n)
  }


  def test1(): Unit ={
    val  classLoader= TestBLAS.getClass.getClassLoader();
    //只能搜索jar包内的字符，比如文件夹org,com，文件名a.xml
    val paths  = classLoader.getResources("META-INF");
    val count = 0;
    while (paths.hasMoreElements()){
      val path = paths.nextElement().toString();
      System.out.println(path);

    }


  }
}
