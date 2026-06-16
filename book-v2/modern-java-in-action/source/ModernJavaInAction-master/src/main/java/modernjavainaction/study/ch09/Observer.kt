package modernjavainaction.study.ch09

fun interface Observer {
    fun notify(tweet: String)
}

class NyTimes : Observer {
    override fun notify(tweet: String) {
        if (tweet.contains("money")) {
            println("Breaking news in NY! $tweet")
        }
    }
}

class Guardian : Observer {
    override fun notify(tweet: String) {
        if (tweet.contains("queen")) {
            println("Yet more news from London... $tweet")
        }
    }
}

class LeMonde : Observer {
    override fun notify(tweet: String) {
        if (tweet.contains("wine")) {
            println("Today cheese, wine and news! $tweet")
        }
    }
}

interface Subject {
    fun registerObserver(o: Observer)
    fun notifyObservers(tweet: String)
}

class Feed : Subject {
    private val observers = mutableListOf<Observer>()

    override fun registerObserver(o: Observer) {
        this.observers.add(o)
    }

    override fun notifyObservers(tweet: String) {
        observers.forEach { it.notify(tweet) }
    }
}

fun main() {
    val f = Feed()
    f.registerObserver(NyTimes())
    f.registerObserver(Guardian())
    f.registerObserver(LeMonde())
    f.notifyObservers("The queen said her favourite book is Modern Java in Action!")
}


