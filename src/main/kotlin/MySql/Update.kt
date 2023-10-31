package MySql
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException
import java.util.*

fun update (){
    val url = "jdbc:mysql://172.17.0.2:3306/prueba2"
    val usuari = "root"
    val password = "secret"
    val con: Connection

    try {
        con = DriverManager.getConnection(url, usuari, password)
        val updateQuery = "UPDATE USUARIO SET Nombre = ?, Password = ? WHERE Email = ?"
        val preparedStatement: PreparedStatement = con.prepareStatement(updateQuery)

        println("Actualiza una fila")
        println("Introduce el nuevo nombre:")
        val nombre = Scanner(System.`in`).nextLine()
        println("Introduce la nueva contraseña:")
        val pass = Scanner(System.`in`).nextLine()
        println("Introduce el email:")
        val email = Scanner(System.`in`).nextLine()

        preparedStatement.setString(1, nombre)
        preparedStatement.setString(2, pass)
        preparedStatement.setString(3, email)

        selectAll()

        val rowCount = preparedStatement.executeUpdate()
        if (rowCount > 0) {
            println("$rowCount fila(s) actualizada(s) con éxito.")
        } else {
            println("No se encontraron registros para actualizar.")
        }

        selectAll()

        preparedStatement.close()
        con.close()
    } catch (e: SQLException) {
        println("Aún no existe la tabla")
        showMenu()
    }
}