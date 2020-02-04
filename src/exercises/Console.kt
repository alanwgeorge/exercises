package exercises

import java.io.InputStream
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.nio.charset.CharsetDecoder

private const val BUFFER_SIZE: Int = 32
private const val LINE_SEPARATOR_MAX_LENGTH: Int = 2

private val decoder: CharsetDecoder by lazy { Charset.defaultCharset().newDecoder() }

var readLineInput: InputStream = System.`in`

fun readLine(): String? = readLine(readLineInput, decoder)

private fun readLine(inputStream: InputStream, decoder: CharsetDecoder): String? {
    require(decoder.maxCharsPerByte() <= 1) { "Encodings with multiple chars per byte are not supported" }

    val byteBuffer = ByteBuffer.allocate(BUFFER_SIZE)
    val charBuffer = CharBuffer.allocate(LINE_SEPARATOR_MAX_LENGTH * 2) // twice for surrogate pairs
    val stringBuilder = StringBuilder()

    var read = inputStream.read()
    if (read == -1) return null
    do {
        byteBuffer.put(read.toByte())
        if (decoder.tryDecode(byteBuffer, charBuffer, false)) {
            if (charBuffer.endsWithLineSeparator()) {
                break
            }
            if (charBuffer.remaining() < 2) {
                charBuffer.offloadPrefixTo(stringBuilder)
            }
        }
        read = inputStream.read()
    } while (read != -1)

    with(decoder) {
        tryDecode(byteBuffer, charBuffer, true) // throws exception if undecoded bytes are left
        reset()
    }

    with(charBuffer) {
        var length = position()
        if (length > 0 && get(length - 1) == '\n') {
            length--
            if (length > 0 && get(length - 1) == '\r') {
                length--
            }
        }
        flip()
        repeat(length) {
            stringBuilder.append(get())
        }
    }

    return stringBuilder.toString()
}

private fun CharsetDecoder.tryDecode(byteBuffer: ByteBuffer, charBuffer: CharBuffer, isEndOfStream: Boolean): Boolean {
    val positionBefore = charBuffer.position()
    byteBuffer.flip()
    with(decode(byteBuffer, charBuffer, isEndOfStream)) {
        if (isError) throwException()
    }
    return (charBuffer.position() > positionBefore).also { isDecoded ->
        if (isDecoded) byteBuffer.clear() else byteBuffer.flipBack()
    }
}
private fun CharBuffer.endsWithLineSeparator(): Boolean {
    val p = position()
    return p > 0 && get(p - 1) == '\n'
}

private fun Buffer.flipBack() {
    position(limit())
    limit(capacity())
}

/** Extracts everything except the last char into [builder]. */
private fun CharBuffer.offloadPrefixTo(builder: StringBuilder) {
    flip()
    repeat(limit() - 1) {
        builder.append(get())
    }
    compact()
}