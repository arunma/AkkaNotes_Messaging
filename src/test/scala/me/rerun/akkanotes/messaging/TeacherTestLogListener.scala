package me.rerun.akkanotes.messaging

import org.scalatest.BeforeAndAfterAll
import org.scalatest.MustMatchers
import org.scalatest.WordSpecLike
import com.typesafe.config.ConfigFactory
import TeacherProtocol.QuoteRequest
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.testkit.TestKit
import akka.testkit.EventFilter

class TeacherTestLogListener extends TestKit(ActorSystem("QuoteSystem", ConfigFactory.parseString("""akka.loggers = ["akka.testkit.TestEventListener"]""")))
  with WordSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

  "A teacher with ActorLogging" must {

    "be verifiable via TestEventListener in response to a QuoteRequest that is sent" in {

      val teacherRef = system.actorOf(Props[Teacher2WithLogging])

      EventFilter.info(message = "Quote printed", occurrences = 1) intercept {
        teacherRef ! QuoteRequest
      }

    }

  }

  override def afterAll() {
    super.afterAll()
    system.shutdown()

  }

}