interface Observable {
    abstract fun addObserver(observer: Observer)
    abstract fun removeObserver(observer: Observer)
}