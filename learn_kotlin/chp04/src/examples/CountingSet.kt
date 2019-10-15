package examples

class CountingSet<T> (
    val innerSet: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerSet {
    var  objectAdded = 0

    override fun add(element: T): Boolean {
        objectAdded++
        return innerSet.add(element)
    }

    override fun addAll(c: Collection<T>) : Boolean {
        objectAdded += c.size
        return innerSet.addAll(c)
    }
}