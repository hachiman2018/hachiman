package org.hachiman.beans.factory.config

import org.yaml.snakeyaml.Yaml
import java.nio.file.Files
import java.nio.file.Paths

abstract class YamlProcessor {


    fun loadYaml(filePath: String): Map<String, Any> {
        val yaml = Yaml()
        val inputStream = Files.newInputStream(Paths.get(filePath))
        val data: Map<String, Any> = yaml.load(inputStream)
        return data
    }


}