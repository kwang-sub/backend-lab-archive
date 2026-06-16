package ch05.atom63

interface Building
interface Kitchen

interface House : Building {
    val kitchens: List<Kitchen>
}

