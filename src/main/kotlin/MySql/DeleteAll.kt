package MySql

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException

fun deleteAll(){
    val url = "jdbc:mysql://172.17.0.2:3306/prueba2"
    val usuari = "root"
    val password = "secret"
    val con: Connection

    try {
        con = DriverManager.getConnection(url, usuari, password)

        val deleteSQL = "TRUNCATE TABLE USUARIO"

        val preparedStatement: PreparedStatement = con.prepareStatement(deleteSQL)
        preparedStatement.executeUpdate()

        preparedStatement.close()
        con.close()
    } catch (e: SQLException) {
        println("Aun no existe la tabla")
        showMenu()
    }
}