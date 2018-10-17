package com.akkademy

import akka.actor.Actor
import akka.pattern._
import akka.util.Timeout

import scala.concurrent.duration._

/**
  * @author tim.han
  */
class MessageDeliverer(remoteAddress: String) extends Actor {
  implicit val timeout = Timeout(5 seconds)
  override def receive: Receive = {
    case SetRequest(key, value) =>
      val remoteDb = context.actorSelection(s"akka.tcp://akkademy@${remoteAddress}/user/akkademy-db")
      remoteDb ? SetRequest(key, value)
  }
}
