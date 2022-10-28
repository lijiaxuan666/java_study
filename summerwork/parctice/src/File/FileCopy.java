package File;

import java.io.*;

class FileUntil {
    private File srcFile;//原路径
    private File desFile;//目标路径

    public FileUntil(String src, String des) {
        this(new File(src), new File(des));
    }

    public FileUntil(File srcFile, File desFile) {
        this.srcFile = srcFile;
        this.desFile = desFile;
    }

    private void copyDir(File file) throws Exception {
        if (file.isDirectory()) {
            File results[] = file.listFiles(); //列出目录组成
            if (results != null) {
                for (int x = 0; x < results.length; x++) {
                    copyDir(results[x]);  //目录的拷贝要用到递归
                }
            }
        } else { //如果是文件
            String newFilePath = file.getPath().replace(this.srcFile.getPath() + File.separator, "");
            //此处replace的作用是：例如将ljx/abc/123,替换为abc/123,以便直接存入目标文件及中，防止出现 LLLjx/ljx/abc/123的现象
            File newFile = new File(this.desFile, newFilePath); //将新路径重新封装
            this.copyFile(file, newFile);
        }
    }

    private boolean copyFile(File srcFile, File desFile) throws Exception { //拷贝文件
        if (!desFile.getParentFile().exists()) {
            desFile.getParentFile().mkdirs();  //如果父目录不存在就创建父目录
        }

        InputStream input = null;
        OutputStream output = null;

        try {
            input = new FileInputStream(srcFile);
            output = new FileOutputStream(desFile);
            //input.transferTo(output);  //JDK1.9之后才可以使用
            byte[] bytes=new byte[1024];
            int len=0;
            while((len=input.read(bytes))!=-1){
                output.write(bytes,0,len);
            }
            return true;
        } catch (Exception e) {
            throw e;
        } finally {
                input.close();
                output.close();
        }
    }

    public boolean copy() throws Exception {
        if(srcFile.isFile()){
            if (!this.srcFile.exists()) {  //文件必须存在
                System.out.println("要拷贝的源文件不存在!");
                return false;
            }
            return this.copyFile(this.srcFile, this.desFile);
        }else{
            copyDir(this.srcFile);
            return true;
        }
    }
}

public class FileCopy {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        FileUntil fu = new FileUntil("d:/ljx", "d:/LLLjx");
        if(fu.copy()){
            System.out.println("拷贝成功!");
        }else{
            System.out.println("拷贝失败!");
        }
        long end = System.currentTimeMillis();
        System.out.println("拷贝用时：" + (end - start)+"毫秒");
    }
}