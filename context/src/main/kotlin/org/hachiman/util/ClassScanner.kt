package org.hachiman.util

import java.io.File
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes
import kotlin.io.path.absolutePathString
import kotlin.io.path.name


const val classSuffix = ".class"

class ClassScanner(private val packageName: String, private val filter: (Class<*>) -> Boolean) {

    private val scannedClass = mutableListOf<Class<*>>()

    private val classFilter: (String) -> Boolean = { it.endsWith(classSuffix) }

    private var cl: ClassLoader? = null

    fun scanPackage(): List<Class<*>> {
        // load path resources
        val iterator = ClassLoaderUtil.getClassLoader().getResources(packageName).iterator()

        // scan package
        while (iterator.hasNext()) {
            val next = iterator.next()
            when (next.protocol) {
                // 类型为文件夹时 扫描文件夹
                "file" -> scanFile(next.path)
            }
        }
        return scannedClass
    }

    private fun scanFile(filePath: String) {
        // 处理读取出来的路径
        val targetFilePath = normalizeFilePath(filePath)

        // 遍历文件
        val path = Paths.get(targetFilePath)
        Files.walkFileTree(path, object : SimpleFileVisitor<Path>() {
            override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
                val className = file.fileName.name
                if (classFilter(className)) {
                    initClassLoader()
                    val clazz = Class.forName(concatClassFullPath(targetFilePath, file.absolutePathString()), false, cl)
                    if (filter(clazz)) {
                        scannedClass.add(clazz)
                    }
                }
                return FileVisitResult.CONTINUE
            }
        })
    }

    private fun normalizeFilePath(filePath: String) = if (filePath.startsWith("/")) {
        filePath.substring(1).replace("/", File.separator)
    } else {
        filePath
    }

    private fun initClassLoader() {
        if (cl == null) {
            cl = ClassScanner::class.java.classLoader
        }
    }


    /**
     * 获取类的包路径名
     */
    fun concatClassFullPath(packageDirPath: String, classParentDirPath: String): String {
        val classPackageDirPath = classParentDirPath.substringAfterLast(packageDirPath).substringBeforeLast(classSuffix)
        return "$packageName${classPackageDirPath.replace(File.separator, ".")}"
    }
}