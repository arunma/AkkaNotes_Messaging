package me.rerun.akkanotes.messaging.logtest2

import me.rerun.akkanotes.messaging.protocols.TeacherProtocol._
import org.scalatest.BeforeAndAfterAll
import org.scalatest.MustMatchers
import org.scalatest.WordSpecLike
import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem
import akka.testkit.TestKit

/**
 * This is a little extension to the previous testcase. 
 * 
 * If we mixin the ActorLogging trait to the Actor for logging (which is btw, the right thing to do)
 * then we could actual capture the log messages in the test case.  
 * 
 * The EventFilter intercept method does exactly this.  
 * 
 */
class TeacherTestLogListener extends TestKit(ActorSystem("UniversityMessageSystem", ConfigFactory.parseString("""akka.loggers = ["akka.testkit.TestEventListener"]""")))
  with WordSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

  "A teacher with ActorLogging" must {

    "be verifiable via TestEventListener in response to a QuoteRequest that is sent" in {

      val teacherRef = system.actorOf(Props[TeacherLogActor])

      EventFilter.info(start = "QuoteResponse", occurrences = 1) intercept {
        teacherRef ! QuoteRequest
      }

    }

  }

  override def afterAll() {
    super.afterAll()
    system.shutdown()

  }

}