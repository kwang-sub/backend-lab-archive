package ch06

import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.JList

class CopyRowAction(
    val list: JList<String>
) : AbstractAction() {

    override fun isEnabled(): Boolean = list.selectedValue != null

    override fun actionPerformed(e: ActionEvent) {
        val value = list.selectedValue!!
    }

}