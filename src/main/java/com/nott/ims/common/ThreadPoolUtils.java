package com.nott.ims.common;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;


@Slf4j
public class ThreadPoolUtils {

    public static final ExecutorService executorService = new ThreadPoolExecutor(0, 1000,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

}
