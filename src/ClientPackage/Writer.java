package ClientPackage;

import ConstantsAndEnumsPackage.ClientType;

public class Writer extends Client {

    public Writer(int id,int numberOfAccess,ClientType type){
        super(id,numberOfAccess,type);
    }

    @Override
    public void run() {

    }
}
