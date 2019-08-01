import java.util.Random;
import java.util.concurrent.Callable;

public class FileDownloader implements Callable<String> {
    
    private String url;
    
    public FileDownloader(String url) {
        this.url = url;
    }

    public String call() {
        System.out.println("START: " + Thread.currentThread().getId() + " / " + url);
        int delaytime = new Random().nextInt(100) * 100;
        try {
            Thread.sleep(delaytime);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getId() + " / " + delaytime + " / " + url;
    }
}
