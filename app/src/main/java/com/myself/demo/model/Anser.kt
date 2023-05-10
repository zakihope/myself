package com.myself.demo.model

class Anser(
    val id: Int,
    val id_Q: Int,
    val value: Int,
    val text: String){
constructor(id: Int, value: Int, text: String) : this(id, 0, value, text) {
    // Additional initialization logic, if needed
}constructor(id: Int) : this(id, 0, 0, "") {
    // Additional initialization logic, if needed
}
}