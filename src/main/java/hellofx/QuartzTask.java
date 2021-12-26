package hellofx;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;

public class QuartzTask {

    static Scheduler sched;

    public void run() throws Exception {
      //  Logger log = LoggerFactory.getLogger(HelloJob.class);
        try {
          //  System.out.println("------- Initializing ----------------------");

            // First we must get a reference to a scheduler
            SchedulerFactory sf = new StdSchedulerFactory();
            sched = sf.getScheduler();

            System.out.println("------- Initialization Complete -----------");

            // computer a time that is on the next round minute
            Date runTime = Time.getTime();

            System.out.println("------- Scheduling Job  -------------------");

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();

            // Trigger the job to run on the next round minute
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("Test")
                    .withSchedule(CronScheduleBuilder
                            .dailyAtHourAndMinute(Controller.myController.json.getH(), Controller.myController.json.getM()))
                    .build();

            // Tell quartz to schedule the job using our trigger
            sched.scheduleJob(job, trigger);
         //   System.out.println(job.getKey() + " will run at: " + runTime);

            // Start up the scheduler (nothing can actually run until the
            // scheduler has been started)
            sched.start();

            System.out.println("------- Started Scheduler -----------------");
        }catch (SchedulerException se){
            se.printStackTrace();
        }

    }
    public void stop() throws SchedulerException {
        System.out.println("------- Shutting Down ---------------------");
        try {
            sched.shutdown(true);
        }catch (SchedulerException e){
            e.printStackTrace();
        }

        System.out.println("------- Shutdown Complete -----------------");

    }

}
