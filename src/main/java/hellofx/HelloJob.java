package hellofx;

import org.quartz.Job;
import org.quartz.JobExecutionContext;


public class HelloJob implements Job  {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        SendEmail.send();
        System.out.println("task Job "+Time.getTime()+" ");

    }
}
