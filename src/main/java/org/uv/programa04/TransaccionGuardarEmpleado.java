package org.uv.programa04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransaccionGuardarEmpleado extends TransaccionDB<Empleado>{
    public TransaccionGuardarEmpleado(Empleado e){
        super(e);
    }
    @Override
            public boolean execute(Connection con){
                try{
                String sql="insert into empleado(clave, nombre, direccion, telefono)"+
                " values (?, ?, ?, ?);";
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setLong(1, p.getClave());
                pst.setString(2, p.getNombre());
                pst.setString(3, p.getDireccion());
                pst.setString(4, p.getTelefono());
                pst.execute();
                return true;
                }catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "No se realizó");
                    return false;
                }
            }
    
}
