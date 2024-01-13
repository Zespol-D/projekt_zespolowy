import android.os.StrictMode
import android.util.Log
import java.sql.DriverManager
import java.sql.Connection
import java.sql.SQLException



//klasa połączeniowa
class Connection {


    //adress hosta
    private val id = "192.168.0.105:1433"
    //nazwa bazy danych
    private val db = "NurturGuide"

    //Login i hasło użytkownika z nadanymi uprawnieniami do bazy danych
    private val user = "nuturAdmin"
    private val pass = "admin"

    //Funkcja łącząca się z bazą dancyh
    fun conn():Connection?{
        val policy= StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn: Connection? = null
        var connString: String? = null
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            //Adres URL za pomocą którego aplikacja łączy się z bazą danych
            connString = "jdbc:jtds:sqlserver://"+id+";databaseName="+db+";user="+user+";password="+pass+";"

            /* Definiowanie połączenia z bazą za pomocą adresu URL (Bartuś pamiętaj o edycji adresu lub o za
            utomatyzownaiu tego procesu */
            conn = DriverManager.getConnection(connString)

        }

        //Exceptions które będą widoczne dla wygody muszę zmienić je na cyfrowe jak komunikaty HTTP

        catch (ex: SQLException){
            Log.e("Error1: ", ex.message.toString())
        }
        catch (ex1: ClassNotFoundException){
            Log.e("Error2: ",ex1.message.toString())
        }
        catch (ex2:Exception){
            Log.e("Error3:", ex2.message.toString())
        }

        return conn;
    }
}
