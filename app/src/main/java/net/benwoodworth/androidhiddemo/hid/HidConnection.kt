package net.benwoodworth.androidhiddemo.hid

import java.io.Closeable

class HidConnection private constructor(
    private val process: Process
) : Closeable {

    private val out = process.outputStream

    constructor(device: String, root: Boolean = false) : this(getProcess(device, root))

    fun write(vararg bytes: Byte) {
        out.write(bytes)
        out.flush()

        process.checkError()
    }

    override fun close() {
        out.close()
        process.destroy()
    }

    private companion object {
        fun Process.checkError() {
            if (errorStream.available() > 0) {
                val error = errorStream
                    .bufferedReader()
                    .readText()

                throw RuntimeException(error)
            }
        }

        fun getProcess(device: String, root: Boolean): Process {
            val cat = "cat > ${device.toShellString()}"

            val command = if (root) {
                arrayOf("su", "-c", "sh -c ${cat.toShellString()}")
            } else {
                arrayOf("sh", "-c", cat)
            }

            return Runtime.getRuntime().exec(command)
        }

        private fun String.toShellString(): String {
            return this
                .replace("'", "'\\''")
                .let { "'$it'" }
        }
    }
}