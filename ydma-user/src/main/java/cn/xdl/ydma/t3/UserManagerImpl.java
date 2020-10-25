package cn.xdl.ydma.t3;

public class UserManagerImpl implements IUserManager {
    @Override
    public void addUser(String id, String password) {
        System.out.println("======调用了UserManagerImpl.addUser()方法======");
    }
}
