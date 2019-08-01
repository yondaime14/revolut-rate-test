package com.carllewis14.revoluttest.util.concurrency.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {

    val io: Scheduler
    val ui: Scheduler

}