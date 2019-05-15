package com.blogWeb.activeMQ.controller;

import com.blogWeb.activeMQ.config.ActiveMqName;
import com.blogWeb.activeMQ.producer.Sender;
import com.blogWeb.activeMQ.producer.queue.QueueSender;
import com.blogWeb.activeMQ.producer.topic.TopicSender;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

@RestController
@ResponseBody
@RequestMapping(value = "/activeMQ")
public class SendControlller {

    @Autowired
    private Sender sender;

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private TopicSender topicSender;

    public volatile Integer count = 0;

    @GetMapping(value = "/add/queue")
    public void addQueue() {
        Destination destination = new ActiveMQQueue(ActiveMqName.QUEUE_ONE);
        while (true) {
            sender.sendTemple(destination, "success"+count);
            count++;
        }
    }

    @GetMapping(value = "/add/topic")
    public void addTopic() {
        Destination destination = new ActiveMQTopic(ActiveMqName.TOPIC_ONE);
        sender.sendTemple(destination, "success");
    }

    @GetMapping(value = "/creat/queue")
    public void creatQueue() {
        queueSender.send(ActiveMqName.QUEUE_ONE, "success");
    }

    @GetMapping(value = "/creat/topic")
    public void creatTopic() {
        topicSender.send(ActiveMqName.TOPIC_ONE, "success");
    }

    @GetMapping(value = "/creat/topic2")
    public void creatTopic2() {
        topicSender.send(ActiveMqName.TOPIC_TWO, "success2");
    }


}
