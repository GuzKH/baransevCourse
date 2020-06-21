package appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {
    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app) {
        this.app = app;
        ftp = new FTPClient();
    }

    public void upload (File file, String target, String backup) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(backup); // удаляем старую резервную копию
        ftp.rename(target, backup); // переименовываем
        ftp.enterLocalPassiveMode(); // пассивный режим передачи данных
        ftp.storeFile(target, new FileInputStream(file)); // передача локального файла на удаленную машину
        ftp.disconnect();
    }

    public void restore(String backup, String target) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(target); // удаляем загружженый файл
        ftp.rename(backup, target); // восстанавливаем файл из резерной копии
        ftp.disconnect();
    }
}
