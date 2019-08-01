import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 解決したい問題：大量のファイルをダウンロードする際に、直列でやっていると遅いので、
 * 並列処理を行いたい。
 * ExecutorServiceを利用して、並列処理を行うサンプル。
 * @author rsogo
 *
 */
public class ExecutorServiceSample {

    // ダウンロードするURLのリスト
    static final List<String> urlList = new ArrayList<String>(Arrays.asList(
            "a",
            "b",
            "c",
            "e",
            "f",
            "g"
            ));
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        
        // ファイルのダウンロードを並列で開始
        for(String url: urlList) {
            futureList.add(executor.submit(new FileDownloader(url)));
        }
        
        // それぞれのURLのファイルのダウンロードが完了した結果を受け取る
        for(Future<String> future: futureList) {
            try {
                String result = future.get();
                System.out.println("END: " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
