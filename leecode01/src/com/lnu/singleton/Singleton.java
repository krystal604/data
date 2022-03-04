package com.lnu.singleton;

public class Singleton {
    private static boolean flag = false;
    //私有构造方法
    private Singleton (){

        synchronized (Singleton.class){

            if (flag){
                throw new RuntimeException("不能创建多个对象");
            }
            flag = true;

        }
    }

    private static class SingletonHolder {
        //在静态内部类中声明并初始化外部类对象
        private static final Singleton instance = new Singleton();
    }

    //提供公共访问方式
    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }

    //当进行反序列化时，会自动调用此方法，将该方法返回值返回
    public Object readResolve() {
        return SingletonHolder.instance;
    }

}
