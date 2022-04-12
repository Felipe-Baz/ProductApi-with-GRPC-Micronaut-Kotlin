package br.com.baz

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.baz")
		.start()
}

