package ch04

import javax.naming.Context
import javax.swing.text.AttributeSet

class MyButton : View2 {
    constructor(ctx: Context)
            : super(ctx)

    constructor(ctx: Context, attr: AttributeSet)
            : super(ctx, attr)
}