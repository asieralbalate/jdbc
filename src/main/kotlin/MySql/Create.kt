package MySql

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

fun create() {
    val url = "jdbc:mysql://172.17.0.2:3306/prueba2"
    val usuari = "root"
    val password = "secret"
    val con: Connection

    try {
        con = DriverManager.getConnection(url, usuari, password)

        val createTableSQL = "CREATE TABLE USUARIO " +
                "(ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "Nombre VARCHAR(15)," +
                "Usuari varchar(15)," +
                "Password varchar(10)," +
                "Telefono INT," +
                "Email varchar(50) UNIQUE);"

        val preparedStatement: PreparedStatement = con.prepareStatement(createTableSQL)
        preparedStatement.executeUpdate()

        preparedStatement.close()
        con.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
