package com.test.spring.quartz.service;

import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.spring.quartz.job.JobInfo;
import com.test.spring.quartz.job.SampleJob;

@Component
public class SchedulerService {

   private String group = "sample";

   @Autowired
   private Scheduler scheduler;

   public String execute(JobInfo jobInfo) throws SchedulerException {
      String jobId = UUID.randomUUID().toString();
      JobDetail jobDetail = JobBuilder.newJob(SampleJob.class).usingJobData("referenceKey", jobInfo.getReference())
            .withIdentity(jobId, group).build();
      CronTriggerImpl trigger = (CronTriggerImpl) CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression()).build();
      trigger.setName(jobId);
      trigger.setGroup(group);
      trigger.setJobGroup(group);
      trigger.setJobName(jobId);
      scheduler.scheduleJob(jobDetail, trigger);
      return jobId;
   }
}
