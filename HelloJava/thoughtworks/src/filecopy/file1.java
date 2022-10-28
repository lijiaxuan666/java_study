package filecopy;
//目录与文件的拷贝
import java.io.*;

class FileUntil {//定义一个文件操作工具类
    private File srcFile;//原路径
    private File desFile;//目标路径

    public FileUntil(String src, String des) {
        this(new File(src), new File(des));
    }

    public FileUntil(File srcFile, File desFile) {
        this.srcFile = srcFile;
        this.desFile = desFile;
    }

    public boolean copyDir() throws Exception {
        this.copyImpl(this.srcFile);
        return true;
    }

    private void copyImpl(File file) throws Exception {
        if (file.isDirectory()) { //如果他是目录
            File results[] = file.listFiles(); //列出目录组成
            if (results != null) {
                for (int x = 0; x < results.length; x++) {
                    copyImpl(results[x]);  //目录的拷贝要用到递归
                }
            }
        } else { //如果是文件
            String newFilePath = file.getPath().replace(this.srcFile.getPath() + File.separator, "");
            //file.getPath()是获取文件的完整路径。由于在不同系统下，文件分隔符不同，所以用File.separator代替
            //此处replace的作用是：例如将ljx/abc/123,替换为abc/123,以便直接存入目标文件及中，防止出现 LLLjx/ljx/abc/123的现象
            File newFile = new File(this.desFile, newFilePath); //拷贝的目标路径
            this.copyFileImpl(file, newFile);
        }
    }

    private boolean copyFileImpl(File srcFile, File desFile) throws Exception { //一个公共的拷贝文件方法
        if (!desFile.getParentFile().exists()) {
            desFile.getParentFile().mkdirs();  //如果父目录不存在就创建父目录
        }

        InputStream input = null;
        OutputStream output = null;

        try {
            input = new FileInputStream(srcFile);
            output = new FileOutputStream(desFile);
            input.transferTo(output);  //JDK1.9之后才可以使用
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

    public boolean copy() throws Exception {  //文件拷贝处理
        if (!this.srcFile.exists()) {  //文件必须存在
            System.out.println("要拷贝的源文件不存在!");
            return false;
        }
        return this.copyFileImpl(this.srcFile, this.desFile);
    }
}

public class file1 {
    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();//用来计时耗时，开始时间

        FileUntil fu = new FileUntil("d:\\ljx", "d:\\LLLjx");
        if (new File("d:\\ljx").isFile()) {  //是文件就调用拷贝文件方法
            System.out.println(fu.copy() ? "拷贝成功" : "拷贝失败");
        } else {  //是文目录就调用拷贝目录方法
            System.out.println(fu.copyDir() ? "拷贝成功" : "拷贝失败");
        }

        long end = System.currentTimeMillis();//结束时间
        System.out.println("拷贝用时：" + (end - start));
    }
}
