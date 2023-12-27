package resource

import org.junit.jupiter.api.Test
import java.io.File
import java.net.URL

class InputStreamResourceTest {

    @Test
    fun test() {
        val url = URL("file:///C:\\workspace\\hachiman\\context\\src\\test\\kotlin\\resource\\application.yml");

        println(url.protocol)
        // 将URL转换为URI
        val uri = url.toURI()

        // 获取URI的路径部分
        val path = uri.path

        // 创建File对象
        val file = File(path);

        // 现在可以使用File对象来操作本地文件
        println("File Path: " + file.absolutePath)
    }

}