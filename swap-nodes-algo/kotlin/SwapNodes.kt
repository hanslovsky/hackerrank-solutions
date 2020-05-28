import java.io.*
import java.math.*
import java.text.*
import java.util.*
import java.util.regex.*

fun calcDepth(
    index: Int,
    left: IntArray, 
    right: IntArray, 
    depthAt: IntArray,
    depth: Int) {
    depthAt[index] = depth
    if (left[index] >= 0) calcDepth(left[index], left, right, depthAt, depth + 1)
    if (right[index] >= 0) calcDepth(right[index], left, right, depthAt, depth + 1)
}

fun traverseInOrder(
    index: Int,
    left: IntArray,
    right: IntArray,
    callback: (Int) -> Unit) {
        if (left[index] >= 0) traverseInOrder(left[index], left, right, callback)
        callback(index)
        if (right[index] >= 0) traverseInOrder(right[index], left, right, callback)
    }

/*
 * Complete the swapNodes function below.
 */
fun swapNodes(indexes: Array<Array<Int>>, queries: Array<Int>): Array<Array<Int>> {
    if (indexes.isEmpty())
        return emptyArray()

    val l = IntArray(indexes.size) { indexes[it][0] - 1 }
    val r = IntArray(indexes.size) { indexes[it][1] - 1 }
    val d = IntArray(indexes.size)
    calcDepth(0, l, r, d, 1)
    val output = mutableListOf<Array<Int>>()
    for (query in queries) {
        // val l = left.clone()
        // val r = right.clone()
        // val d = depth.clone()
        for (index in d.indices) {
            if (d[index] % query == 0) {
                val tmp  = r[index]
                r[index] = l[index]
                l[index] = tmp
            }
        }
        val inOrder = mutableListOf<Int>()
        traverseInOrder(0, l, r) { inOrder += it + 1 }
        output += inOrder.toTypedArray()
    }

    return output.toTypedArray()

    
    /*
     * Write your code here.
     */

}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val indexes = Array<Array<Int>>(n, { Array<Int>(2, { 0 }) })

    for (indexesRowItr in 0 until n) {
        indexes[indexesRowItr] = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
    }

    val queriesCount = scan.nextLine().trim().toInt()

    val queries = Array<Int>(queriesCount, { 0 })
    for (queriesItr in 0 until queriesCount) {
        val queriesItem = scan.nextLine().trim().toInt()
        queries[queriesItr] = queriesItem
    }

    val result = swapNodes(indexes, queries)

    println(result.map{ it.joinToString(" ") }.joinToString("\n"))
}
