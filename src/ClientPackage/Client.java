package ClientPackage;

import ConstantsAndEnumsPackage.ClientType;

import java.security.PublicKey;

public abstract class Client implements Runnable{
    protected int id = -1;
    protected int numberOfAccess = -1;
    protected ClientType type = null;
    public Client(int id,int numberOfAccess,ClientType type){
        this.id = id;
        this.numberOfAccess = numberOfAccess;
        this.type = type;
    }
}
