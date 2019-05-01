package net.benwoodworth.androidhiddemo.hid

@HidDslMarker
class HidKeyboardDsl(
    val keyboard: HidKeyboard,
    val modifiers: Int
) {

    inline fun modifiers(modifiers: Int, dsl: HidKeyboardDsl.() -> Unit) {
        if (modifiers == this.modifiers) {
            dsl(this)
        } else {
            dsl(
                HidKeyboardDsl(
                    keyboard,
                    modifiers or HidKeyboard.MOD_LSHIFT
                )
            )
        }
    }

    inline fun lctrl(dsl: HidKeyboardDsl.() -> Unit) {
        modifiers(modifiers or HidKeyboard.MOD_LCTRL) {
            dsl(this)
        }
    }

    inline fun lshift(dsl: HidKeyboardDsl.() -> Unit) {
        modifiers(modifiers or HidKeyboard.MOD_LSHIFT) {
            dsl(this)
        }
    }

    inline fun lalt(dsl: HidKeyboardDsl.() -> Unit) {
        modifiers(modifiers or HidKeyboard.MOD_LALT) {
            dsl(this)
        }
    }

    inline fun lsuper(dsl: HidKeyboardDsl.() -> Unit) {
        modifiers(modifiers or HidKeyboard.MOD_LSUPER) {
            dsl(this)
        }
    }

    inline fun rctrl(dsl: HidKeyboardDsl.() -> Unit) {
        modifiers(modifiers or HidKeyboard.MOD_RCTRL) {
            dsl(this)
        }
    }

    inline fun rshift(dsl: HidKeyboardDsl.() -> Unit) {
        modifiers(modifiers or HidKeyboard.MOD_RSHIFT) {
            dsl(this)
        }
    }

    inline fun ralt(dsl: HidKeyboardDsl.() -> Unit) {
        modifiers(modifiers or HidKeyboard.MOD_RALT) {
            dsl(this)
        }
    }

    inline fun rsuper(dsl: HidKeyboardDsl.() -> Unit) {
        modifiers(modifiers or HidKeyboard.MOD_RSUPER) {
            dsl(this)
        }
    }

    fun code(code: Int) {
        keyboard.setState(modifiers, code)
        keyboard.setState()
    }

    fun tab() = code(0x2b)
    fun enter() = code(0x28)
    fun escape() = code(0x29)
    fun space() = code(0x2c)
    fun minus() = code(0x2d)
    fun backspace() = code(0x2a)
    fun equalSign() = code(0x2e)
    fun openBracket() = code(0x2f)
    fun semicolon() = code(0x33)
    fun tilde() = code(0x35)
    fun comma() = code(0x36)
    fun period() = code(0x37)
    fun backSlash() = code(0x31)
    fun closeBracket() = code(0x30)
    fun singleQuote() = code(0x34)
    fun forwardSlash() = code(0x38)
    fun capsLock() = code(0x39)
    fun scrollLock() = code(0x47)
    fun insert() = code(0x49)
    fun pauseBreak() = code(0x48)
    fun home() = code(0x4a)
    fun pageUp() = code(0x4b)
    fun delete() = code(0x4c)
    fun end() = code(0x4d)
    fun pageDown() = code(0x4e)
    fun right() = code(0x4f)
    fun left() = code(0x50)
    fun down() = code(0x51)
    fun up() = code(0x52)
    fun numLock() = code(0x53)

    fun fn(n: Int) {
        assert(n in 1..12)
        code(0x39 + n)
    }

    fun number(number: Int) {
        assert(number in 0..9)
        if (number == 0) {
            return code(0x27)
        } else {
            code(0x1d + number)
        }
    }

    fun number(number: Char) {
        assert(number in '0'..'9')
        number(number - '0')
    }

    fun letter(letter: Char) {
        assert(letter in 'a'..'z' || letter in 'A'..'Z')

        if (letter in 'a'..'z') {
            code(letter - 'a' + 0x04)
        } else {
            code(letter - 'A' + 0x04)
        }
    }

    fun numpad(number: Int) {
        assert(number in 0..9)
        if (number == 0) {
            return code(0x62)
        } else {
            code(number + 0x58)
        }
    }

    fun numpad(number: Char) {
        assert(number in '0'..'9')
        return code(number - '0')
    }

    fun text(ch: Char) {
        when (ch) {
            '\b' -> backspace()
            '\u007F' -> delete()
            '\t' -> tab()
            '\n' -> enter()
            ' ' -> space()
            in 'a'..'z' -> letter(ch)
            in 'A'..'Z' -> lshift { letter(ch) }
            in '0'..'9' -> number(ch)
            '!' -> lshift { number(1) }
            '@' -> lshift { number(2) }
            '#' -> lshift { number(3) }
            '$' -> lshift { number(4) }
            '%' -> lshift { number(5) }
            '^' -> lshift { number(6) }
            '&' -> lshift { number(7) }
            '*' -> lshift { number(8) }
            '(' -> lshift { number(9) }
            ')' -> lshift { number(0) }
            ';' -> semicolon()
            ':' -> lshift { semicolon() }
            '=' -> equalSign()
            '+' -> lshift { equalSign() }
            ',' -> comma()
            '<' -> lshift { comma() }
            '-' -> minus()
            '_' -> lshift { minus() }
            '.' -> period()
            '>' -> lshift { period() }
            '/' -> forwardSlash()
            '?' -> lshift { forwardSlash() }
            '`' -> tilde()
            '~' -> lshift { tilde() }
            '[' -> openBracket()
            '{' -> lshift { openBracket() }
            '\\' -> backSlash()
            '|' -> lshift { backSlash() }
            ']' -> closeBracket()
            '}' -> lshift { closeBracket() }
            '\'' -> singleQuote()
            '"' -> lshift { singleQuote() }
            else -> throw RuntimeException("Unable to sendKeys character '$ch'")
        }
    }

    fun text(text: String) {
        text.forEach { text(it) }
    }

    operator fun String.unaryPlus(): Unit = text(this)

    operator fun Char.unaryPlus(): Unit = text(this)
}