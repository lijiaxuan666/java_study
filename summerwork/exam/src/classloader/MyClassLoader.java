package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executors;

public class MyClassLoader extends ClassLoader{
    private String CLASS_PATH="D:"+File.separator+"My Documents"+File.separator+"Desktop"+File.separator;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes=loadClassData(name);
        if(bytes!=null){
            return defineClass(name,bytes,0,bytes.length-1);
        }
        return  null;
    }

    private byte[] loadClassData(String name){
        String path=CLASS_PATH+name+".class";
        InputStream input=null;
        ByteArrayOutputStream output=null;
        try{
            input=new FileInputStream(new File(path));
            output=new ByteArrayOutputStream();
            int len=0;
            while((len=input.read())!=-1){
                output.write(len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                input.close();
                output.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return output.toByteArray();
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        /*MyClassLoader myClassLoader=new MyClassLoader();
        Class<?> clazz=myClassLoader.findClass("Student");*/
        Class<?> clazz=Person.class;
        System.out.println(clazz.getClassLoader());
        System.out.println(clazz.getClassLoader().getParent());
        System.out.println(clazz.getClassLoader().getParent().getParent());
        Object obj=clazz.getDeclaredConstructor().newInstance();
        Method method=clazz.getMethod("introduce",String.class);
        method.invoke(obj,"ljx");
    }
}
class Person{
    public void introduce(String name){
        System.out.println(name+"是个人");
    }
}
