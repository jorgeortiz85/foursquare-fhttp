// Copyright 2011 Foursquare Labs Inc. All Rights Reserved.

package com.foursquare.fhttp

import com.twitter.finagle.util.TimerFromNettyTimer
import java.util.concurrent.{Executors, ThreadFactory}
import org.jboss.netty.util.HashedWheelTimer

object FTimer {
  val threadFactory = new ThreadFactory {
    val default = Executors.defaultThreadFactory
    override def newThread(r: Runnable): Thread = {
      val thread = default.newThread(r)
      thread.setDaemon(true)
      thread
    }
  }

  val nettyTimer = new org.jboss.netty.util.HashedWheelTimer(threadFactory)
  val finagleTimer = new com.twitter.finagle.util.TimerFromNettyTimer(nettyTimer)

  nettyTimer.start
}
