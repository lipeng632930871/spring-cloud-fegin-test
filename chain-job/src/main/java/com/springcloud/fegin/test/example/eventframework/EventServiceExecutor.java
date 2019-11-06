package com.springcloud.fegin.test.example.eventframework;

import com.springcloud.fegin.test.example.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class EventServiceExecutor {
    @Autowired
    public EventDispatcher dispatcher;
    private final int THREAD_TOTAL = 30;
//    private final int THREAD_NOTTIMELY_NUM = 5;
    private final int OVERTIME_LONG_TIME_NUM = 1;

    public void startExecute(){
        ExecutorService executorService = null;
        executorService = Executors.newFixedThreadPool(THREAD_TOTAL);

        for (int i = 0; i < EventQueue.queueMap.size(); i++) {
            executorService.submit(new OvertimeTaskRunable(EventQueue.queueMap.get(i)));
        }

        for (int i = 0; i < OVERTIME_LONG_TIME_NUM; i++) {
            executorService.submit(new OvertimeLongRunable());
        }

//        for (int i = 0; i < THREAD_NOTTIMELY_NUM; i++) {
//            executorService.submit(new FixedTimeTaskRunable(i));
//        }
    }

    class OvertimeTaskRunable implements Runnable{
        private ArrayBlockingQueue<Event> queue;
        public OvertimeTaskRunable(ArrayBlockingQueue queue) {
            this.queue = queue;
        }
        @Override
        public void run() {
            Thread.currentThread().setName("thread queue");
            while (true) {
                try {
                    Event event =  queue.take();
                    dispatcher.dispatch(event);
                } catch (InterruptedException e) {
                    log.error("OvertimeTaskRunable",e);
                } catch (Exception e) {
                    log.error("OvertimeTaskRunable",e);
                }
            }
        }
    }


    class OvertimeLongRunable implements Runnable{

        @Override
        public void run() {
            log.info("OvertimeLongRunable run...");
            Thread.currentThread().setName("overtime thread");
            ArrayBlockingQueue<Event> overtimeLongTimeTaskQueue = EventQueue.overtimeLongTimeTaskQueue;
            Event event = null;
            while (true) {
                try {
                    event = overtimeLongTimeTaskQueue.take();
                    dispatcher.dispatch(event);
                } catch (InterruptedException e) {
                    log.error("OvertimeLongRunable",e);
                } catch (Exception e) {
                    log.error("OvertimeLongRunable",e);
                }
            }
        }
    }


}
