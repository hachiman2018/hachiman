package ioc

import cn.hutool.core.io.IoUtil
import org.hachiman.core.io.DefaultResourceLoader
import org.junit.jupiter.api.Test

class ResourceTest {


    @Test
    fun testClassPathResource() {
        // 类路径下
        val resourceLoader = DefaultResourceLoader()
        var resource = resourceLoader.getResource("classpath:hello.txt")
        var inputStream = resource.getInputStream();
        var fileContent = IoUtil.readUtf8(inputStream)
        println(fileContent)

        // 读取文件
        resource = resourceLoader.getResource("/src/test/resources/hello.txt")
        inputStream = resource.getInputStream();
        fileContent = IoUtil.readUtf8(inputStream)
        println(fileContent)

        //加载url资源
        resource = resourceLoader.getResource("https://github.com/hachiman2018/hachiman/blob/develop/README.md")
        inputStream = resource.getInputStream()
        fileContent = IoUtil.readUtf8(inputStream)
        println(fileContent)
    }
}