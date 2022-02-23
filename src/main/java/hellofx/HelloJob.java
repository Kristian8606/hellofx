package hellofx;

import javafx.application.Platform;
import org.quartz.Job;
import org.quartz.JobExecutionContext;


public class HelloJob implements Job  {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        SendEmail.send();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    Controller.myController.PrintTask();
                    Controller.myController.RestartStage();
                });
            }

        });
        thread.setDaemon(true);
        thread.start();


        /*
        System.out.println("task Job "+Time.getTime()+" ");


           new Thread(()  -> {
                Platform.runLater(() -> {
                    Controller.myController.PrintTask();
                    Controller.myController.RestartStage();
                });
            }).start();

         */

    }
}
