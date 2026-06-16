package ch02.atom23

fun averageIncome(income: Int, months: Int) =
    if (months == 0) throw IllegalArgumentException("Months can't be zero")
    else income / months
