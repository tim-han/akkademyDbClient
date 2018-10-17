package com.akkademy

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern._
import akka.util.Timeout

import scala.concurrent.duration._

/**
  * @author tim.han
  */
class Client(val remoteAddress: String) {
  private implicit val timeout = Timeout(5 seconds)
  private implicit val system = ActorSystem("LocalSystem")
  private val remoteDb = system.actorSelection(s"akka.tcp://akkademy@$remoteAddress/user/akkademy-db")

  def set(key: String, value: Object) = {
    remoteDb ? SetRequest(key, value)
  }

  def get(key: String) = {
    remoteDb ? GetRequest(key)
  }
}
