package reconAndBilling.sftp.connect;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.*;

import javax.swing.*;

public class ConnectToSftp {
    public static void main(String[] args) {
        ConnectToSftp connectToSftp = new ConnectToSftp();
        connectToSftp.connectTosftp();
    }

    public void connectTosftp(){

        JSch jSch = new JSch();
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;

        String sftpHost="196.31.21.115",sftpUser="VodBusOps_UAT",password="gbGtrNezS7&MJb";
        int sftpPort = 1349;

        try {

            //connection
            session = jSch.getSession(sftpUser,sftpHost,sftpPort);
            session.setPassword(password);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking","no");
            session.setConfig(config);

            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();

            channelSftp = (ChannelSftp)channel;

            if (channelSftp.isConnected()) {
                System.out.println("connected to sftp server ");
                Vector fileList = channelSftp.ls("/");
                System.out.println(fileList.size());
                getFile("/");
                channelSftp.disconnect();
            }

            //upload file

//            File file = new File("/file/location");
//            FileInputStream fileInputStream = new FileInputStream(file);
//            channelSftp.put(fileInputStream,file.getName());
//            fileInputStream.close();

//            //download files
//            String remoLocation = "somelocation/filename.extention";
//            channelSftp.get(remoLocation,"/location/file/will/be/saved/filename.extention");

            //count number of files


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getFile(String dirPath) {

        int count = 0;

        File f = new File(dirPath);
        File[] files = f.listFiles();

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                count++;
                File file = files[i];

                if (file.isDirectory()) {
                    getFile(file.getAbsolutePath());
                }
            }
        }
        for (int i = 0; i <files.length ; i++) {
            System.out.println(files[i]);
        }
    }
}
