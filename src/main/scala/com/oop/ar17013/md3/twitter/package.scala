package com.oop.ar17013.md3

import java.io.FileNotFoundException
import java.util.Properties

import scala.io.Source

package object twitter {

    private val url = getClass.getResource("/credentials.properties")

    val properties: Properties = new Properties()

    if (url != null) {
        val source = Source.fromURL(url)
        properties.load(source.bufferedReader())
    }
    else {
        throw new FileNotFoundException("Properties file cannot be loaded. Please create resource credentials.properties file.")
    }


}
