package com.hero.wireless.queue.collection;

import com.hero.wireless.queue.BlockingConsumption;
import com.hero.wireless.queue.FileQueue;
import com.hero.wireless.queue.utils.ProtostuffUtils;

/**
 * 集合对象的消费者
 *
 * @author :  raymond
 * @version :  V1.0
 * @date :  2021-01-21 17:31
 */
@SuppressWarnings("all")
public class CollectConsumption<E> extends BlockingConsumption<E> {


    public CollectConsumption(Class<E> eClass, String path, String topic, String groupName, FileQueue<E> fileQueue, FileQueue.GrowMode growMode, String srcGroupName) throws Exception {
        super(eClass, path, topic, groupName, fileQueue, growMode, srcGroupName);
    }

    @Override
    protected E getData(byte[] bytes) {
        return (E) ProtostuffUtils.deserializer(bytes, CollectionEntry.class).getData();
    }
}
