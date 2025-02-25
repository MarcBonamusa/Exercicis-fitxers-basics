package org.example

import org.example.utils.*
import java.io.File
import java.io.FileNotFoundException

val carpeta = File("./txt")
val fitxer = File(carpeta, "arxiu.txt")

fun main() {
    gestioFitxer()
    gestioFitxerMenu()
}

fun gestioFitxerMenu() {
    val sortir: Boolean = false
    do {
        println(verMenu())
        print("Introdueix una opció: ")
        val opcio: Int = readln().toInt()
        when (opcio) {
            1 -> mostrarFitxer()
            2 -> mostrarLiniaFitxer()
            3 -> escriureFitxer()
            4 -> escriureLiniaPrincipiFitxer()
        }
    } while (!sortir)
}

fun gestioFitxer() {
    if (!carpeta.exists()) carpeta.mkdirs()
    if (!fitxer.exists()) fitxer.createNewFile()
}

fun mostrarFitxer() {
    fitxer.forEachLine { println(it) }
}

fun mostrarLiniaFitxer() {
    println("Introdueix el numero de linia que vols veure: ")
    val numeroLinia: Int = readln().toInt()
    var linia: Int = 1
    /*
    / // forma gens eficient
    fitxer.forEachLine {
        if (numeroLinia == linia) {
            println(it)
            linia++
        }
    }
    */
    val linies: List<String> = fitxer.readLines()
    if (linies.size < numeroLinia) {
        println("El contigut del fitxer es menor a la linia introduida")
    } else {
        println(linies[numeroLinia - 1])
    }
}

fun escriureFitxer() {
    var acabar: Boolean = false
    do {
        print("Introdueix una frase: ")
        val frase: String = readln()
        if (frase == "") {
            println(RED_BOLD_BRIGHT + "No has introduit cap text" + RESET)
            acabar = true
        } else if (frase == "@ESBORRA") {
            fitxer.writeText("")
            println(RED_BOLD_BRIGHT + "S'ha esborrat el contingut del fitxer" + RESET)
        } else {
            try {
                fitxer.appendText(frase + "\n")
            } catch (fne: FileNotFoundException) {
                println(RED_BOLD_BRIGHT + fne.message + RESET)
                acabar = true
            }
        }
    } while (!acabar)
}

fun escriureLiniaPrincipiFitxer() {
    val contingut: String = fitxer.readText()
    print("Introdueix una frase: ")
    val frase: String = readln()
    fitxer.writeText(frase + "\n" + contingut)
}

fun verMenu(): String {
    return "-------------------- MENU --------------------\n" +
            "1. Mostrar contigut fitxer\n" +
            "2. Mostrar una linia concreta del fitxer\n" +
            "3. Escriu en el fitxer\n" +
            "4. Escriure a la linia del principi del fitxer\n" +
            "----------------------------------------------"
}
