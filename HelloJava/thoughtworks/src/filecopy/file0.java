package filecopy;
//文件的拷贝
import java.io.*;

class FileUntil0 {//定义一个文件操作工具类
    private File srcFile;//原路径
    private File desFile;//目标路径

    public FileUntil0(String src, String des) {
        this(new File(src), new File(des));
    }

    public FileUntil0(File srcFile, File desFile) {
        this.srcFile = srcFile;
        this.desFile = desFile;
    }

    public boolean copy() throws Exception {  //文件拷贝处理

        if (!this.srcFile.exists()) {  //文件必须存在
            System.out.println("要拷贝的源文件不存在!");
            return false;
        }
        if (!this.desFile.getParentFile().exists()) {
            this.desFile.getParentFile().mkdirs();  //如果父目录不存在就创建父目录
        }

        //byte data[] = new byte[1024];//开辟一个拷贝的缓冲区
        InputStream input = null;
        OutputStream output = null;

        try {
            input = new FileInputStream(this.srcFile);
            output = new FileOutputStream(this.desFile);
/*
            int len = 0;//当len=-1的时候拷贝完成
            while ((len = input.read(data)) != -1) {
                output.write(data, 0, len);
            }*/
            input.transferTo(output);
            return true;
        } catch (Exception e) {//异常类型太多，所以用Exception
            throw e;
        } finally {  //无论出不出错都要关闭
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }
}

public class file0 {
    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();//用来计时耗时，开始时间

        FileUntil0 fu = new FileUntil0("d:\\123.txt", "d:\\456\\789.txt");
        System.out.println(fu.copy() ? "拷贝成功" : "拷贝失败");//返回true就是拷贝成功，返回false就是拷贝失败

        long end = System.currentTimeMillis();//结束时间
        System.out.println("拷贝用时：" + (end - start));
    }
}

