package ch04

class DelegatingCollection<T>(
    innerList: Collection<T> = ArrayList<T>()
): Collection<T> by innerList {
}