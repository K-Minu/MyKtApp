package com.example.myktapp

import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase

val data1: Int by lazy {
    println("asdasd")
    20
}
var dattt: Any = 1
class User
var data3: Any = User()
var data2 : Int? = null

open class Super(){
    open var data = 10
    open fun some(){
        println("super data : $data")
    }
}

val obj = object: Super() {
    override var data =11
    override fun some(){
        println("obj data : $data")
    }
}

val obb = object : Super() {
     override var data =11
     override fun some(){
        println("obj data : $data")
    }
}

class MyClass {
    companion object{
        var data = 11
        fun some(){
            println(data)
        }
    }
}
fun hoFun(arg : (Int) -> Boolean) : () -> String{
    val result = if(arg(10)){
        "vaild"
    } else{
        "invaild"
    }
    return {"hoFun result $result"}
}

fun cal(a : Int, b : Int, p: (Int, Int) -> Int) {
    println("$a, $b, ${p(a, b)}")
}


fun main(){

    var data : String? = null


    println("data length : ${data?.length ?:3}")


    /*

val result = hoFun({ no -> no > 0 })

println(result())

val test = cal(10,4, {a,b -> a/b})


val some : (Int, Int) -> Int = {no1 , no2 -> no1 + no2}

println("result : ${some(1,2)}")

MyClass.some()

obb.data = 123
obb.some()
println("main..")
data2 = 15
dattt = 3
println(data1 + 5)
println(data2!!.plus(2))

val str1 = """
hi
        hello
""".trimIndent()
println("str1 : \n $str1")

fun sum(no : Int): Int {
var sum = 0
for (i in 1..no){
    sum += i
}
return sum
}

val name : String = "kang"
println("name : $name, sum : ${sum(5)}, plus : ${dattt}+3 ")

fun some(data1: Int = 3, data2: Int = 10): Int{
return data1 * data2
}

print(some(data2 = 1))

val data11: Array<Int> = Array(3, {0})
data11[0] = 10
data11[1] = 20
data11.set(2,22)

println(
"""
    array size : ${data11.size}
    array data : ${data11[0]}, ${data11[1]}, ${data11.get(2)}
""".trimIndent()
)

val data12: IntArray = IntArray(3, {0})
val data13: BooleanArray = BooleanArray(3, {false})

val data14 = arrayOf<Int>(10, 20, 30)
println(
"""
    array size : ${data14.size}
    array data : ${data14[0]}, ${data14[1]}, ${data14.get(2)}
""".trimIndent()
)

var list = listOf<Int>(11,22,33)
var mutableList = mutableListOf<Int>(12,23,34)
mutableList.add(3, 44)
mutableList.set(0, 9)
println(
"""
    List array size : ${mutableList.size}
    List array data : ${mutableList[0]}, ${mutableList[1]}, ${mutableList.get(2)}, ${mutableList.get(3)}
""".trimIndent()
)

var map = mapOf<String, Int>(Pair("one", 1), "two" to 2)
println(
"""
    map size : ${map.size}
    map data : ${map.get("one")}, ${map.get("two")}
""".trimIndent()
)

var data = 2
val result = if (data > 0){
println("data > 0")
true
} else{
println("data <= 0 ")
false
}
println(result)

var datt: Any = 10

when (datt){
is String -> println("data is String")
4, 0 -> print("qwe")
in 1..10 -> println("data is 1..10")
else -> print("qqqq")
}

val resultt = when {
data <= 0 -> "data is $data"
data >= 0 -> "data is $data"
else -> "woowoo"
}

println("resultt is $resultt")


fun asd(){
var sum: Int = 0
for (i in 10 downTo 1){
    sum += i
}
println(sum)
}
asd()

fun qwe(){
var data = arrayOf<Int>(10,20,30)
//        for (i in data.indices) {
for ((index, value) in data.withIndex()) {
    print(value)
    if (index !== data.size -1) print(",")
}
}
qwe()


class User(var name : String, var cnt : Int) {
init {
    println("init..")
}
fun someFun(){
    println("name : $name \ncount : $cnt")
}

inner class SomeClass { }
}

val user = User("kang", 19)
user.someFun()

class Deploy(var name : String){
var cnt: Any? = "qwe"
var email: String? = "asd"
constructor(name : String, cnt : Int) : this(name){
    this.cnt = cnt
}
constructor(name : String, cnt : Int, email : String) : this(name, cnt){
    this.cnt = cnt
    this.email = email
}
fun get(){
    println("name: $name\ncnt: $cnt\nemail: $email")

}
}

val deploy = Deploy("ohhh",1)

deploy.get()


open class Super(name : String){
open var cnt : Int = 20
open fun some(){
    println("super, cnt : $cnt")
}
}
class Sub: Super {
override var cnt: Int = 0
constructor(name : String, cnt : Int) : super(name){
    this.cnt = cnt
}
override fun some(){
    println("sub class, cnt : $cnt")
}
}

val obj = Super("akgn")
obj.cnt =30
obj.some()

data class Asd(val name: String, val age : Int){
lateinit var area: String
constructor(name: String, age : Int, area : String) : this(name, age){
    this.area = area
}
}

val opt = Asd("akng", 31, "seed")
val opt2 = Asd("akng", 31, "asd")

println("opt, opt2 is equals? ${opt.equals(opt2)}")
println("opt, opt2 is equals? ${opt.toString()}")
*/


}



