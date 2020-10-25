package cn.xdl.ydma.t3;

public class Client {

    public static void main(String[] args) {
        /*JDKProxy jdkProxy =new JDKProxy();

        IUserManager userManagerJDK = (IUserManager) jdkProxy.newProxy(new UserManagerImpl());
        userManagerJDK.addUser("jpeony", "123456");*/


        CGLibProxy cgLibProxy= new CGLibProxy();

        IUserManager userManager =(IUserManager)cgLibProxy.createProxyObject(new UserManagerImpl());
        userManager.addUser("ZH","123");


    }



}

