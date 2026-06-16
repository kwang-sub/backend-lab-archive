package modernjavainaction.study.ch03

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.StringBuilder


fun processFile(p: BufferedReaderProcessor): String {
    val rootPath = System.getProperty("user.dir")
    val resourcesPath = "$rootPath${File.separator}src${File.separator}main${File.separator}resources"
    println(resourcesPath)
    return BufferedReader(FileReader("$resourcesPath${File.separator}modernjavainaction${File.separator}chap03${File.separator}data.txt"))
        .use { p.process(it) }
}


fun main() {
    val result = processFile {
        val sb = StringBuilder()
        sb.append("${it.readLine()}\n" )
        sb.append("${it.readLine()}\n" )
        sb.append("${it.readLine()}\n" )
        sb.append("${it.readLine()}\n" )
        sb.toString()
    }
    println(result)
}