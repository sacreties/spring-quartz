package com.test.spring.quartz.service.rest;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.test.spring.quartz.job.JobInfo;
import com.test.spring.quartz.service.SchedulerService;

@RestController
public class SchedulerRestService {

   @Autowired
   private SchedulerService service;

   @Autowired
   private ObjectMapper mapper;

   @PostMapping(path = "/schedule", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody
   JsonNode schedule(@RequestBody
   JobInfo jobInfo) throws SchedulerException {
      ObjectNode object = mapper.createObjectNode();
      object.put("JobId", service.execute(jobInfo));
      return object;
   }
}
