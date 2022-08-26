package com.king.springcloud;

import ch.qos.logback.core.util.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author getao
 * @date 2022/7/1 16:31
 */
public class ZipUtils {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        /*// 单文件压缩
        // 源文件路径
        String sourcePath = "C:\\Users\\getao\\Desktop\\s3afn5yfmjj.jpg";
        // 压缩路径
        String targetPath = "C:\\Users\\getao\\Desktop\\james.zip";
        // 调用压缩方法
        pathTOZipFile(sourcePath, targetPath);
        System.out.println("文件：" + sourcePath + "压缩成功啦！");*/

        /*// 多文件、单文件压缩
        // 源文件路径
        String sourcePath = "C:\\Users\\getao\\Desktop\\james";
        // 压缩路径
        String targetPath = "C:\\Users\\getao\\Desktop\\james.zip";
        // 调用压缩方法
        pathFileTOZipFile(sourcePath, targetPath);
        System.out.println("多文件压缩：" + sourcePath + "执行压缩成功!!!");*/

        // 多文件、单文件、文件夹通用压缩
        // 源文件路径
        String sourcePath = "C:\\Users\\getao\\Desktop\\james";
        // 压缩路径
        String targetPath = "C:\\Users\\getao\\Desktop\\james.zip";
        // 调用压缩方法
        pathToZipFile(sourcePath, targetPath);
        System.out.println("文件夹压缩：" + sourcePath + "执行压缩成功!!!");



        /*//被压缩的文件夹
        String sourceFile = "src/main/resources/zipTest";
        //压缩结果输出，即压缩包
        FileOutputStream fos = new FileOutputStream("src/main/resources/dirCompressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(sourceFile);
        //递归压缩文件夹
        zipFile(fileToZip, fileToZip.getName(), zipOut);
        //关闭输出流
        zipOut.close();
        fos.close();*/
    }


    /**
     * 将fileToZip文件夹及其子目录文件递归压缩到zip文件中
     * @param fileToZip 递归当前处理对象，可能是文件夹，也可能是文件
     * @param fileName fileToZip文件或文件夹名称
     * @param zipOut 压缩文件输出流
     * @throws IOException
     */
    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        //不压缩隐藏文件夹
        if (fileToZip.isHidden()) {
            return;
        }
        //判断压缩对象如果是一个文件夹
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                //如果文件夹是以“/”结尾，将文件夹作为压缩箱放入zipOut压缩输出流
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                //如果文件夹不是以“/”结尾，将文件夹结尾加上“/”之后作为压缩箱放入zipOut压缩输出流
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            //遍历文件夹子目录，进行递归的zipFile
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            //如果当前递归对象是文件夹，加入ZipEntry之后就返回
            return;
        }
        //如果当前的fileToZip不是一个文件夹，是一个文件，将其以字节码形式压缩到压缩包里面
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    /**
     * 压缩文件为ZIP格式---通用
     * @param sourcePath 源文件路径，可以使文件获文件夹
     * @param targetPath 压缩目标路径，如：C:/zip/target.zip
     */
    public static void pathToZipFile(String sourcePath, String targetPath) {
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);
        // 如果目标压缩位置不存在，则创建
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdir();
        }
        // 进行压缩处理
        try {
            FileOutputStream outputStream = new FileOutputStream(targetFile);
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            /*
             *  调用压缩方法，这个方法关键在于判定当前操作的文件对象是File还是Directory
             *  如果是文件，直接写入Zip输出流，如果是文件夹，将当前文件夹写入压缩流中，在进
             *  行其中的文件流写入
             */
            toZipFile(sourceFile, sourceFile.getName(), zipOutputStream);
            zipOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行路径压缩
     * @param sourceFile
     * @param fileName 当前操作文件名称，建议以参数形式传入
     * @param zipOutputStream
     */
    public static void toZipFile(File sourceFile, String fileName, ZipOutputStream zipOutputStream) {
        try {
            // 如果是一个文件夹
            if (sourceFile.isDirectory()) {
                // 将文件夹路径写入压缩流中，否则文件将会在同一级目录中压缩
                if (fileName.endsWith("/")) {
                    zipOutputStream.putNextEntry(new ZipEntry(fileName));
                    zipOutputStream.closeEntry();
                } else {
                    zipOutputStream.putNextEntry(new ZipEntry(fileName+"/"));
                    zipOutputStream.closeEntry();
                }
                // 递归调用
                File[] files = sourceFile.listFiles();
                for (File file : files) {
                    toZipFile(file, fileName+"/"+file.getName(), zipOutputStream);
                }
                // 表示当前文件夹已经处理完成，直接结束本次递归
                return;
            }
            // 如果是一个文件，调用前一章中封装的方法进行压缩
            FileInputStream inputStream = null;
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOutputStream.putNextEntry(zipEntry);
            inputStream = new FileInputStream(sourceFile);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len=inputStream.read(buffer)) >=0 ) {
                zipOutputStream.write(buffer, 0, len);
            }
            inputStream.close();
            zipOutputStream.closeEntry();
            zipOutputStream.flush();
            System.out.println("文件：" + sourceFile.getAbsolutePath() + "压缩成功啦！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩文件到指定路径---单个文件适用
     * @param sourcePath 源文件路径
     * @param targetZipPath 目标路径
     */
    public static void pathTOZipFile(String sourcePath, String targetZipPath) {
        File sourceFile = new File(sourcePath);
        File targetZipFile = new File(targetZipPath);
        // 如果目标路径不存在，就创建父级目录，暂不考虑系统权限问题
        if (!targetZipFile.getParentFile().exists()) {
            targetZipFile.getParentFile().mkdir();
        }
        try {
            // 创建目标压缩文件保存路径输出流
            FileOutputStream outputStream = new FileOutputStream(targetZipFile);
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            // 如果是单个文件
            ZipEntry zipEntry = new ZipEntry(sourceFile.getName());
            zipOutputStream.putNextEntry(zipEntry);
            FileInputStream inputStream = new FileInputStream(sourceFile);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len=inputStream.read(buffer)) >=0 ) {
                zipOutputStream.write(buffer, 0, len);
            }
            // 释放资源
            zipOutputStream.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩文件到指定路径---单个文件、多个文件适用
     * @param sourcePath 源文件路径
     * @param targetZipPath 目标路径
     */
    public static void pathFileTOZipFile(String sourcePath, String targetZipPath) {
        File sourceFile = new File(sourcePath);
        File targetZipFile = new File(targetZipPath);
        // 如果目标路径不存在，就创建父级目录，暂不考虑系统权限问题
        if (!targetZipFile.getParentFile().exists()) {
            targetZipFile.getParentFile().mkdir();
        }
        try {
            // 创建目标压缩文件保存路径输出流
            FileOutputStream outputStream = new FileOutputStream(targetZipFile);
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            // 如果源文件路径是文件夹，文件下包含多个文件
            if (sourceFile.isDirectory()) {
                File[] files = sourceFile.listFiles();
                if (files.length != 0) {
                    for (File file : files) {
                        executeToZip(zipOutputStream, file);
                    }
                }
            } else {
                executeToZip(zipOutputStream, sourceFile);
            }
            // 释放资源
            zipOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    /**
     * 执行单个文件压缩---可以作为压缩方法单独使用
     * @param zipOutputStream 目标Zip输出流
     * @param sourceFile 源文件
     */
    public static void executeToZip(ZipOutputStream zipOutputStream, File sourceFile) throws IOException {
        FileInputStream inputStream = null;
        try {
            // 如果是单个文件
            ZipEntry zipEntry = new ZipEntry(sourceFile.getName());
            zipOutputStream.putNextEntry(zipEntry);
            inputStream = new FileInputStream(sourceFile);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len=inputStream.read(buffer)) >=0 ) {
                zipOutputStream.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            System.out.println("文件：" + sourceFile.getAbsolutePath() + "压缩成功啦！");
        }
    }
}
