package cn.xdl.ydma.t2;

public class Test {

    public   int atest(){
        int i=0;
        i++;
        return  i;
    }

    public static void main(String[] args) {
        Test t=new Test();
        t.atest();
        int j =t.atest();
        System.out.printf("j="+j);
    }

}
