package me.rerun.akkanotes.messaging.firenforget

import org.scalatest.BeforeAndAfterAll
import org.scalatest.MustMatchers
import org.scalatest.WordSpecLike
import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.testkit.TestKit
import me.rerun.akkanotes.messaging.protocols.TeacherProtocol.QuoteRequest
import akka.testkit.EventFilter

class TeacherTest extends TestKit(ActorSystem("UniversityMessageSystem", ConfigFactory.parseString("""akka.loggers = ["akka.testkit.TestEventListener"]""")))
  with WordSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

  //1. Sends message to the Print Actor. Not even a testcase actually
  "A teacher" must {

    "print a quote when a QuoteRequest message is sent" in {

      val teacherRef = TestActorRef[TeacherActor]
      teacherRef ! QuoteRequest
    }
  }

  //2. Sends message to the Log Actor. Again, not a testcase per se
  "A teacher with ActorLogging" must {

    "log a quote when a QuoteRequest message is sent" in {

      val teacherRef = TestActorRef[TeacherLogActor]
      teacherRef ! QuoteRequest
    }

    //3. Asserts the internal State of the Log Actor. 
    "have a quote list of size 4" in {

      val teacherRef = TestActorRef[TeacherLogActor]
      teacherRef.underlyingActor.quoteList must have size (4)
      teacherRef.underlyingActor.quoteList must have size (4)
    }

    //4. Verifying log messages from eventStream
    "be verifiable via EventFilter in response to a QuoteRequest that is sent" in {

      val teacherRef = TestActorRef[TeacherLogActor]
      EventFilter.info(pattern = "QuoteResponse*", occurrences = 1) intercept {
        teacherRef ! QuoteRequest
      }
    }

  }

  override def afterAll() {
    super.afterAll()
    system.shutdown()
  }
  
}