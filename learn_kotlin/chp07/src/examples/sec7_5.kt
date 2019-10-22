package examples

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

fun s7_5() {
    s7_5_3()
}

open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class PersonA(val name: String, age: Int, salary: Int) : PropertyChangeAware() {

    var age: Int = age
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("age", oldValue, newValue)
        }
    var salary: Int = salary
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("salary", oldValue, newValue)
        }
}



fun s7_5_3() {
    println("***  7.5.3 ****")
    val p = PersonA("Dimitry", 34, 2000)
    p.addPropertyChangeListener(
        PropertyChangeListener {
                event ->
            println("Property ${event.propertyName} cahanged " +
            "from ${event.oldValue} to ${event.newValue}")
        })
    p.age = 35
    p.salary = 2100
}