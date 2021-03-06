package com.akkademy

import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * @author tim.han
  */
class ClientIntegrationSpec extends FunSpecLike with Matchers {
  val client = new Client("127.0.0.1:25522")

  describe("akkademyDbClient") {
    it("should set a value"){
      client.set("123", new Integer(123))
      val futureResult = client.get("123")
      val result = Await.result(futureResult, 5 seconds)
      result should equal(123)
    }
  }

}
