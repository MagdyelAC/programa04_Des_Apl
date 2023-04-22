package org.uv.programa04;

import java.util.Iterator;
import java.util.List;

public class Programa04 {

    public static void main(String[] args) {
        DAOEmpleado daoEmpleado=new DAOEmpleado();
        Empleado emp=new Empleado(1, "Aldo", "Calle1", "12345");
        
        TransaccionGuardarEmpleado tge=new TransaccionGuardarEmpleado(emp);
        daoEmpleado.create(emp);
        
        daoEmpleado.delete(10L);
      
        emp.setClave(1);
        emp.setNombre("Aldo");
        emp.setTelefono("12345");
        daoEmpleado.update(emp, emp.getClave());
        
        List<Empleado> lista=daoEmpleado.findAll();
        Iterator <Empleado> i = lista.iterator();
        while(i.hasNext()){
            emp=i.next();
            System.out.println("Clave: "+emp.getClave()+" Nombre: "+emp.getNombre()+" Dirección: "+emp.getDireccion()+" Telefono: "+emp.getTelefono());
        }
      
       Empleado emp2=new Empleado();
       emp2=daoEmpleado.findbyID(25L, emp2);
       System.out.println("Nombre: " +emp2.getNombre());
        
    }
}
