package com.test.spring.quartz.job;

public class JobInfo {

   private String cronExpression;

   private String reference;

   public String getCronExpression() {
      return cronExpression;
   }

   public void setCronExpression(String cronExpression) {
      this.cronExpression = cronExpression;
   }

   public String getReference() {
      return reference;
   }

   public void setReference(String reference) {
      this.reference = reference;
   }

}
