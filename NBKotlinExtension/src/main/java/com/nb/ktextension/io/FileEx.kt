package com.busd.lightwallet.extension

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Created by NieBin on 18-9-28
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */
/**读取指定文件下的内容，并转换为byte array格式*/
val File.content2ByteArray: ByteArray
    get() {
        var input = FileInputStream(this)
        return input.content2ByteArray
    }

/**读取指定文件下的内容，并转换为byte array格式*/
val String.file2ByteArray: ByteArray
    get() = FileInputStream(this).content2ByteArray

/**读取file中的内容，并转换为String*/
val File.content2String: String
    get() = content2ByteArray.toString(Charsets.UTF_8)

/**获取路径下的文件内容，并就转换为String
 * */
val String.file2String: String
    get() = file2ByteArray.toString(Charsets.UTF_8)

/**根据路径获取文件夹，不存在就创建*/
fun String.getFolder(): Pair<Boolean, File> {
    val file = File(this)
    val isC = file.createFolder()
    return Pair(isC, file)
}

/**传见文件夹*/
fun File.createFolder(): Boolean {
    return try {
        if (!this.exists()) {
            this.mkdirs()
        }
        true
    } catch (e: Exception) {
        false
    }
}

/**
 * 传入文件名获取file, 如果不存在就创建，但是不会创建文件夹
 * @param folder 文件夹的路径,末尾不用带文件分割符
 * */
fun String.path2File(folder: String = ""): Pair<Boolean, File> {
    var sp = if (folder.isNotEmpty()) File.separator else ""
    var file = File("$folder$sp$this")
    var isC = try {
        if (!file.exists()) file.createNewFile()
        true
    } catch (e: Exception) {
        false
    }
    return Pair(isC, file)
}

/**
 * 将byte array 写入文件，并关闭留
 * @param content 内容*/
fun File.write2String(content: String): Boolean {
    return write2ByteArray(content.toByteArray())
}

/**将byte array 写入文件，并关闭留
 * @param bytes 内容*/
fun File.write2ByteArray(bytes: ByteArray): Boolean {
    var out: FileOutputStream? = null
    return try {
        out = FileOutputStream(this)
        out.write2Bytes(bytes)
        true
    } catch (e: Exception) {
        false
    } finally {
        out?.close()
    }

}

/**
 * 将byte array 写入文件，并关闭留
 * @param bytes 内容
 **/
fun String.write2ByteArray(bytes: ByteArray): Boolean {
    var (isC, file) = path2File()
    if (isC) {
        isC = file.write2ByteArray(bytes)
    }
    return isC
}

/**
 * 指定文件路径将byte array 写入文件，并关闭留
 * @param content 内容*/
fun String.write2String(content: String): Boolean {
    return write2ByteArray(content.toByteArray())
}

