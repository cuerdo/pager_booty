package be.rlab.hellobot

import be.rlab.tehanu.SpringApplication
import be.rlab.tehanu.config.SlackBeans
import be.rlab.tehanu.config.TelegramBeans
import be.rlab.tehanu.domain.model.ChatType
import org.springframework.context.support.beans

class Main : SpringApplication() {

    override fun initialize() {
        val beans = beans {
            bean { SayHello(name = "say_hello", botName = "@hellobot", memory = ref()) }
            bean { SayHelloCommand(name = "/say_hello", botName = "@hellobot", scope = listOf(ChatType.PRIVATE, ChatType.GROUP), memory = ref()) }
        }

        applicationContext.apply {
            beans.initialize(this)
            TelegramBeans.beans(resolveConfig()).initialize(this)
        }
    }
}

fun main(args: Array<String>) {
    Main().start()
}
