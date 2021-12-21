package hellofx;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static hellofx.ReadJson.myJson;

public class QuartzTest {
    static Scheduler scheduler;

    public static void run() {

        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            // Trigger the job to run now, and then repeat every day
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(dailyAtHourAndMinute(myJson.getH(), myJson.getM()))
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
            System.out.println("Start Quartz Task scheduler");

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
    public static void stop(){
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
