package com.hero.wireless.queue.collection;

import com.hero.wireless.queue.BlockingProduction;
import com.hero.wireless.queue.utils.ProtostuffUtils;

import java.io.IOException;

/**
 * 集合对象的生产者
 *
 * @author :  raymond
 * @version :  V1.0
 * @date :  2021-01-21 17:31
 */
public class CollectProduction<E> extends BlockingProduction<E> {

    public CollectProduction(String path, String topic) throws IOException {
        super(path, topic);
    }

    @Override
    protected byte[] getBytes(E e) {
        return ProtostuffUtils.serializer(new CollectionEntry<>(e), super.buffer);
    }
}
