package be.rlab.hellobot

import be.rlab.tehanu.domain.Memory
import be.rlab.tehanu.domain.MessageContext
import be.rlab.tehanu.domain.MessageListener
import be.rlab.tehanu.domain.model.Chat
import be.rlab.tehanu.domain.model.Message
import be.rlab.tehanu.domain.model.TextMessage
import be.rlab.tehanu.domain.model.User

class SayHello(
    override val name: String,
    private val botName: String,
    memory: Memory
) : MessageListener {

    var names: List<String> by memory.slot("SayHello::names", emptyList<String>())

    override fun applies(
        chat: Chat,
        user: User?,
        message: Message
    ): Boolean {
        return message is TextMessage && message.text.startsWith(botName)
    }

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
                context.answer("kc $input!")
                names = names.filter { name ->
                    name != input
                }
            } else {
                context.answer("un gusto $input!")
                names = names + input
            }
        }
    }
}
