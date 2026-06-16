package ch14

import java.lang.NumberFormatException
import java.text.ParseException

class Args {
    private var schema: String? = null
    private var args: Array<String> = arrayOf()
    private var valid: Boolean = true
    private val unexpectedArguments: MutableSet<Char> = mutableSetOf()
    private val marshalers: MutableMap<Char, ArgumentMarshaler> = mutableMapOf()
    private val argsFound: MutableSet<Char> = mutableSetOf()
    private var currentArgument: Int = 0
    private var errorArgumentId: Char = '0'
    private var errorParameter: String = "TILT"
    private var errorCode: ErrorCode = ErrorCode.OK

    private enum class ErrorCode {
        OK, MISSING_STRING, MISSING_INTEGER, INVALID_INTEGER, UNEXPECTED_ARGUMENT
    }

    constructor(schema: String, args: Array<String>) {
        this.schema = schema
        this.args = args
        valid = parse()
    }

    private fun parse(): Boolean {
        if (schema?.length == 0 && args.isEmpty())
            return true
        parseSchema()
        try {
            parseArguments()
        } catch (e: ArgsException) {

        }
        return valid
    }

    private fun parseSchema(): Boolean {
        schema?.split(",")
            ?.forEach { if (it.isNotEmpty()) parseSchemaElement(it.trim()) }
        return true
    }

    private fun parseSchemaElement(element: String) {
        val elementId = element[0]
        val elementTail = element.substring(1)
        validateSchemaElementId(elementId)
        if (isBooleanSchemaElement(elementTail))
            marshalers[elementId] = BooleanArgumentMarshaler()
        else if (isStringSchemaElement(elementTail))
            marshalers[elementId] = StringArgumentMarshaler()
        else if (isIntegerSchemaElement(elementTail))
            marshalers[elementId] = IntegerArgumentMarshaler()
        else throw ParseException(String.format("Argument: %c has invalid format: %s.", elementId, elementTail), 0)

    }

    private fun validateSchemaElementId(elementId: Char) {
        if (Character.isLetter(elementId))
            throw ParseException("Bad character: $elementId in Args format: $schema", 0)
    }

    private fun isIntegerSchemaElement(elementTail: String): Boolean = elementTail == "#"

    private fun isStringSchemaElement(elementTail: String): Boolean = elementTail == "*"

    private fun isBooleanSchemaElement(elementTail: String): Boolean = elementTail.isEmpty()


    private fun parseArguments(): Boolean {
        args.forEach { parseArgument(it) }
        return true
    }

    private fun parseArgument(arg: String) {
        if (arg.startsWith("-"))
            parseElements(arg)
    }

    private fun parseElements(arg: String) {
        arg.forEach { parseElement(it) }
    }

    private fun parseElement(argChar: Char) {
        if (setArgument(argChar))
            argsFound.add(argChar)
        else {
            unexpectedArguments.add(argChar)
            errorCode = ErrorCode.UNEXPECTED_ARGUMENT
            valid = false
        }
    }

    private fun setArgument(argChar: Char): Boolean {
        val m = marshalers[argChar]
        try {
            if (m is BooleanArgumentMarshaler) {
                setBooleanArg(m)
            } else if (m is StringArgumentMarshaler) {
                setStringArg(m)
            } else if (m is IntegerArgumentMarshaler) {
                setIntArg(m)
            } else return false
        } catch (e: ArgsException) {
            valid = false
            errorArgumentId = argChar
            throw e
        }

        return true
    }

    private fun setBooleanArg(m: ArgumentMarshaler?) {
        m?.set("true")
    }

    private fun setStringArg(m: ArgumentMarshaler?) {
        currentArgument++
        try {
            m?.set(args[currentArgument])
        } catch (e: ArrayIndexOutOfBoundsException) {
            errorCode = ErrorCode.MISSING_STRING
            throw ArgsException()
        }
    }

    private fun setIntArg(m: ArgumentMarshaler?) {
        currentArgument++
        try {
            m?.set(args[currentArgument])
        } catch (e: ArrayIndexOutOfBoundsException) {
            errorCode = ErrorCode.MISSING_INTEGER
            throw ArgsException()
        } catch (e: ArgsException) {
            errorParameter = args[currentArgument]
            errorCode = ErrorCode.INVALID_INTEGER
            throw e
        }
    }

    fun cardinality(): Int = argsFound.size

    fun usage(): String =
        if (schema?.isNotEmpty() == true)
            "-[$schema]"
        else ""

    fun errorMessage(): String =
        when (errorCode) {
            ErrorCode.OK -> throw Exception("TILT: Should not get here.")
            ErrorCode.UNEXPECTED_ARGUMENT -> unexpectedArgumentMessage()
            ErrorCode.MISSING_STRING -> "Could not find string parameter for $errorArgumentId"
            ErrorCode.INVALID_INTEGER -> "Argument $errorArgumentId expects an integer but was $errorParameter"
            ErrorCode.MISSING_INTEGER -> "Could not find integer parameter for $errorArgumentId"
        }

    private fun unexpectedArgumentMessage(): String {
        val message = StringBuffer("argument(s) -")
        unexpectedArguments.forEach { message.append(it) }
        message.append(" unexpected.")
        return message.toString()
    }

    private fun falseIfNull(b: Boolean?) = b != null && b

    private fun zeroIfNull(i: Int?) = i ?: 0

    private fun blankIfNull(s: String?) = s ?: ""

    fun getString(arg: Char): String {
        try {
            return marshalers[arg]?.get() as String
        } catch (e: ClassCastException) {
            return ""
        }
    }

    fun getInt(arg: Char): Int {
        try {
            return marshalers[arg]?.get() as Int
        } catch (e: ClassCastException) {
            return 0
        }
    }

    fun getBoolean(arg: Char): Boolean {
        val am = marshalers[arg]
        var b: Boolean
        try {
            b = am != null && am.get() as Boolean
        } catch (e: ClassCastException) {
            b = false
        }
        return b
    }

    fun has(arg: Char): Boolean = argsFound.contains(arg)

    fun isValid(): Boolean = valid
}

class ArgsException : Exception()

private abstract class ArgumentMarshaler(
    open var booleanValue: Boolean? = null,
    open var stringValue: String? = null,
    open var integerValue: Int? = null,
) {
    abstract fun set(s: String)
    abstract fun get(): Any?
}

private class BooleanArgumentMarshaler(
    override var booleanValue: Boolean? = false
) : ArgumentMarshaler() {
    override fun set(s: String) {
        booleanValue = true
    }

    override fun get(): Any? = booleanValue
}

private class StringArgumentMarshaler(
    override var stringValue: String? = ""
) : ArgumentMarshaler() {
    override fun set(s: String) {
        stringValue = s
    }

    override fun get(): Any? = stringValue
}

private class IntegerArgumentMarshaler(
    override var integerValue: Int? = 0
) : ArgumentMarshaler() {
    override fun set(s: String) {
        try {
            integerValue = s.toInt()
        } catch (e: NumberFormatException) {
            throw ArgsException()
        }
    }

    override fun get(): Any? = integerValue
}
