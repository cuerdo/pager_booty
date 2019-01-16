#!/usr/bin/python
from telegram.ext import Updater, CommandHandler
import sys
import env

def hello(bot, update):
     update.message.reply_text('Hello {}'.format(update.message.from_user.first_name))

updater = Updater(env.telegram_token())

updater.dispatcher.add_handler(CommandHandler('hello', hello))

updater.start_polling()
updater.idle()
