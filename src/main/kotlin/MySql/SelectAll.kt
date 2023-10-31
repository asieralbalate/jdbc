package MySql

import java.sql.*

fun selectAll(){
    val url = "jdbc:mysql://172.17.0.2:3306/prueba2"
    val usuari = "root"
    val password = "secret"
    val con: Connection

    try {
        con = DriverManager.getConnection(url, usuari, password)
        val sentenciaSQL = "SELECT * FROM USUARIO"

        val preparedStatement: PreparedStatement = con.prepareStatement(sentenciaSQL)
        val rs: ResultSet = preparedStatement.executeQuery()

        println("Resultado de la consulta:")
        System.out.println("ID \tNombre \tUser \tPassword \tTelefono \tEmail\t")
        System.out.println("---------------------------------------------------------")
        while (rs.next()) {
            print("" + rs.getInt(1) + "\t")
            print("" + rs.getString(2) + "\t")
            print("" + rs.getString(3) + "\t")
            print("" + rs.getString(4) + "\t")
            print("" + rs.getInt(5) + "\t")
            println("" + rs.getString(6)+ "\t")
        }

        rs.close()
        preparedStatement.close()
        con.close()
    } catch (e: SQLException) {
        println("Aun no existe la tabla")
        showMenu()
    }
}