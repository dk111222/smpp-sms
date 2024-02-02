package com.hero.wireless.test;

import com.hero.wireless.queue.FileQueue;
import com.hero.wireless.web.entity.send.Submit;

public class QueueTest {

    public static void main(String[] args) throws Exception {
        ordinaryTest();
    }

    /**
     * 测试普通队列基本功能
     * @throws Exception 异常
     */
    private static void ordinaryTest() throws Exception {
        FileQueue<Submit> testFilePlusQueue = FileQueue.ordinary(Submit.class, "ordinary");
//        for (int i = 0; i < 10; i++) {
//            Submit submit = new Submit();
//            submit.setPhone_No("" + i);
//            testFilePlusQueue.put(submit);
//        }
//        System.out.println(testFilePlusQueue.size());
//        Submit test;
//        while ((test = testFilePlusQueue.poll()) != null ) {
//            System.out.println(JSONObject.toJSONString(test));
//        }
        testFilePlusQueue.poll();
        System.out.println(testFilePlusQueue.size());


//        for (int i = 0; i < 1; i++) {
//            testFilePlusQueue.put(new Test("test222"));
//        }
//        //这里poll跟进去看readIndex
//        while ((test = testFilePlusQueue.poll()) != null ) {
//            System.out.println(JSONObject.toJSONString(test));
//        }
    }
}
