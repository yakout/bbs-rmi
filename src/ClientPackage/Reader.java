package ClientPackage;

import ConstantsAndEnumsPackage.ClientType;

public class Reader extends Client{
    public Reader(int id,int numberOfAccess,ClientType type){
       super(id,numberOfAccess,type);
    }

    @Override
    public void run() {

    }
}
