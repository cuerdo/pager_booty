package be.rlab.hellobot

import be.rlab.tehanu.domain.Command
import be.rlab.tehanu.domain.Memory
import be.rlab.tehanu.domain.MessageContext
import be.rlab.tehanu.domain.MessageListener
import be.rlab.tehanu.domain.model.*

class SayHelloCommand(
    override val name: String,
    override val scope: List<ChatType>,
    private val botName: String,
    memory: Memory
) : Command {

    var names: List<String> by memory.slot("SayHello::names", emptyList<String>())

    override fun handle(
        context: MessageContext,
        message: Message
    ): MessageContext = context.userInput {
        val input:String by field("decime el nombre") {
            keyboard {
                button(context.user?.userName ?: "unknown", context.user?.userName ?: "unknown")
            }
        }

        onSubmit {
            if (names.contains(input)) {
                context.answer("kc $input!, tu id es = ${context.user?.id}")
/*                names = names.filter { name ->
                   name != input
                 }
*/          } else {
                context.answer("un gusto $input!")
                names = names + input
            }
        }
    }
}
