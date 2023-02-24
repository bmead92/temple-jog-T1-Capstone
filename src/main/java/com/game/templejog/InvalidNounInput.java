package com.game.templejog;

public enum InvalidNounInput {
    BAD_LOOK("You see nothing of interest."),
    BAD_GET("There is nothing to grab."),
    BAD_USE("You have no item to use."),
    BAD_SOUND("Sound warning template"),
    BAD_NAV("You cannot move in this direction.");
    private final String warning_message;

    private InvalidNounInput(String msg) {
        this.warning_message = msg;
    }

    public String getWarning() {
        return this.warning_message;
    }
}