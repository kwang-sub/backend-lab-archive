package ch06.item34

enum class PayrollDay(
    private val payType: PayType,
) {
    MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY), WEDNESDAY(PayType.WEEKDAY), THURSDAY(PayType.WEEKDAY),
    FRIDAY(PayType.WEEKDAY), SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);


    enum class PayType {
        WEEKDAY {
            override fun overtimePay(minutesWorked: Int, payRate: Int): Int {
                return if (minutesWorked <= MIN_PER_SHIFT) 0 else (minutesWorked - MIN_PER_SHIFT) * payRate / 2
            }
        },
        WEEKEND {
            override fun overtimePay(minutesWorked: Int, payRate: Int): Int {
                return minutesWorked * payRate / 2
            }
        };

        abstract fun overtimePay(minutesWorked: Int, payRate: Int): Int

        fun pay(minWorked: Int, payRate: Int): Int {
            val basePay = minWorked * payRate
            return basePay + overtimePay(minWorked, payRate)
        }

        companion object {
            private const val MIN_PER_SHIFT: Int = 8 * 60
        }
    }
}