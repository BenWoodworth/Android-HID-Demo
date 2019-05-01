package net.benwoodworth.androidhiddemo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_keyboard.*
import net.benwoodworth.androidhiddemo.hid.HidKeyboardDsl

class Keyboard : Fragment() {

    private lateinit var hidGadgets: HidGadgets

    companion object {
        fun newInstance(hidGadgets: HidGadgets) = Keyboard().apply {
            this.hidGadgets = hidGadgets
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keyboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        codeSnippets.forEach { snippet ->
            val button = Button(context).apply {
                text = snippet.name
                setOnClickListener {
                    hidGadgets.keyboard.value?.invoke {
                        snippet.writeCode(this)
                    }
                }
            }

            buttonLayout.addView(button)
        }
    }

    private class CodeSnippet(
        val name: String,
        val writeCode: HidKeyboardDsl.() -> Unit
    )


    private val codeSnippets = listOf(
        CodeSnippet("Class") {
            +"class CLASS_NAME {\n"
            +"    \n"
            +"}"

            // Highlight CLASS_NAME
            repeat(2) { up() }
            end()
            repeat(2) { left() }
            lshift {
                repeat(10) { left() }
            }
        },

        CodeSnippet("Function") {
            +"fun FUNC_NAME() {\n"
            +"    \n"
            +"}"

            // Highlight FUNC_NAME
            repeat(2) { up() }
            end()
            repeat(4) { left() }
            lshift {
                repeat(9) { left() }
            }
        },

        CodeSnippet("Surprise") {
            +"""
                ZZZZZZZZZZZZZZZZZZZZOOOOOOOOOOOOOOOOOOOOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZO##77III???????IIII777#####ZZZZZZZZOOOOO8
                ZZZZZZZZZZZZZZZZZZZOOOOOOOOOOOOOOOOOOOOOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZO##777IIIIIIIIIII77777#####ZZZZZZZZOOOOO8
                ZZZZZZZZZZZZZZZZZDDDDOOOOOOOZZZZZZZOOOOOOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ##7777IIIIIII777777#######ZZZZZZZOOOOO8
                ZZZZZZZZZZZZZZZZZD88888DDOOZZZZZZZZZZZZOOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZO####7777777777777#########ZZZZZZZZOOOO8
                ZZZZZZZZZZZZZZZZZZD888888D+DZZZZZZZZZZZZOOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZZ####77777777############ZZZZZZZZOOOO8
                ZZZZZZZZZZZZZZZZZODD88888D++++D8ZZZZZZZZZOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ########################ZZZZZZZOOOO8
                ZZZZZZZZZZZZZZZZZDDD88888++++++++DOZZZZZZOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZZZZZZ####################ZZZZZZZOOOO8
                ZZZZZZZZZZZZZZZZO88888888+++++++++++D8ZZZOOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZOOOOOOD
                ZZZZZZZZZZZZZZZZOOOZZD888++++++++++++++DZOOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOODDDI8888
                ZZZZZZZZZZZZZZZO######D88D+++++++++++++++ZDOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZO88OOOOOOOOOOOOO8888888888DD+++++I8888
                ZZZZZZZZZZZZZZO########D8D++++++++++++++++++DOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZO88888888888888888888DD++++++++++D8888
                ZZZZZZZZZZZZZZO#########D8++++++++++++++++++++IDZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZO88888888888888888D++++++++++++++D8888
                ZZZZZZZZZZZZZO#########ZZDD++++++++++++++++++++++DZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ8888888888888DD+++++++++++++++++D888D
                ZZZZZZZZZZZZZZ########ZZZZD++++++++++++++++++++++++DZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZO888888888D+++++++++++++++++++++888D8
                ZZZZZZZZZZZZO#######ZZZZZZZD+++++++++++++++++++++++++DZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZO88888DD+++++++++++++++++++++++D88D88
                ZZZZZZZZZZZZZ#######ZZZZZZZZD++++++++++++++++++++++++++DZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ88D+++++++++++++++++++++++++++D8D888
                ZZZZZZZZZZZOZ#######ZZZZZZOOO++++++++++++++++++++++++++++DZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ8D+++++++++++++++++++++++++++++D8D8888
                ZZZZZZZZZZZZZ#######ZZZZZOOOO8++++++++++++++++++++++++++++DZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZDD++++++++++++++++++++++++++++++++DD88888
                ZZZZZZZZZZOZZ#######ZZZZZOOOOOD+++++++++++++++++++++++++++++OZZZZZZZZZZZ8DDDDDDDDDDDDDDDDDDDDZZZDD++++++++++++++++++++++++++++++++++DD888888
                ZZZZZZZZZZZZZ######ZZZZZOOOOOO8D+++++++++++++++++++++++++++++DD7+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++DD8888888
                ZZZZZZZZZOZZZZZ###ZZZZZZOOOOO8888+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++I8OOOOO888
                ZZZZZZZZZZZZZZZZZZZZZZZOOOOOO88888D++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++IOOOOOOO888
                ZZZZZZZZOZZZZZZZZZZZZZOOOOOOO888888D++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++#OOOOOOOOOO8
                ZZZZZZZZOOOOOOOOOOOOOOOOOOOOO8888888D++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++DZZZZOOOOOOO8
                ZZZZZZZOOOOOOOOOOOOOOOOOOOOO8888888888D+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++DZZZZZZZOOOOO8
                ZZZZZZZOOOOOOOOOOOOOOOOOOO8888888888888D+++++++D++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++D##ZZZZZZZZOOOO8
                ZZZZZZO8888888888888888888888888888888888D+++?8++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++D#####ZZZZZZOOOO8
                ZZZZZZ888888888888888888888888888888888888DID+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++7+++++++ZDZZ###ZZZZZZZZOOOO8
                ZZZZZO8888888888888888888888888888888888888D+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++D++++D8ZZZZZZZZZZZZZZZOOOO8
                ZZZZZ8888888888888888888888888888888888888D++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++8DOZZZZZZZZZZZZZZZZOOOOO8
                ZZZZO8888888888888888888888888888888888888++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++DOOZZZZZZZZZZZZZZZOOOOO8
                ZZZZ88888888DDDDDDDD888888888888888888888D+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++DOOZZZZZZZZZZZZZOOOOOO8
                ZZZO8888888DDDDDDDDDDD8888888888888OO8O8D++++++++++++++DD8DD++++++++++++++++++++++++++++++++++DD8DD+++++++++++++++++++OOOOZZZZZZZZZOOOOOOOO8
                ZZZ88888DDDDDDDDDDDDDDD88888888888OOOOOO8+++++++++++++O~,~88D++++++++++++++++++++++++++++++++Z~,~88D++++++++++++++++++DOOOOOOZOOOOOOOOOOOOO8
                ZZO88888DDDDDDDDDDDDDDD8888888888OOOOOOD+++++++++++++D~,,,888D++++++++++++++++++++++++++++++D~,,,888D++++++++++++++++++DOOOOOOOOOOOOOOOOOOO8
                ZZ88888DDDDDDDDDDDDDDDDD88888888OOOOOOO++++++++++++++D8~~O888D++++++++++++++++++++++++++++++D8~~O888D++++++++++++++++++DOOOOOOOOOOOOOOOOOO88
                ZO88888DDDDDDDDDDDDDDDDD88888888OOOOOOD++++++++++++++D8888888D++++++++++++++++++++++++++++++D8888888D+++++++++++++++++++8OOOOOOOOOOOOOOOO888
                ZO8888DDDDDDDDDDDDDDDDDD8888888OOOOOOO++++++++++++++++888888D++++++++++++++++++++++++++++++++888888D++++++++++++++++++++DOOOOOOOOOOOOOOOO888
                O88888DDDDDDDDDDDDDDDDDD8888888OOOOOOD+++++++++++++++++DD8DD++++++++++++++++++++++++++++++++++DD8DD++++++++++++++++++++++OOOOOOOOOOOOOO88888
                O8888DDDDDDDDDDDDDDDDDDDD888888OOOOOOZ+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++D8888888888O8888888
                88888DDDDDDDDDDDDDDDDDDDD888888OOOOOD++++++++++++++++++++++++++++++++++DDDDD+++++++++++++++++++++++++++++++++++++++++++++I888888888888888888
                88888DDDDDDDDDDDDDDDDDDDD888888OOOOOD++++++++++++++++++++++++++++++++++D8888D+++++++++++++++++++++++++++++++++++++++++++++D88888888888888888
                88888DDDDDDDDDDDDDDDDDDD888888OOOOOO++++++####+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++?####++++++++++++DOOOOOOOOOOOOOO888
                8888888DDDDDDDDDDDDDDD8888888OOOOOOD+++##########+++++++++++++++++++++++++++++++++++++++++++++++++++++###########++++++++++DOOOOOOOOOOOOO888
                8888888888888DDDDD8888888888OOOOOOOD++############+++++++++++++++++++++++++++++++++++++++++++++++++++##############++++++++DOOOOOOOOOOOOOOO8
                8888888888888888888888888OOOOOOOOOO++##############+++++++++++++++++++++++++++++++++++++++++++++++++7##############+++++++++OOOOOOOOOOOOOOO8
                888888888888888888888OOOOOOOOOOOOOZ++##############++++++++++++++++++++++++++I++++++++++++++++++++++###############+++++++++DOOOOOOOOOOOOOO8
                88OOOOOOOOOOOOOOOOOOOOOOOOOOOOZZZZZ+++############+++++++++++++++++++++88????????88+++++++++++++++++?##############+++++++++DZZZOOOOOOOOOOO8
                OOOOOOOOOOOOOOOOOOOOOOOOOOZZZZZZZZZ++++##########++++++++++++++++++++8?????????????8+++++++++++++++++#############+++++++++++DZZZZZZOOOOOOO8
                OOOOOOOOOOOOOOZZZZZZZZZZZZZZZZZZZZZ+++++++####?+++++++++++++++++++++87?????????????8+++++++++++++++++++##########++++++++++++DZZZZZZZZOOOOO8
                OOOOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZD++++++++++++++++++++++++++++++++8I??????????????8+++++++++++++++++++++I##+++++++++++++++++ZZZZZZZZOOOOO8
                OOOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ8+++++++++++++++++++++++++++++++++8??????????????8+++++++++++++++++++++++++++++++++++++++++DZZZZZZZZOOOO8
                OOOOOZZZZZZZZZZZZZ###############ZZZD++++++++++++++++++++++++++++++++?8????????????8++++++++++++++++++++++++++++++++++++++++++DZZZZZZZZOOOO8
                OOOOZZZZZZZZZ#####################ZZZI+++++++++++++++++++++++++++++++++88?????????O8+++++++++++++++++++++++++++++++++++++++++++##ZZZZZZOOOO8
                OOOOZZZZZZZZZ#####################ZZZD+++++++++++++++++++++++++++++++++++888888888+++++++++++++++++++++++++++++++++++++++++++++DZZZZZZZOOOO8
                OOOOOZZZZZZZZZZ##################ZZZZZD++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++DZZZZZZZOOOO8
                OOOOOZZZZZZZZZZZZ##############ZZZZZZZZD+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++DZZZZZZZOOOO8
                OOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZD+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ZZZZZZOOOOO8
                OOOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZO+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++DZZZZZOOOOO8
                OOOOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOOD++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++DZZZZOOOOOO8
                OOOOOOOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOOOOD+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++DZZOOOOOOOO8
                OOOOOOOOOOOZZZZZZZZZZZZZZZZZZZZZZZZOOOOOOOD++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++OOOOOOOOOO8
                OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++OOOOOOOOOO8
                ZZOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOD+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++DOOOOOOOO88
                ZZZOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++DOOOOOOO888
                ZZZZOOOOOOOOOOOOOO8888888888888888888888D++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++D8888888888
            """.trimIndent()
        }
    )
}
