package org.uv.programa04;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOEmpleado implements IDAOGeneral<Empleado, Long>{
    private PreparedStatement query=null; 
    @Override
    public Empleado create(Empleado p) {
        ConexionDB cx=ConexionDB.getInstance();
        TransaccionDB tdb=new TransaccionDB<Empleado>(p){
            @Override
            public boolean execute(Connection con){
                try{
                    query=con.prepareStatement("insert into empleados(clave, nombre, direccion, telefono)"+
                    " values (?, ?, ?, ?);");
                    query.setLong(1, p.getClave());
                    query.setString(2, p.getNombre());
                    query.setString(3, p.getDireccion());
                    query.setString(4, p.getTelefono());
                    query.execute();
                    return true;
                }catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean res=cx.execute(tdb);
        if (res)
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Se guardo");
        else
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Se guardo");
        
        return p;
    }

    @Override
    public boolean delete(Long id) {
        ConexionDB cx=ConexionDB.getInstance();
        TransaccionDB tdb=new TransaccionDB <Long>(id){
        
            @Override
            public boolean execute(Connection con){
                try{
                String sql="Delete from empleados where clave=?";
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setString(1, String.valueOf(id));
                pst.execute();
                return true;
                }catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "No se realizó");
                    return false;
                }
        
            }
        };
        boolean res=cx.execute(tdb);
        if (res)
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Se ha borrado");
        else
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "No se realizó");
        

        return res;
    }

    @Override
    public Empleado update(Empleado p, Long id) {
        ConexionDB cx=ConexionDB.getInstance();
        TransaccionDB tdb= new TransaccionDB<Empleado>(p){
            @Override
            public boolean execute(Connection con){
                try{
                    String sql="Update empleados set nombre=?, direccion=?, telefono=? where clave=?";
                    PreparedStatement pst=con.prepareStatement(sql);
                    pst.setString(1, p.getNombre());
                    pst.setString(2, p.getDireccion());
                    pst.setString(3, p.getTelefono());
                    pst.setString(4, String.valueOf(id));
                    pst.execute();
                    return true;
                }catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, null, ex);
                    return false;
                }
            }
        };
        boolean res=cx.execute(tdb);
        if (res)
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Se actualizó");
        else
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "No se realizó");
        

        return p;  
    }

    @Override
    public List<Empleado> findAll() {
        List<Empleado> listaEmpleados=new ArrayList<Empleado>();
        ConexionDB cx=ConexionDB.getInstance();
        TransaccionDB tdb=new TransaccionDB<List<Empleado>>(listaEmpleados){
        @Override
            public boolean execute(Connection con){
                try{
                String sql="Select * from empleados";
                PreparedStatement pst=con.prepareStatement(sql);
                ResultSet st=pst.executeQuery();
                while(st.next()){
                listaEmpleados.add(new Empleado(st.getLong("clave"), st.getString("nombre"), st.getString("direccion"), st.getString("telefono")));
                }
                return true;
                }catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "No se realizó");
                    return false;
                }
        
            }
        };
        boolean res=cx.execute(tdb);
        if (res)
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Se obtuvo la información");
        else
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "No se realizó");
        
        return listaEmpleados;
    }

    @Override
    public Empleado findbyID(Long id, Empleado emp) {
        ConexionDB cx=ConexionDB.getInstance();
        TransaccionDB tdb=new TransaccionDB<Empleado>(emp){
        @Override
            public boolean execute(Connection con){
                try{
                String sql="Select * from empleados where clave=?";
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setString(1, String.valueOf(id));
                ResultSet st=pst.executeQuery();
                if (st.next()){
                emp.setClave(st.getLong(1));
                emp.setNombre(st.getString(2));
                emp.setDireccion(st.getString(3));
                emp.setTelefono(st.getString(4));}
                return true;
                }catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "No se realizó", ex);
                    return false;
                }
        
            }
        };
        boolean res=cx.execute(tdb);
        if (res)
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Se obtuvo la información");
        else
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "No se realizó");
        
        return emp;
    }
    
}
