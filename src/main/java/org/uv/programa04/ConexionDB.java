package org.uv.programa04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {
    private Connection con=null;
    
    private ConexionDB(){
        String url="jdbc:postgresql://localhost:5432/ejemplo";
        String pwd="postgres";
        String user="postgres";
        
        try{
            con=DriverManager.getConnection(url,user,pwd);
          Logger.getLogger(ConexionDB.class.getName()).log(Level.INFO, "Se conectó");
        }catch(SQLException es){
            Logger.getLogger(ConexionDB.class.getName()).log(Level.INFO, "No se conectó");
        }
    }
    public static ConexionDB cx=null;
    public static ConexionDB getInstance(){
        if(cx==null){
            cx=new ConexionDB();
        }
        return cx;
    }
    
    //Se usa la clase abstracta
    public boolean execute(TransaccionDB tdb){
        return tdb.execute(con);
        
    }
}
