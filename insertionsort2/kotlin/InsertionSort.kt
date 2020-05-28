import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

// Complete the insertionSort2 function below.
fun insertionSort2(n: Int, arr: Array<Int>): Unit {
    for (index in 1 until arr.size) {
        val element = arr[index]
        var k = index - 1
        while(k >= 0 && element < arr[k]) {
            arr[k+1] = arr[k]
            --k
        }
        arr[k+1] = element
        println(arr.joinToString(" "))
    }
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val arr = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()

    insertionSort2(n, arr)
}
