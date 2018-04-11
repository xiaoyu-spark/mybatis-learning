package training.utils;




import java.io.*;
import java.nio.channels.FileChannel;
import java.util.List;

public class FileUtils {
    public static void deleteAllSubFile(File file){
        if(file.exists() && file.isDirectory()){
            File[] tempFileArr = file.listFiles();
            File tempFile = null;
            for (int i = 0; i < tempFileArr.length; i++) {
                tempFile = tempFileArr[i];
                if (tempFile.isFile()) {
                    tempFile.delete();
                }
                if (tempFile.isDirectory()) {
                    deleteFolder(tempFile);
                }
            }
        }
    }
    public static void deleteFolder(File folderFile) {
        if(folderFile.exists()) {
            if (folderFile.isDirectory() && folderFile.list().length > 0){
                deleteAllSubFile(folderFile);
            }
            folderFile.delete();
        }
    }
    public static void deleteFileList(List<File> fileList){
        for(int i=fileList.size()-1;i>=0;i--){
            File file = fileList.get(i);
            fileList.remove(i);
            if(file.exists() && file.isFile()){
                file.delete();
            }
        }
    }
    public static void deleteAllEmptyFolder(File file){
        if(file.exists() && file.isDirectory()){
            File[] tempFileArr = file.listFiles();
            if(tempFileArr.length == 0){
                file.delete();
                return ;
            }
            File tempFile = null;
            for (int i = 0; i < tempFileArr.length; i++) {
                tempFile = tempFileArr[i];
                if (tempFile.isFile()) {
                    break;
                }
                if (tempFile.isDirectory()) {
                    deleteAllEmptyFolder(tempFile);
                }
            }
            tempFileArr = file.listFiles();
            if(tempFileArr.length == 0){
                file.delete();
            }
        }
    }

    public static File[] getSubFile(File file){
        File[] fileArr = null;
        File[] totalFileArr = null;
        if(file.exists() && file.isDirectory()){
            fileArr = file.listFiles();
            totalFileArr = fileArr;
            File tempFile = null;
            for (int i = 0; i < fileArr.length; i++) {
                tempFile = fileArr[i];
                if (tempFile.isDirectory()) {
                    File[] subFileArr = getSubFile(tempFile);
                    if(null != subFileArr && subFileArr.length > 0){
                        totalFileArr = ArrayUtils.merge(totalFileArr,subFileArr);
                    }
                }
            }
        }
        return totalFileArr;
    }
    public static void moveFileListToDir(List<File> fileList,File toDir) throws IOException {
        if(null != fileList && fileList.size() > 0){
            for(File file : fileList){
                if(file.exists() && file.isFile()){
                    if(!toDir.exists() || !toDir.isDirectory()){
                        toDir.mkdirs();
                    }
                    copyFile(file,new File(toDir,file.getAbsolutePath()),true);
                }
            }
        }
    }
    public static boolean copyFile(File srcFile, File destFile,boolean overlay) {

        if (!srcFile.exists()) {

            return false;
        } else if (!srcFile.isFile()) {
            return false;
        }

        if (destFile.exists()) {
            if (overlay) {
                destFile.delete();
            }
        } else {
            if (!destFile.getParentFile().exists()) {
                if (!destFile.getParentFile().mkdirs()) {
                    return false;
                }
            }
        }

        int byteread = 0;
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];

            while ((byteread = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteread);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //fastest than io
    public static void copyFileOnNIO(String sourceFilePath, String targetFilePath) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel targetChannel = null;
        try {
            RandomAccessFile sourceFile = new RandomAccessFile(sourceFilePath,"rw");
            RandomAccessFile targetFile = new RandomAccessFile(targetFilePath,"rw");
            sourceChannel = sourceFile.getChannel();
            targetChannel = targetFile.getChannel();
            targetChannel.transferFrom(sourceChannel,0,sourceChannel.size());

        }finally {
            sourceChannel.close();
            targetChannel.close();
        }

    }
}
