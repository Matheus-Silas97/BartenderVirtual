package com.example.letsdrink

interface Router {
    val id: String
    val popBackStack: Boolean
    val arguments: List<String>
    val optionalArguments: List<String>?
    val router: String
    fun address(args: List<String> = emptyList()): String
}

class RouterImpl(
    override val id: String,
    override val popBackStack: Boolean = false,
    override val arguments: List<String> = emptyList(),
    override val optionalArguments: List<String>? = null,
) : Router {
    override val router: String by lazy {
        StringBuilder(id).apply {
            arguments.let { arguments ->
                arguments.forEach { argument ->
                    append("/{$argument}")
                }
            }
            optionalArguments?.let { optionalArguments ->
                optionalArguments.forEach { optionalArgument ->
                    append("?$optionalArgument={$optionalArgument}")
                }
            }
        }.toString()
    }

    override fun address(args: List<String>): String {
        var address = router
        args.mapIndexed { index, arg ->
            address = address.replace(oldValue = "{${arguments[index]}}", newValue = arg)
        }
        return address
    }
}

fun <T> List<T>.second(): T {
    if (isEmpty())
        throw NoSuchElementException("List is empty.")
    return this[1]
}