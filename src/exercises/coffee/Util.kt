package exercises.coffee

val start: Long = System.currentTimeMillis()

fun log(v: Any) = println("${System.currentTimeMillis() - start} [${Thread.currentThread().name}] $v")

fun Float.format(digits: Int): String = java.lang.String.format("%.${digits}f", this)
