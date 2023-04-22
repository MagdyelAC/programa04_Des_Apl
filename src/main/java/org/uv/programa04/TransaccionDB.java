package org.uv.programa04;

import java.sql.Connection;

public abstract class TransaccionDB<T> {
    protected T p;
    protected TransaccionDB(T p){
        this.p=p;
    }
    public abstract boolean execute(Connection con);
}
