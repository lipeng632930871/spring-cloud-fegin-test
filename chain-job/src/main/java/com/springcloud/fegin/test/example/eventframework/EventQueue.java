package com.springcloud.fegin.test.example.eventframework;

import com.springcloud.fegin.test.example.event.Event;
import com.springcloud.fegin.test.example.event.EventType;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * Description: the queue for collect event
 *
 * @author 003186
 */
@Slf4j
public class EventQueue {
    public static ConcurrentHashMap<Integer, ArrayBlockingQueue> queueMap = null;

    static {
        queueMap = new ConcurrentHashMap<>(16);
        IntStream.range(0,10).forEach(i->{
            ArrayBlockingQueue queue = new ArrayBlockingQueue(500);
            queueMap.put(i, queue);
        });
    }


    /**
     * long time event queue
     */
    public static final ArrayBlockingQueue overtimeLongTimeTaskQueue = new ArrayBlockingQueue(2000);

    public static void submitByRound(Event event){
        log.info("commit event:{}  {}",event.getId() % 10, event);
        ArrayBlockingQueue arrayBlockingQueue= null;
        try {
            String code = event.getType().getCode();
            if (EventType.CXC_SITE_CLOSE_REFUND.getCode().equals(code)) {
                log.info("overtimeLongTimeTaskQueue:{}",event);
                overtimeLongTimeTaskQueue.put(event);
                return;
            }

            if (event.getId() != null) {
                /**
                 * id%10
                 */
                Long n = event.getId() % 10;
                arrayBlockingQueue = queueMap.get(n.intValue());
                log.info("arrayBlockingQueue {}:{}",n,event);
                arrayBlockingQueue.put(event);
            }
        } catch (InterruptedException e) {
            log.error("arrayBlockingQueue", e);
        } catch (Exception e) {
            log.error("arrayBlockingQueue", e);
        }

    }

    public static void main(String[] args) {

       /* LongStream.range(0,200).forEach(i->{
            System.out.println(i % 10);
        });*/
        ArrayBlockingQueue n = new ArrayBlockingQueue(10000);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
