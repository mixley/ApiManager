package cn.crap.utils;

import java.io.*;

/**
 * 文件处理工具类,实现文件的复制、移动、查找、追加内容等。
 * @author huangsq
 * @version 2.0, 2013-10-23
 * @since 1.0, 2013-02-19
 */
public class FileUtil {

    private FileUtil() {
    }

    public static byte[] getFileBytes(File file) {
        byte[] data = null;
        //读取图片字节数组
        try (InputStream in = new FileInputStream(file)) {
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    /**
     * 判断文件是否有读权限
     * @param file
     * @return
     */
    public static Boolean canRead(File file) {
        if (file.isDirectory()) {
            try {
                File[] listFiles = file.listFiles();
                // 返回null表示无法读取或访问，如果为空目录返回的是一个空数组
                if (listFiles == null) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
            // 文件不存在
        } else if (!file.exists()) {
            return false;
        }
        return checkRead(file);
    }

    /**
     * 检测文件是否有读权限
     * @param file
     * @return
     */
    private static boolean checkRead(File file) {
        try ( FileReader fd  = new FileReader(file)){
            while ((fd.read()) != -1) {
                break;
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 判断文件是否有写权限
     * @param file
     * @return
     */
    public static Boolean canWrite(File file) {
        if (file.isDirectory()) {
            try {
                file = new File(file, "canWriteTestDeleteOnExit.temp");
                if (file.exists()) {
                    boolean checkWrite = checkWrite(file);
                    if (!deleteFile(file)) {
                        file.deleteOnExit();
                    }
                    return checkWrite;
                } else if (file.createNewFile()) {
                    if (!deleteFile(file)) {
                        file.deleteOnExit();
                    }
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return checkWrite(file);
    }

    /**
     * 检测文件是否有写权限
     * @param file
     * @return
     */
    private static boolean checkWrite(File file) {
        boolean delete = !file.exists();
        boolean result = false;
        try (FileWriter fw = new FileWriter(file, true)){
            fw.write("");
            fw.flush();
            result = true;
            return result;
        } catch (IOException e) {
            return false;
        } finally {
            if (delete && result) {
                deleteFile(file);
            }
        }
    }

    /**
     * 删除文件，如果要删除的对象是文件夹，先删除所有子文件(夹)，再删除该文件
     * @param file 要删除的文件对象
     * @return 删除是否成功
     */
    public static boolean deleteFile(File file) {
        return deleteFile(file, true);
    }

    /**
     * 删除文件，如果要删除的对象是文件夹，则根据delDir判断是否同时删除文件夹
     * @param file 要删除的文件对象
     * @param delDir 是否删除目录
     * @return 删除是否成功
     */
    public static boolean deleteFile(File file, boolean delDir) {
        // 文件不存在
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        } else {
            boolean result = true;
            File[] children = file.listFiles();
            // 删除所有子文件和子文件夹
            for (int i = 0; i < children.length; i++) {
                // 递归删除文件
                result = deleteFile(children[i], delDir);
                if (!result) {
                    return false;
                }
            }
            if (delDir) {
                // 删除当前文件夹
                result = file.delete();
            }
            return result;
        }
    }

}