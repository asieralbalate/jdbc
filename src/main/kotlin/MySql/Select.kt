package MySql
import java.sql.*
import java.util.*

fun select() {
    val url = "jdbc:mysql://172.17.0.2:3306/prueba2"
    val usuari = "root"
    val password = "secret"
    val con: Connection

    try {
        con = DriverManager.getConnection(url, usuari, password)
        val st = con.createStatement()

        println("Introduce los campos a filtrar separados por comas si quieres ver todo introduce *")
        val input = Scanner(System.`in`).nextLine()


        if (input.equals('*')) {
            val sentenciaSQL = "SELECT * FROM USUARIO"
            val rs = st.executeQuery(sentenciaSQL)
            val preparedStatement = con.prepareStatement(sentenciaSQL)
            System.out.println("ID. \tNombre \tUser \tPassword \tTelefono \tEmail")
            System.out.println("---------------------------------------------------------")
            preparedStatement.executeQuery(sentenciaSQL)
            var rowCount = 0
            while (rs.next()) {
                print("" + rs.getInt(1) + "\t")
                print("" + rs.getString(2) + "\t")
                print("" + rs.getString(3) + "\t")
                print("" + rs.getString(4) + "\t")
                print("" + rs.getDouble(5) + "\t")
                println("" + rs.getString(6) + "\t")
                rowCount++
            }

            println("Número de registros: $rowCount")
            preparedStatement.close()
            rs.close()
        } else {
            val niceInput = if (input.endsWith(",")) input.removeSuffix(",") else input
            val sentenciaSQL = "SELECT $niceInput FROM USUARIO"
            val items = niceInput.split(",")
            val rs = st.executeQuery(sentenciaSQL)

            val metaData = rs.metaData
            val columnCount = metaData.columnCount


            var header = ""
            for (i in 1..columnCount) {
                header += metaData.getColumnName(i) + "\t"
            }
            println("")
            println(header)

            var rowCount = 0
            while (rs.next()) {
                for (i in 1..columnCount) {
                    val columnName = metaData.getColumnName(i)
                    val columnType = metaData.getColumnType(i)

                    if (columnType == java.sql.Types.INTEGER) {
                        print("" + rs.getInt(i) + "\t")
                    } else if (columnType == java.sql.Types.DOUBLE) {
                        print("" + rs.getDouble(i) + "\t")
                    } else {
                        print("" + rs.getString(i) + "\t")
                    }
                }
                println("")
                rowCount++
            }
            println("Número de registros: $rowCount")
            println("")
            rs.close()
        }

        st.close()
        con.close()
    } catch (e: SQLException) {
        println("No existe aun la tabla")
        showMenu()
    }
}