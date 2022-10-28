import java.io.*;

public class FileCopy {
    private File oldFile;//原路径
    private File newFile;//目标路径

    public FileCopy(String src, String des) {
        this(new File(src), new File(des));
    }

    public FileCopy(File srcFile, File desFile) {
        this.oldFile = srcFile;
        this.newFile = desFile;
    }

    private void copyDir(File file) throws Exception {
        if (file.isDirectory()) {
            File results[] = file.listFiles();
            if (results != null) {
                for (int x = 0; x < results.length; x++) {
                    copyDir(results[x]);
                }
            }
        } else { //如果是文件
            if(oldFile.getName().endsWith(".java")){
                String newFilePath = file.getPath().replace(this.oldFile.getPath() + File.separator, "");
                File newFile = new File(this.newFile, newFilePath);
                this.copyFile(file, newFile);
            }
        }
    }

    private boolean copyFile(File srcFile, File desFile) throws Exception { //拷贝文件
        if (!desFile.getParentFile().exists()) {
            desFile.getParentFile().mkdirs();
        }
        InputStream in =new FileInputStream(srcFile);;
        OutputStream out =new FileOutputStream(desFile);

        try {
            byte[] bytes=new byte[1024];
            int len=0;
            while((len=in.read(bytes))!=-1){
                out.write(bytes,0,len);
            }
            return true;
        } catch (Exception e) {
            throw e;
        } finally {
            in.close();
            out.close();
        }
    }

    public boolean copy() throws Exception {
        if(oldFile.isFile()){
            if (!this.oldFile.exists()) {
                System.out.println("文件不存在!");
                return false;
            }else{
                if(oldFile.getName().endsWith(".java"))
                    return this.copyFile(this.oldFile, this.newFile);
            }
        }else copyDir(this.oldFile);
        return true;
    }

}
class test{
    public static void main(String[] args) throws Exception {
        long start=System.currentTimeMillis();
        FileCopy fc=new FileCopy("d:/ljx","d:/LLLLjx");
        if(fc.copy()){
            System.out.println("拷贝成功！");
        }else{
            System.out.println("拷贝失败！");
        }
        long end=System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)+"毫秒");
    }
}