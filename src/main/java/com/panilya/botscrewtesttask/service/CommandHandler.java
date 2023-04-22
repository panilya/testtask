package com.panilya.botscrewtesttask.service;

import com.panilya.botscrewtesttask.exception.CommandNotFoundException;

public interface CommandHandler {

    String handleCommand(String commandContent) throws CommandNotFoundException;

}
