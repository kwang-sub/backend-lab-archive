package ch04

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

/*
fun TalkativeButton.giveSpeech() {
    yell()
    whisper()
}*/
