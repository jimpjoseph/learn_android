package examples

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

fun s7_5() {
    s7_5_3()
    s7_5_5()
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

class ObservablePropertyB(
    val propName: String, var propValue: Int,
    val changeSupport: PropertyChangeSupport
) {
    fun getValue(): Int = propValue
    fun setValue(newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName,oldValue,newValue)
    }
}

class PersonB(val name: String, age: Int, salary: Int) : PropertyChangeAware() {

    val _age = ObservablePropertyB("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) { _age.setValue(value) }

    val _salary = ObservablePropertyB("salary",salary,changeSupport)
    var salary: Int
        get() =_salary.getValue()
        set(value) { _salary.setValue(value) }
}

class PersonD(
    val name: String, age: Int, salary: Int
) : PropertyChangeAware() {
    var age: Int by ObservableProperty("age", age, changeSupport)
    var salary: Int by ObservableProperty("salary", salary, changeSupport)
}

class ObservableProperty(
    val propName: String, var propValue: Int,
    val changeSupport: PropertyChangeSupport
) {
    operator fun getValue(p: PersonD, prop: KProperty<*>): Int = propValue
    operator fun setValue(p: PersonD, prop:KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName,oldValue,newValue)
    }
}

class PersonC (
    val name:String, age: Int, salary: Int
) : PropertyChangeAware() {
    private val observer = {
        prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)

    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}

fun s7_5_3() {
    println("***  7.5.3 ****")
    val pA = PersonA("Dimitry", 34, 2000)
    pA.addPropertyChangeListener(
        PropertyChangeListener {
                event ->
            println("A: Property ${event.propertyName} changed " +
            "from ${event.oldValue} to ${event.newValue}")
        })
    pA.age = 35
    pA.salary = 2100

    val pB = PersonB("Dimitry", 34, 2000)
    pB.addPropertyChangeListener(
        PropertyChangeListener {
                event ->
            println("B: Property ${event.propertyName} changed " +
                    "from ${event.oldValue} to ${event.newValue}")
        })
    pB.age = 36
    pB.salary = 2200

    val pD = PersonD("Dimitry", 34, 2000)
    pD.addPropertyChangeListener(
        PropertyChangeListener {
                event ->
            println("D: Property ${event.propertyName} changed " +
                    "from ${event.oldValue} to ${event.newValue}")
        })
    pD.age = 36
    pD.salary = 2200

    val pC = PersonD("Dimitry", 34, 2000)
    pC.addPropertyChangeListener(
        PropertyChangeListener {
                event ->
            println("C: Property ${event.propertyName} changed " +
                    "from ${event.oldValue} to ${event.newValue}")
        })
    pC.age = 31
    pC.salary = 1900
}

class PersonE {
    private val _attributes = hashMapOf<String, String>()

    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    val name: String by _attributes
}

fun s7_5_5() {
    println("*** 7.5.5 ***")
    val p = PersonE()
    val data = mapOf("name" to "Dmitry", "company" to "HERE")
    for ((attrName,value) in data) {
        p.setAttribute(attrName,value)
    }
    println(p.name)
}