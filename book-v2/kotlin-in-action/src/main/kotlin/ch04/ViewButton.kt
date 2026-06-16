package ch04

class ViewButton: View {
    override fun getCurrentState(): State = ButtonState()
    override fun restoreState(state: State) {

    }

    class ButtonState: State
}