
package org.uv.programa04;

import java.util.List;

public interface IDAOGeneral <T,ID>{
    public T create(T p);
    public boolean delete(ID id);
    public T update(T p, ID id);
    
    public List<T> findAll();
    public T findbyID(ID id, T t);
}
