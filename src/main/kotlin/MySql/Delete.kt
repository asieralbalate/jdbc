package MySql
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException
import java.util.*

fun delete(){
    val url = "jdbc:mysql://172.17.0.2:3306/prueba2"
    val usuari = "root"
    val password = "secret"
    val con: Connection

    try {
        con = DriverManager.getConnection(url, usuari, password)

        println("Borra una fila segun su id")
        println("Introduce el id:")
        val idMenu = Scanner(System.`in`).nextLine()

        val deleteSQL = "DELETE FROM USUARIO WHERE ID = ?"

        val preparedStatement: PreparedStatement = con.prepareStatement(deleteSQL)
        preparedStatement.setString(1, idMenu)
        preparedStatement.executeUpdate()

        selectAll()
        preparedStatement.close()
        con.close()
    } catch (e: SQLException) {
        println("Aun no existe la tabla")
        showMenu()
    }
}